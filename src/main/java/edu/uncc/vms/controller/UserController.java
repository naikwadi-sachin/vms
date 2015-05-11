package edu.uncc.vms.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.service.facade.VMSFacadeService;
import edu.uncc.vms.web.form.ControllerCodes;
import edu.uncc.vms.web.form.LoginForm;
import edu.uncc.vms.web.form.UserForm;

@Controller
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private MessageSource messages;

	@Autowired
	@Qualifier("vmsFacadeService")
	private VMSFacadeService facade;

	@RequestMapping(value = "/loginUser", method = RequestMethod.GET)
	public ModelAndView showLogin() {
		return new ModelAndView("login", "login", new LoginForm());
	}

	@RequestMapping(value = "/logoutUser", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "forward:/showPosts?status=" + ControllerCodes.logoutSuccess;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView showRegister() {
		return new ModelAndView("register", "user", new UserForm());
	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public String validateUser(@Valid @ModelAttribute("login") LoginForm login,
			BindingResult result, HttpServletRequest request, Locale locale,
			Model model) {
		if (result.hasErrors()) {
			logger.debug("user form has errors : "
					+ result.getErrorCount());
			model.addAttribute("login", login);
			return "login";
		}

		logger.debug("Validating user " + login.toString());
		UserEntity user = new UserEntity();
		user.setEmail(login.getEmail());
		user.setPassword(login.getPassword());
		user.setUserType(login.getUserType());

		try {
			user = facade.checkUser(user);
		} catch (CannotGetJdbcConnectionException ce) {

			ObjectError error = new ObjectError("invalidUser",
					messages.getMessage("db.error", null, locale));
			result.addError(error);
			model.addAttribute("login", login);
			return "login";
		}
		logger.debug("loginStatus = " + user);
		if (user.getUserId() != 0) {
			request.getSession().setAttribute("user", user);
			return "forward:/showPosts?status=" + ControllerCodes.loginSuccess;
		} else {
			ObjectError error = new ObjectError("invalidUser",
					messages.getMessage("user.invalid", null, locale));
			result.addError(error);
			model.addAttribute("login", login);
			return "login";
		}
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") UserForm user,
			BindingResult errors, Locale locale, Model model) {
		logger.debug("registering user " + user.toString());
		if (errors.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}

		try {
			UserEntity ruser = new UserEntity();
			ruser.setEmail(user.getEmail());
			ruser.setFirstName(user.getFirstName());
			ruser.setLastName(user.getLastName());
			ruser.setPassword(user.getPassword());
			ruser.setUserId(user.getUserId());
			ruser.setUserType(user.getUserType());
			
			USER_STATUS_CODE status = facade.register(ruser);
			logger.debug("registrationStatus = " + status);
			if (status.equals(USER_STATUS_CODE.REGISTRATION_SUCCESS)) {
				model.addAttribute("successMessage", messages.getMessage(
						"user.registration.success",
						new Object[] { user.getEmail() }, locale));

				try {
					facade.sendEmail(user.getEmail(),
							"Welcome to VMS community!",
							"Your registration is confirmed, Thank you for joining..");
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "success";
			} else if (status.equals(USER_STATUS_CODE.DUPLICATE_USER)) {
				ObjectError error = new ObjectError("duplicateUser",
						messages.getMessage("user.duplicate",
								new Object[] { user.getEmail() }, locale));
				errors.addError(error);
			} else {

				ObjectError error = new ObjectError("registrationError",
						messages.getMessage("user.registration.error",
								new Object[] { user.getEmail() }, locale));
				errors.addError(error);
			}
			return "register";
		} catch (CannotGetJdbcConnectionException ce) {
			model.addAttribute("dbError",
					messages.getMessage("db.error", null, locale));
			model.addAttribute("login", new LoginForm());
			return "login";
		}
	}
}

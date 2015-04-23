package edu.uncc.vms.web;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.service.facade.VMSFacadeService;
import edu.uncc.vms.web.form.ControllerCodes;

@Controller
public class DonationController {

	@Autowired
	@Qualifier("vmsFacadeService")
	private VMSFacadeService facade;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/donate/{eventId}", method = RequestMethod.GET)
	public String donate(@PathVariable("eventId") int eventId, Model model) {
		DonationEntity donation = new DonationEntity();
		System.out.println("donate eventId="+eventId);
		donation.setEventId(eventId);
		model.addAttribute("donation", donation);
		EventEntity event = facade.getPost(eventId);
		model.addAttribute("event", event);
		return "makeDonation";
	}

	@RequestMapping(value = "/donate", method = RequestMethod.POST)
	public String acceptDonation(
			@Valid @ModelAttribute("donation") DonationEntity donation,
			BindingResult result, HttpSession session, Locale locale,
			Model model) {
		System.out.println("acceptDonation " + donation);
		
		if (result.hasErrors()) {
			EventEntity event = facade.getPost(donation.getEventId());
			model.addAttribute("event", event);
			model.addAttribute("donation", donation);
			return "makeDonation";
		}
		
		if (session.getAttribute("user") != null) {
			UserEntity user = (UserEntity) session.getAttribute("user");
			donation.setUserId(user.getUserId());
		}
		else
		{
			ObjectError error = new ObjectError("unauthorizedDonationUser",
					messages.getMessage("user.donation.unauthorized",
							null, locale));
			result.addError(error);
			EventEntity event = facade.getPost(donation.getEventId());
			model.addAttribute("event", event);
			model.addAttribute("donation", donation);
			return "makeDonation";
		}

		DONATION_STATUS_CODE donationResult = facade.insertDonation(donation);
		System.out.println("donationResult " + donationResult);
		String status="";
		if (donationResult == DONATION_STATUS_CODE.DONATION_SUCCESS) {
			status=ControllerCodes.donationSuccess;
		} else if (donationResult == DONATION_STATUS_CODE.DONATION_ERROR) {
			status=ControllerCodes.donationError;
		}

		return "forward:/showPosts?status=" + status;
	}
}

package edu.uncc.vms.web;

import java.util.ArrayList;
import java.util.Locale;

import javax.mail.MessagingException;
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
import edu.uncc.vms.domain.Item;
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

	@RequestMapping(value = "/support/{eventId}", method = RequestMethod.GET)
	public String nonMonetaryDonation(@PathVariable("eventId") int eventId,
			Model model) {
		System.out.println("nonMonetaryDonation eventId=" + eventId);
		EventEntity event = facade.getPost(eventId);
		model.addAttribute("event", event);
		Item item = new Item();
		item.setEventId(eventId);
		model.addAttribute("item", item);
		return "support";
	}
	
	@RequestMapping(value="/support", method = RequestMethod.POST)
	public String saveNonMonetaryDonation(@Valid @ModelAttribute("item") Item item, BindingResult result,HttpSession session,Locale locale, Model model)
	{
		System.out.println("saveNonMonetaryDonation " + item);

		if (result.hasErrors()) {
			EventEntity event = facade.getPost(item.getEventId());
			model.addAttribute("event", event);
			model.addAttribute("item", item);
			return "support";
		}

		UserEntity user;

		if (session.getAttribute("user") != null) {
			user = (UserEntity) session.getAttribute("user");
			item.setUserId(user.getUserId());
		} else {
			ObjectError error = new ObjectError("unauthorizedDonationUser",
					messages.getMessage("user.donation.unauthorized", null,
							locale));
			result.addError(error);
			EventEntity event = facade.getPost(item.getEventId());
			model.addAttribute("event", event);
			model.addAttribute("donation", item);
			return "support";
		}

		DONATION_STATUS_CODE donationResult = facade.insertItemDonation(item);
		System.out.println("donationResult " + donationResult);
		String status = "";
		if (donationResult == DONATION_STATUS_CODE.DONATION_SUCCESS) {
			status = ControllerCodes.donationSuccess;

			try {
				facade.sendEmail(user.getEmail(),
						"Your donations are received!",
						"Thank you for donating " + item.getItemCategory()
								+ " description : " + item.getItemDescription());
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		} else if (donationResult == DONATION_STATUS_CODE.DONATION_ERROR) {
			status = ControllerCodes.donationError;
		}

		return "forward:/showPosts?status=" + status;

	}

	@RequestMapping(value = "/donate/{eventId}", method = RequestMethod.GET)
	public String donate(@PathVariable("eventId") int eventId, Model model) {
		DonationEntity donation = new DonationEntity();
		System.out.println("donate eventId=" + eventId);
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

		UserEntity user;

		if (session.getAttribute("user") != null) {
			user = (UserEntity) session.getAttribute("user");
			donation.setUserId(user.getUserId());
		} else {
			ObjectError error = new ObjectError("unauthorizedDonationUser",
					messages.getMessage("user.donation.unauthorized", null,
							locale));
			result.addError(error);
			EventEntity event = facade.getPost(donation.getEventId());
			model.addAttribute("event", event);
			model.addAttribute("donation", donation);
			return "makeDonation";
		}

		DONATION_STATUS_CODE donationResult = facade.insertDonation(donation);
		System.out.println("donationResult " + donationResult);
		String status = "";
		if (donationResult == DONATION_STATUS_CODE.DONATION_SUCCESS) {
			status = ControllerCodes.donationSuccess;

			try {
				facade.sendEmail(user.getEmail(),
						"Your donations are received!",
						"Thank you for donating " + donation.getAmount()
								+ " $, your money will be utilized properly.");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		} else if (donationResult == DONATION_STATUS_CODE.DONATION_ERROR) {
			status = ControllerCodes.donationError;
		}

		return "forward:/showPosts?status=" + status;
	}

	@RequestMapping(value = "/showDonations", method = RequestMethod.GET)
	public String showDonations(Model model) {
		ArrayList<DonationEntity> donations = facade.getDonations(null);
		model.addAttribute("donations", donations);
		return "showDonations";
	}
}

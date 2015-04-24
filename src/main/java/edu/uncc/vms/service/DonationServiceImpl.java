package edu.uncc.vms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.DonationItem;
import edu.uncc.vms.domain.Item;
import edu.uncc.vms.repository.DonationRepository;

@Service("donationService")
public class DonationServiceImpl implements DonationService{

	@Autowired
	private DonationRepository donationRepository;
	
	@Override
	public DONATION_STATUS_CODE insertDonation(DonationEntity donation) {
		return donationRepository.insertDonation(donation);
	}

	@Override
	public ArrayList<DonationItem> getDonations(DonationEntity donation) {
		return donationRepository.getDonations(donation);
	}

	@Override
	public DONATION_STATUS_CODE insertItemDonation(Item item) {
		return donationRepository.insertItemDonation(item);
	}

}

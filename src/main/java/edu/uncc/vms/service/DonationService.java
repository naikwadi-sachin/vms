package edu.uncc.vms.service;

import java.util.ArrayList;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;

public interface DonationService {

	public DONATION_STATUS_CODE insertDonation(DonationEntity donation);
	public ArrayList<DonationEntity> getDonations(DonationEntity donation);
}

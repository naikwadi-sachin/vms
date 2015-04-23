package edu.uncc.vms.repository;

import java.util.ArrayList;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;

public interface DonationRepository {

	public DONATION_STATUS_CODE insertDonation(DonationEntity donation);
	public ArrayList<DonationEntity> getDonations(DonationEntity donation);
}

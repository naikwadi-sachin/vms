package edu.uncc.vms.repository;

import java.util.ArrayList;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.DonationItem;
import edu.uncc.vms.domain.Item;

public interface DonationRepository {

	public DONATION_STATUS_CODE insertDonation(DonationEntity donation);
	public ArrayList<DonationItem> getDonations(DonationEntity donation);
	
	public DONATION_STATUS_CODE insertItemDonation(Item item);
}

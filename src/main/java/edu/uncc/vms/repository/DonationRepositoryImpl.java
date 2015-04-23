package edu.uncc.vms.repository;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.EVENT_STATUS_CODE;

@Repository("donationRepository")
public class DonationRepositoryImpl implements DonationRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public DONATION_STATUS_CODE insertDonation(DonationEntity donation) {

		System.out.println("insertDonation " + donation.toString());
		String sql = "insert into vms_donation(event_id,user_id,amount,card_holder_name,billing_address,"
				+ "card_type,card_number,expiry_month,expiry_year,security_code) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, donation.getEventId(),
				donation.getUserId(), donation.getAmount(),
				donation.getCardHolderName(), donation.getBillingAddress(),
				donation.getCardType(), donation.getCardNumber(),
				donation.getExpiryMonth(), donation.getExpiryYear(),
				donation.getSecurityCode());
		System.out.println("insertDonation count=" + count);
		if (count > 0)
			return DONATION_STATUS_CODE.DONATION_SUCCESS;
		else
			return DONATION_STATUS_CODE.DONATION_ERROR;
	}

	@Override
	public ArrayList<DonationEntity> getDonations(DonationEntity donation) {
		return null;
	}

}

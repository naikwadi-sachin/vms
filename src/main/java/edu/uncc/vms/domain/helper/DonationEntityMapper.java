package edu.uncc.vms.domain.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.uncc.vms.domain.DonationEntity;

public class DonationEntityMapper implements RowMapper<DonationEntity>{

	@Override
	public DonationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonationEntity donation = new DonationEntity();
		donation.setEventId(rs.getInt("event_id"));
		donation.setUserId(rs.getInt("user_id"));
		donation.setAmount(rs.getString("amount"));
		donation.setCardHolderName(rs.getString("card_holder_name"));
		donation.setBillingAddress(rs.getString("billing_address"));
		
		donation.setCardType(rs.getString("card_type"));
		donation.setCardNumber(rs.getString("card_number"));
		donation.setExpiryMonth(rs.getString("expiry_month"));
		donation.setExpiryYear(rs.getString("expiry_year"));
		donation.setSecurityCode(rs.getInt("security_code"));
		donation.setDonationDate(rs.getString("donation_date"));
		return donation;
	}

}

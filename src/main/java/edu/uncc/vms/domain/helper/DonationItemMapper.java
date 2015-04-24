package edu.uncc.vms.domain.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.uncc.vms.domain.DonationItem;

public class DonationItemMapper implements RowMapper<DonationItem>{

	@Override
	public DonationItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonationItem item = new DonationItem();
		item.setEventName(rs.getString("event_name"));
		item.setUserName(rs.getString("user_name"));
		item.setEmail(rs.getString("email"));
		item.setDonationCategory(rs.getString("item_category"));
		item.setDonation(rs.getString("donation"));
		item.setDonationDate(rs.getString("donation_date"));
		return item;
	}


}

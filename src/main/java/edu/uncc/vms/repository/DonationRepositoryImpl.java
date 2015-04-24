package edu.uncc.vms.repository;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.DonationItem;
import edu.uncc.vms.domain.Item;
import edu.uncc.vms.domain.helper.DonationItemMapper;

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
	public ArrayList<DonationItem> getDonations(DonationEntity donation) {

		try {
			String sql = " SELECT e.event_name, upper(concat(u.first_name,' ',u.last_name)) as user_name,u.email, 'Funds' as item_category, concat(d.amount,'$') as donation,d.donation_date "
					+ "from vms_donation d inner join vms_event e on d.event_id = e.event_id inner join vms_user u on d.user_id = u.user_id "
					+ "union "
					+ "SELECT e.event_name, upper(concat(u.first_name,' ',u.last_name)) as user_name,u.email, d.item_category, d.item_description as donation,d.item_date as donation_date "
					+ "from vms_donation_items d inner join vms_event e on d.event_id = e.event_id inner join vms_user u on d.user_id = u.user_id where 1=1 ";
			String where = "";
			String orderBy = " order by donation_date desc";
			if (donation != null) {
			}

			sql = sql + where + orderBy;

			System.out.println("sql:" + sql);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			ArrayList<DonationItem> donations = (ArrayList<DonationItem>) jdbcTemplate
					.query(sql, new DonationItemMapper());
			return donations;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			return null;
		}
	}

	@Override
	public DONATION_STATUS_CODE insertItemDonation(Item item) {

		System.out.println("insertItemDonation " + item.toString());
		String sql = "insert into vms_donation_items(event_id,user_id,item_category,item_description) "
				+ "values(?,?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, item.getEventId(),
				item.getUserId(), item.getItemCategory(),
				item.getItemDescription()
				);
		System.out.println("insertItemDonation count=" + count);
		if (count > 0)
			return DONATION_STATUS_CODE.DONATION_SUCCESS;
		else
			return DONATION_STATUS_CODE.DONATION_ERROR;
	}

}

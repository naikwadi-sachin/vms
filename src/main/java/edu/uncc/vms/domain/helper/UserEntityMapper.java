package edu.uncc.vms.domain.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.uncc.vms.domain.UserEntity;

public class UserEntityMapper implements RowMapper<UserEntity> {

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserEntity user = new UserEntity();
		user.setEmail(rs.getString("email"));
		return user;
	}

}

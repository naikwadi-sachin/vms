package edu.uncc.vms.repository;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private DataSource dataSource;

	private SimpleJdbcCall jdbcCall;

	/*
	 * @Override public boolean checkUser(UserEntity user) { SqlParameterSource
	 * in = new MapSqlParameterSource().addValue("in_email",
	 * user.getEmail()).addValue("in_password", user.getPassword());
	 * this.jdbcCall = new
	 * SimpleJdbcCall(dataSource).withProcedureName("check_user"); Map<String,
	 * Object> out = jdbcCall.execute(in);
	 * System.out.println("email="+out.get("email"));
	 * System.out.println("user_exists="+out.get("user_exists"));
	 * if(!(out.get("user_exists")==null)) return true; return false; }
	 */

	@Override
	public UserEntity checkUser(UserEntity user) {
		String sql = "select user_id,user_type,email,first_name,last_name from vms.vms_user where email = ? and password=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int userId = 0;
		try {
//			userId = jdbcTemplate.queryForInt(sql, user.getEmail(),
//					user.getPassword(),user.getUserType());
			user = jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail(),user.getPassword()}, new BeanPropertyRowMapper<UserEntity>(UserEntity.class));
			System.out.println("in user-repo chkuser = " + user.toString());
		} catch (EmptyResultDataAccessException e) {
		}
		
		System.out.println("checkUser1 user_id=" + userId);
		//user.setUserId(userId);
		return user;
	}

	@Override
	public USER_STATUS_CODE insertUser(UserEntity user) {

		String sql = "insert into vms.vms_user(first_name, last_name, email,password) values(?,?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			int count = jdbcTemplate.update(sql, user.getFirstName(),
					user.getLastName(), user.getEmail(), user.getPassword());
			System.out.println("addUser count=" + count);
			if (count > 0)
				return USER_STATUS_CODE.REGISTRATION_SUCCESS;
			else
				return USER_STATUS_CODE.REGISTRATION_FAILED;
		} catch (DuplicateKeyException e) {
			return USER_STATUS_CODE.DUPLICATE_USER;
		}
	}

}

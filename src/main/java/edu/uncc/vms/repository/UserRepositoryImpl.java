package edu.uncc.vms.repository;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	private DataSource dataSource;

	@Override
	public UserEntity checkUser(UserEntity user) {
		String sql = "select user_id,user_type,email,first_name,last_name from vms.vms_user where email = ? and password=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int userId = 0;
		try {
//			userId = jdbcTemplate.queryForInt(sql, user.getEmail(),
//					user.getPassword(),user.getUserType());
			user = jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail(),user.getPassword()}, new BeanPropertyRowMapper<UserEntity>(UserEntity.class));
			logger.debug("in user-repo chkuser = " + user.toString());
		} catch (EmptyResultDataAccessException e) {
		}
		
		logger.debug("checkUser1 user_id=" + userId);
		return user;
	}

	@Override
	public USER_STATUS_CODE insertUser(UserEntity user) {

		String sql = "insert into vms.vms_user(first_name, last_name, email,password) values(?,?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			int count = jdbcTemplate.update(sql, user.getFirstName(),
					user.getLastName(), user.getEmail(), user.getPassword());
			logger.debug("addUser count=" + count);
			if (count > 0)
				return USER_STATUS_CODE.REGISTRATION_SUCCESS;
			else
				return USER_STATUS_CODE.REGISTRATION_FAILED;
		} catch (DuplicateKeyException e) {
			return USER_STATUS_CODE.DUPLICATE_USER;
		}
	}

}

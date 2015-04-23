package edu.uncc.vms.repository;

import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;

public interface UserRepository {

	public UserEntity checkUser(UserEntity user);
	
	public USER_STATUS_CODE insertUser(UserEntity user);
}

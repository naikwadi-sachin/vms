package edu.uncc.vms.service;

import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;

public interface UserService {

	public UserEntity checkUser(UserEntity user);
	
	public USER_STATUS_CODE register(UserEntity user);
}

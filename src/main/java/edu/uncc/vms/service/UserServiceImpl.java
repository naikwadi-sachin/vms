package edu.uncc.vms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userRepositoryImpl")
	private UserRepository userRepository;
	
	@Override
	public UserEntity checkUser(UserEntity user) {
		return userRepository.checkUser(user);
	}

	@Override
	public USER_STATUS_CODE register(UserEntity user) {
		return userRepository.insertUser(user);
	}

}

package com.sosxsos.ssm.service;

import java.util.List;

import com.sosxsos.ssm.entity.User;

public interface UserService {

	List<User> getUserList(int offset, int limit);
	 
}

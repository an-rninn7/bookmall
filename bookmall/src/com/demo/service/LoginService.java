package com.demo.service;

import com.demo.common.model.User;

public class LoginService {

	private static final User dao = new User().dao();

	public User doLogin(String email, String password) {
		User user = dao.findFirst(
				"select * from user where email=? and password=?", email,
				password);
		return user;
	}

}

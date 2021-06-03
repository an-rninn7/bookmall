package com.demo.service;

import com.demo.common.model.User;
import com.jfinal.plugin.activerecord.Page;

public class UserService {

	private static final User dao = new User().dao();

	public Page<User> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *",
				"from user where type=0 order by id desc");
	}

	public void doaudit(Integer userId, Integer status) {
		User user = dao.findById(userId);
		user.setStatus(status);
		user.update();
	}

}

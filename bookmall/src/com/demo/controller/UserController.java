package com.demo.controller;

import com.demo.common.model.User;
import com.demo.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class UserController extends Controller {

	static UserService service = new UserService();

	public void index() {
		render("index.html");
	}

	public void audit() {
		Page<User> page = service.paginate(1, 10);
		setAttr("page", page);
		render("audit.html");
	}

	public void doaudit() {
		Integer userId = getParaToInt(0);
		Integer status = getParaToInt(1);
		service.doaudit(userId, status);
		renderJson();
	}

	public void search() {
		Page<User> paginate = service.paginate(1, 10);
		render("audit.html");
	}

	public void logout() {
		getSession().invalidate();
		redirect("/login");
	}

}

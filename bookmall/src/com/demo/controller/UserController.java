package com.demo.controller;

import com.demo.service.LoginService;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	static LoginService service = new LoginService();

	public void index() {
		render("login.html");
	}
}
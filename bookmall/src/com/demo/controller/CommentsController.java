package com.demo.controller;

import com.demo.service.LoginService;
import com.jfinal.core.Controller;

public class CommentsController extends Controller {
	static LoginService service = new LoginService();

	public void audit() {
		render("audit.html");
	}
}

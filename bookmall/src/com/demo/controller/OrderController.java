package com.demo.controller;

import com.demo.service.LoginService;
import com.jfinal.core.Controller;

public class OrderController extends Controller {
	static LoginService service = new LoginService();

	public void list() {
		render("list.html");
	}

	public void detail() {
		render("detail.html");
	}

	public void mylist() {
		render("mylist.html");
	}
}

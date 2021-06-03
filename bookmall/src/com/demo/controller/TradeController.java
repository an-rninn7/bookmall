package com.demo.controller;

import com.demo.service.LoginService;
import com.jfinal.core.Controller;

public class TradeController extends Controller {
	static LoginService service = new LoginService();

	public void secondaudit() {
		render("secondaudit.html");
	}

	public void exchangeaudit() {
		render("exchangeaudit.html");
	}
}

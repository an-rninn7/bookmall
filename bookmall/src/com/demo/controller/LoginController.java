package com.demo.controller;

import com.demo.common.model.User;
import com.demo.constants.Constants;
import com.demo.controller.validate.LoginValidator;
import com.demo.service.LoginService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class LoginController extends Controller {
	static LoginService service = new LoginService();

	public void index() {
		render("login.html");
	}

	public void register() {
		render("register.html");
	}

	@Before(LoginValidator.class)
	public void doregister() {
		User user = getModel(User.class, "", true);
		String errorCode = Constants.error_code_error;
		try {
			errorCode = user.save() ? Constants.error_code_success
					: Constants.error_code_error;
		} catch (Exception e) {
			if (e.getMessage().contains("Duplicate entry")) {
				errorCode = Constants.error_code_email_exists;
			}
		}
		setAttr("errorCode", errorCode);
		render("registerResult.html");
	}

}

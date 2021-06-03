package com.demo.controller;

import com.demo.common.model.User;
import com.demo.constants.Constants;
import com.demo.controller.validate.LoginValidator;
import com.demo.service.LoginService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;

public class LoginController extends Controller {
	static LoginService service = new LoginService();

	public void index() {
		render("login.html");
	}

	public void dologin() {
		String email = getPara("email");
		String password = getPara("password");
		password = HashKit.sha1(password);
		User user = service.doLogin(email, password);
		if (user != null) {
			setSessionAttr(Constants.SESSION_LOGIN_USER, user);
			redirect("/user");
		} else {
			setAttr("errorCode", Constants.error_code_error);
			keepPara();
			render("login.html");
		}
	}

	public void register() {
		render("register.html");
	}

	@Before(LoginValidator.class)
	public void doregister() {
		User user = getBean(User.class, "", true);
		String errorCode = Constants.error_code_error;
		String password = user.getPassword();
		password = HashKit.sha1(password);
		user.setStatus(Constants.user_status_auditing);
		try {
			user.setPassword(password);
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

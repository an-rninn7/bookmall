package com.demo.controller.validate;

import com.demo.common.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

	protected void validate(Controller c) {
		validateRequired("name", "errorMsg", "姓名不能为空.");
		validateEmail("email", "errorMsg", "请输入正确的邮箱地址.");
		validateEqualField("password", "password1", "errorMsg", "密码输入不一致.");
	}

	protected void handleError(Controller c) {
		c.keepBean(User.class, "");
		c.render("register.html");
	}
}

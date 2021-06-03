package com.demo.common;

import com.demo.constants.Constants;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * BlogInterceptor 此拦截器仅做为示例展示，在本 demo 中并不需要
 */
public class SessionInterceptor implements Interceptor {

	public void intercept(Invocation ai) {
		System.out.println("SessionInterceptor Before invoking "
				+ ai.getActionKey());

		Controller ctl = ai.getController();

		// 判断用户是否登录
		Object user = ai.getController().getSessionAttr(
				Constants.SESSION_LOGIN_USER);
		String action = ai.getActionKey();
		if (user != null || action.indexOf("login") >= 0) {
			ai.invoke();
		} else {
			ctl.redirect("/login");
		}
		System.out.println("SessionInterceptor After invoking "
				+ ai.getActionKey());
	}
}

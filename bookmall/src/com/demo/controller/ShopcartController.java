package com.demo.controller;

import java.util.List;

import com.demo.common.model.JsonResult;
import com.demo.common.model.Shopcart;
import com.demo.common.model.User;
import com.demo.constants.Constants;
import com.demo.service.ShopcartService;
import com.jfinal.core.Controller;

public class ShopcartController extends Controller {

	static ShopcartService service = new ShopcartService();

	public void index() {
		User user = getSessionAttr(Constants.SESSION_LOGIN_USER);
		List<Shopcart> list = service.getList(user);
		setAttr("list", list);
		render("cart.html");
	}

	public void add() {
		Integer bid = getParaToInt(0);
		Integer num = getParaToInt(1);
		User user = getSessionAttr(Constants.SESSION_LOGIN_USER);
		String errorCode = service.addBook(user, bid, num);
		JsonResult jr = new JsonResult(errorCode);
		renderJson(jr);
	}

	public void remove() {
		Integer id = getParaToInt(0);
		Shopcart.dao.deleteById(id);
		JsonResult jr = new JsonResult(Constants.error_code_success);
		renderJson(jr);
	}

}

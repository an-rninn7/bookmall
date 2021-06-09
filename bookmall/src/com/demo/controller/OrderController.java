package com.demo.controller;

import java.util.List;

import com.demo.common.model.JsonResult;
import com.demo.common.model.Order;
import com.demo.common.model.Shopcart;
import com.demo.common.model.User;
import com.demo.constants.Constants;
import com.demo.service.OrderService;
import com.demo.service.ShopcartService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class OrderController extends Controller {
	static OrderService service = new OrderService();

	static ShopcartService shopcartService = new ShopcartService();

	public void settle() {
		User user = getSessionAttr(Constants.SESSION_LOGIN_USER);
		List<Shopcart> list = shopcartService.getList(user);
		setAttr("list", list);
		setAttr("user", user);
		render("settle.html");
	}

	public void submit() {
		Order order = getBean(Order.class, "", true);
		User user = getSessionAttr(Constants.SESSION_LOGIN_USER);
		String errorCode = service.submit(user, order);
		JsonResult jr = new JsonResult(errorCode);
		renderJson(jr);
	}

	public void pay() {
		String orderId = getPara();

		User suser = getSessionAttr(Constants.SESSION_LOGIN_USER);

		String errorCode = service.pay(suser.getId(), orderId);
		JsonResult jr = new JsonResult(errorCode);
		renderJson(jr);
	}

	public void delivery() {
		String orderId = getPara();
		String errorCode = service.delivery(orderId);
		JsonResult jr = new JsonResult(errorCode);
		renderJson(jr);
	}

	public void received() {
		String orderId = getPara();
		String errorCode = service.received(orderId);
		JsonResult jr = new JsonResult(errorCode);
		renderJson(jr);
	}

	public void close() {
		String orderId = getPara();
		String errorCode = service.close(orderId);
		JsonResult jr = new JsonResult(errorCode);
		renderJson(jr);
	}

	public void list() {
		User suser = getSessionAttr(Constants.SESSION_LOGIN_USER);
		String view = Constants.user_type_admin == suser.getType() ? "list.html"
				: "mylist.html";
		Page<Order> page = service.orderList(1, 10, suser);
		setAttr("page", page);
		render(view);
	}

	public void detail() {
		String orderId = getPara();
		Order order = service.getOrder(orderId);
		setAttr("order", order);
		render("detail.html");
	}

}

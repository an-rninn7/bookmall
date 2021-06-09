package com.demo.controller;

import java.util.Date;
import java.util.List;

import com.demo.common.model.Comments;
import com.demo.common.model.JsonResult;
import com.demo.common.model.Order;
import com.demo.common.model.OrderItem;
import com.demo.common.model.User;
import com.demo.constants.Constants;
import com.demo.service.CommentsService;
import com.demo.service.OrderService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class CommentsController extends Controller {

	static OrderService orderService = new OrderService();

	static CommentsService service = new CommentsService();

	public void index() {
		String orderId = getPara();
		Order order = orderService.getOrder(orderId);
		setAttr("order", order);
		render("comments.html");
	}

	public void mine() {
		User suser = getSessionAttr(Constants.SESSION_LOGIN_USER);
		Page<Comments> page = service.mine(1, 10, suser);
		setAttr("page", page);
		render("mycomments.html");
	}

	public void submit() {
		String orderId = getPara("orderId");
		Order order = orderService.getOrder(orderId);
		List<OrderItem> items = order.getItems();
		Date now = new Date();
		for (OrderItem orderItem : items) {
			Comments comments = getBean(Comments.class, orderItem.getBookId()
					.toString(), true);
			comments.setBookId(orderItem.getBookId());
			comments.setCreateTime(now);
			comments.setOrderId(orderId);
			comments.setStatus(Constants.comments_status_auditing);
			comments.setUserId(order.getUserId());
			comments.setType(Constants.comments_type_order);
			comments.save();
		}
		order.setCommented(Constants.order_comments_yes);
		order.update();

		JsonResult jr = new JsonResult(Constants.error_code_success);
		renderJson(jr);
	}

	public void auditList() {
		Page<Comments> page = service.getAuditList(1, 10,
				Constants.comments_status_auditing);
		setAttr("page", page);
		render("audit.html");
	}

	public void pass() {
		Integer id = getParaToInt();
		service.audit(id, Constants.comments_status_pass);
		redirect("/comments/auditList");
	}

	public void reject() {
		Integer id = getParaToInt();
		service.audit(id, Constants.comments_status_reject);
		redirect("/comments/auditList");
	}
}

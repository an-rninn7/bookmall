package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.common.model.Book;
import com.demo.common.model.Order;
import com.demo.common.model.OrderItem;
import com.demo.common.model.Shopcart;
import com.demo.common.model.User;
import com.demo.constants.Constants;
import com.demo.constants.GeneralUtil;
import com.jfinal.plugin.activerecord.Page;

public class OrderService {

	private static final Order dao = new Order().dao();
	private static final Book bookdao = new Book().dao();
	static ShopcartService shopcartService = new ShopcartService();

	public String submit(User user, Order order) {
		order.setCreateTime(new Date());
		order.setUserId(user.getId());
		if ("online".equals(order.getPayway())) {
			order.setStatus(Constants.order_status_payment);
		} else {
			order.setStatus(Constants.order_status_delivery);
		}
		List<Shopcart> list = shopcartService.getList(user);
		double total = 0;
		String orderId = "O" + GeneralUtil.getBizNo()
				+ GeneralUtil.getCode(3, 0);
		List<OrderItem> items = new ArrayList<OrderItem>();
		for (Shopcart shopcart : list) {
			Book book = bookdao.findById(shopcart.getBookId());
			if (book.getInventory() < shopcart.getNum()) {
				return Constants.error_code_inventory_insufficient;
			}
			OrderItem item = new OrderItem();
			String id = "OI" + GeneralUtil.getBizNo()
					+ GeneralUtil.getCode(3, 0);
			item.setId(id);
			item.setBookId(shopcart.getBookId());

			item.setNum(shopcart.getNum());
			item.setOrderId(orderId);
			item.setPrice(shopcart.getPrice());
			item.setPriceType(book.getPriceType());
			item.setStatus(Constants.order_item_status_nomarl);
			item.setTotal(shopcart.getTotal());
			items.add(item);
			total += item.getTotal();
		}
		order.setTotal(total);
		order.setId(orderId);
		order.save();

		for (OrderItem orderItem : items) {
			orderItem.save();
		}
		shopcartService.clear(user.getId());
		return Constants.error_code_success;
	}

	public String pay(Integer uid, String orderId) {
		Order order = Order.dao.findById(orderId);

		User user = User.dao.findById(uid);

		if (user.getBlance() < order.getTotal()) {
			return Constants.error_code_blance_insufficient;
		}
		order.setPayTime(new Date());
		order.setStatus(Constants.order_status_delivery);
		order.update();

		user.setBlance(user.getBlance() - order.getTotal());
		user.update();
		return Constants.error_code_success;
	}

	public String delivery(String orderId) {
		Order order = Order.dao.findById(orderId);
		order.setDeliveryTime(new Date());
		order.setStatus(Constants.order_status_receiving);
		order.update();
		return Constants.error_code_success;
	}

	public String received(String orderId) {
		Order order = Order.dao.findById(orderId);
		order.setStatus(Constants.order_status_success);
		order.update();
		return Constants.error_code_success;
	}

	public String close(String orderId) {
		Order order = Order.dao.findById(orderId);
		order.setStatus(Constants.order_status_close);
		order.update();
		return Constants.error_code_success;
	}

	public Page<Order> orderList(int pageNumber, int pageSize, User suser) {
		if (Constants.user_type_admin == suser.getType()) {
			return dao.paginate(pageNumber, pageSize, "select *",
					"from `order` order by id desc");
		} else {
			return dao.paginate(pageNumber, pageSize, "select *",
					"from `order` where user_id=? order by id desc",
					suser.getId());
		}

	}

	public Order getOrder(String orderId) {
		Order order = dao.findById(orderId);
		List<OrderItem> list = OrderItem.dao.find(
				"select * from order_item where order_id=?", orderId);
		for (OrderItem item : list) {
			item.setBook(Book.dao.findById(item.getBookId()));
		}
		order.setItems(list);
		return order;
	}
}

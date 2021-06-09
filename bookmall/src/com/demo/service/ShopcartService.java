package com.demo.service;

import java.util.List;

import com.demo.common.model.Book;
import com.demo.common.model.Shopcart;
import com.demo.common.model.User;
import com.demo.constants.Constants;

public class ShopcartService {

	private static final Shopcart dao = new Shopcart().dao();

	public String addBook(User user, Integer bid, Integer num) {
		Book book = Book.dao.findById(bid);
		Shopcart shopcart = dao.findFirst(
				"select * from shopcart where user_id=? and book_id=?",
				user.getId(), bid);
		if (shopcart == null) {
			shopcart = new Shopcart();
			shopcart.setBookId(bid);
			shopcart.setUserId(user.getId());
			shopcart.setNum(num);
			shopcart.setPrice(book.getPrice());
			shopcart.setTotal(shopcart.getPrice() * shopcart.getNum());
			shopcart.save();
		} else {
			shopcart.setNum(shopcart.getNum() + num);
			shopcart.setPrice(book.getPrice());
			shopcart.setTotal(shopcart.getPrice() * shopcart.getNum());
			shopcart.update();
		}
		return Constants.error_code_success;
	}

	public List<Shopcart> getList(User user) {
		List<Shopcart> list = dao.find(
				"select * from shopcart where user_id=? ", user.getId());
		for (Shopcart shopcart : list) {
			shopcart.setBook(Book.dao.findById(shopcart.getBookId()));
		}
		return list;
	}

	public String removeBook(User user, Integer bid) {
		Shopcart shopcart = dao.findFirst(
				"select * from shopcart where user_id=? and book_id=?",
				user.getId(), bid);
		shopcart.delete();
		return Constants.error_code_success;
	}

	public void clear(Integer userId) {
		List<Shopcart> list = dao.find(
				"select * from shopcart where user_id=? ", userId);
		for (Shopcart shopcart : list) {
			shopcart.delete();
		}
	}

}

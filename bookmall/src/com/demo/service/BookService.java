package com.demo.service;

import com.demo.common.model.Book;
import com.jfinal.plugin.activerecord.Page;

public class BookService {

	private static final Book dao = new Book().dao();

	public Page<Book> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *",
				"from book order by id desc");
	}

	public String getPriceType(double price) {
		// A(0,29),B(30,49),C(50,69),D(70,)
		if (price < 30) {
			return "A";
		} else if (price < 50) {
			return "B";
		} else if (price < 70) {
			return "C";
		} else {
			return "D";
		}
	}

}

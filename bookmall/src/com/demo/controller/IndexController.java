package com.demo.controller;

import com.demo.common.model.Book;
import com.demo.service.BookService;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * IndexController
 */
@Clear
public class IndexController extends Controller {

	static BookService service = new BookService();

	public void index() {
		Page<Book> page = service.paginate(1, 12);
		setAttr("page", page);
		render("index.html");
	}

}

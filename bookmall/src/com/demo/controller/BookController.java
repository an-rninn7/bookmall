package com.demo.controller;

import com.demo.common.SessionInterceptor;
import com.demo.common.model.Book;
import com.demo.common.model.Comments;
import com.demo.service.BookService;
import com.demo.service.CommentsService;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class BookController extends Controller {

	static BookService service = new BookService();
	static CommentsService commentsService = new CommentsService();

	public void index() {
		Page<Book> page = service.paginate(1, 10);
		setAttr("page", page);
		render("list.html");
	}

	@Clear(SessionInterceptor.class)
	public void detail() {
		Integer bid = getParaToInt();
		Book book = Book.dao.findById(bid);
		Page<Comments> page = commentsService.getByBookId(1, 10, book.getId());
		setAttr("page", page);
		setAttr("book", book);
		render("detail.html");
	}

	public void add() {
		render("add.html");
	}

	public void doadd() {
		getFile();
		Book book = getBean(Book.class, "", true);
		String priceType = service.getPriceType(book.getPrice());
		book.setPriceType(priceType);
		book.save();
		redirect("/book");
	}

	public void edit() {
		Integer bid = getParaToInt();
		Book book = Book.dao.findById(bid);
		setAttr("book", book);
		render("edit.html");
	}

	public void doedit() {
		getFile();
		Book book = getBean(Book.class, "", true);
		String priceType = service.getPriceType(book.getPrice());
		book.setPriceType(priceType);
		book.update();
		redirect("/book");
	}

	public void delete() {
		Integer bid = getParaToInt();
		Book.dao.deleteById(bid);
		renderJson();
	}

}

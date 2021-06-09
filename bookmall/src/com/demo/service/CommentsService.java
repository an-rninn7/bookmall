package com.demo.service;

import com.demo.common.model.Book;
import com.demo.common.model.Comments;
import com.demo.common.model.User;
import com.jfinal.plugin.activerecord.Page;

public class CommentsService {

	private static final Comments dao = new Comments().dao();

	public Page<Comments> mine(int pageNumber, int pageSize, User suser) {
		Page<Comments> page = dao
				.paginate(pageNumber, pageSize, "select *",
						"from comments where user_id=? order by id desc",
						suser.getId());
		for (Comments comm : page.getList()) {
			comm.setBook(Book.dao.findById(comm.getBookId()));
		}
		return page;
	}

	public Page<Comments> getByBookId(int pageNumber, int pageSize,
			Integer bookId) {
		Page<Comments> page = dao.paginate(pageNumber, pageSize, "select *",
				"from comments where book_id=? and status=1 order by id desc",
				bookId);
		return page;
	}

	public Page<Comments> getAuditList(int pageNumber, int pageSize,
			Integer status) {
		Page<Comments> page = dao.paginate(pageNumber, pageSize, "select *",
				"from comments where status=? order by id desc", status);
		for (Comments comm : page.getList()) {
			comm.setBook(Book.dao.findById(comm.getBookId()));
		}
		return page;
	}

	public void audit(Integer id, int status) {
		Comments comments = dao.findById(id);
		comments.setStatus(status);
		comments.update();
	}

}

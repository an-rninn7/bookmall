package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseShopcart<M extends BaseShopcart<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public M setBookId(java.lang.Integer bookId) {
		set("book_id", bookId);
		return (M)this;
	}

	public java.lang.Integer getBookId() {
		return getInt("book_id");
	}

	public M setPrice(java.lang.Double price) {
		set("price", price);
		return (M)this;
	}

	public java.lang.Double getPrice() {
		return getDouble("price");
	}

	public M setNum(java.lang.Integer num) {
		set("num", num);
		return (M)this;
	}

	public java.lang.Integer getNum() {
		return getInt("num");
	}

	public M setTotal(java.lang.Double total) {
		set("total", total);
		return (M)this;
	}

	public java.lang.Double getTotal() {
		return getDouble("total");
	}

}

package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSecondTrade<M extends BaseSecondTrade<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.String getId() {
		return getStr("id");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public M setOrderItemId(java.lang.String orderItemId) {
		set("order_item_id", orderItemId);
		return (M)this;
	}

	public java.lang.String getOrderItemId() {
		return getStr("order_item_id");
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

	public M setSoldTotal(java.lang.Double soldTotal) {
		set("sold_total", soldTotal);
		return (M)this;
	}

	public java.lang.Double getSoldTotal() {
		return getDouble("sold_total");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}

	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public M setBuyerId(java.lang.Integer buyerId) {
		set("buyer_id", buyerId);
		return (M)this;
	}

	public java.lang.Integer getBuyerId() {
		return getInt("buyer_id");
	}

	public M setBuyerName(java.lang.String buyerName) {
		set("buyer_name", buyerName);
		return (M)this;
	}

	public java.lang.String getBuyerName() {
		return getStr("buyer_name");
	}

	public M setBuyerMobile(java.lang.String buyerMobile) {
		set("buyer_mobile", buyerMobile);
		return (M)this;
	}

	public java.lang.String getBuyerMobile() {
		return getStr("buyer_mobile");
	}

	public M setBuyerAddress(java.lang.String buyerAddress) {
		set("buyer_address", buyerAddress);
		return (M)this;
	}

	public java.lang.String getBuyerAddress() {
		return getStr("buyer_address");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setCommented(java.lang.Integer commented) {
		set("commented", commented);
		return (M)this;
	}

	public java.lang.Integer getCommented() {
		return getInt("commented");
	}

}

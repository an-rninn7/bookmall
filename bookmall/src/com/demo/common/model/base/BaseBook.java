package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBook<M extends BaseBook<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public java.lang.String getName() {
		return getStr("name");
	}

	public M setSummary(java.lang.String summary) {
		set("summary", summary);
		return (M)this;
	}

	public java.lang.String getSummary() {
		return getStr("summary");
	}

	public M setCategory(java.lang.String category) {
		set("category", category);
		return (M)this;
	}

	public java.lang.String getCategory() {
		return getStr("category");
	}

	public M setAuthor(java.lang.String author) {
		set("author", author);
		return (M)this;
	}

	public java.lang.String getAuthor() {
		return getStr("author");
	}

	public M setPublisher(java.lang.String publisher) {
		set("publisher", publisher);
		return (M)this;
	}

	public java.lang.String getPublisher() {
		return getStr("publisher");
	}

	public M setPrice(java.lang.Double price) {
		set("price", price);
		return (M)this;
	}

	public java.lang.Double getPrice() {
		return getDouble("price");
	}

	public M setPriceType(java.lang.String priceType) {
		set("price_type", priceType);
		return (M)this;
	}

	public java.lang.String getPriceType() {
		return getStr("price_type");
	}

	public M setInventory(java.lang.Integer inventory) {
		set("inventory", inventory);
		return (M)this;
	}

	public java.lang.Integer getInventory() {
		return getInt("inventory");
	}

	public M setPubDate(java.lang.String pubDate) {
		set("pub_date", pubDate);
		return (M)this;
	}

	public java.lang.String getPubDate() {
		return getStr("pub_date");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}

	public java.lang.String getDescription() {
		return getStr("description");
	}

	public M setBookImg(java.lang.String bookImg) {
		set("book_img", bookImg);
		return (M)this;
	}

	public java.lang.String getBookImg() {
		return getStr("book_img");
	}

	public M setDescImg(java.lang.String descImg) {
		set("desc_img", descImg);
		return (M)this;
	}

	public java.lang.String getDescImg() {
		return getStr("desc_img");
	}

}

package com.demo.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("book", "id", Book.class);
		arp.addMapping("comments", "id", Comments.class);
		arp.addMapping("exchange_item", "id", ExchangeItem.class);
		arp.addMapping("exchange_trade", "id", ExchangeTrade.class);
		arp.addMapping("order", "id", Order.class);
		arp.addMapping("order_item", "id", OrderItem.class);
		arp.addMapping("second_trade", "id", SecondTrade.class);
		arp.addMapping("shopcart", "id", Shopcart.class);
		arp.addMapping("user", "id", User.class);
	}
}


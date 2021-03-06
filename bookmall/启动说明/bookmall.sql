-- CREATE DATABASE bookmall;

USE bookmall;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) NOT NULL,
	`email` varchar(64) NOT NULL,
  `mobile` varchar(32) NOT NULL,
	`type` int NOT NULL default 0 comment '0:user,1:admin',
	`password` varchar(64) NOT NULL,
	`blance` double NOT NULL default 1000,
	`address` varchar(200) NOT NULL,
	`photo` varchar(200) NOT NULL,
	`status` int NOT NULL comment '1：待审核，2：审核通过，3：审核拒绝' ,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) NOT NULL,
	`summary` varchar(512) NOT NULL,
	`category` varchar(64) NOT NULL,
  `author` varchar(32) NOT NULL,
	`publisher` varchar(64) NOT NULL,
	`price` double NOT NULL default 0,
	`price_type` varchar(32) NOT NULL comment 'A(0,29),B(30,49),C(50,69),D(70,)',
	`inventory` int NOT NULL default 20,
	`pub_date` varchar(32) NOT NULL,
	`description` mediumtext NOT NULL,
 	`book_img` varchar(64) NULL,
 	`desc_img` varchar(64) NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `id` varchar(32) NOT NULL,
	`user_id` int(11) NOT NULL,
	`total` double NOT NULL default 0,
	`create_time` datetime NOT NULL,
	`pay_time` datetime  NULL,
	`delivery_time` datetime  NULL,
	`name` varchar(64) NOT NULL,
	`payway` varchar(32) NOT NULL,
	`invoice` varchar(32) NULL,
  	`mobile` varchar(32) NOT NULL,
	`address` varchar(200) NOT NULL,
 	`status` int NOT NULL comment '1：待买家付款:2：待发货,3：待买家确认收货,4：交易完成，5：交易关闭' ,
	`commented` int NOT NULL default 0 comment '0:未评价,1:已评价',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_item` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
	`book_id` int(11) NOT NULL,
	`price` double NOT NULL default 0,
	`num` int NOT NULL default 0,
	`total` double NOT NULL default 0,
	`price_type` varchar(32) NOT NULL comment 'A(0,29),B(30,49),C(50,69),D(70,)',
	`status` int NOT NULL default 0 comment '0:nomarl,1:sold,2:exchange' ,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `second_trade` (
  `id` varchar(32) NOT NULL,
	`user_id` int(11) NOT NULL,
  `order_item_id` varchar(32) NOT NULL,
	`book_id` int(11) NOT NULL,
	`price` double NOT NULL default 0,
	`num` int NOT NULL default 0,
	`total` double NOT NULL default 0,
	`sold_total` double NOT NULL default 0,
	`status` int NOT NULL default 0 comment '0:待审核,1:交易中,2:待发货,3：待买家确认收货,4:交易完成，5：交易关闭' ,
	`buyer_id` int(11) NOT NULL,
	`buyer_name` varchar(32) NULL,
	`buyer_mobile` varchar(32) NULL,
	`buyer_address` varchar(200) NOT NULL,
	`create_time` datetime NOT NULL,
	`commented` int NOT NULL default 0 comment '0:未评价,1:已评价',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `exchange_trade` (
  `id` varchar(32) NOT NULL,
	`user_id` int(11) NOT NULL,
	`user_exchange_item_id` int(11) NULL,
	`status` int NOT NULL default 0 comment '0:待审核,1:交易中,4:交易完成，5：交易关闭' ,
	`buyer_id` int(11) NOT NULL,
	`buyer_exchange_item_id` int(11) NULL,
	`create_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `exchange_item` (
  `id` int(11) NOT NULL auto_increment,
	`name` varchar(32) NULL,
	`mobile` varchar(32) NULL,
	`address` varchar(200) NULL,
	`commented` int NOT NULL default 0 comment '0:未评价,1:已评价',
  `order_item_id` varchar(32) NOT NULL,
	`book_id` int(11) NOT NULL,
	`price` double NOT NULL default 0,
	`num` int NOT NULL default 0,
	`total` double NOT NULL default 0,
	`price_type` varchar(32) NOT NULL comment 'A(0,29),B(30,49),C(50,69),D(70,)',
	`status` int NOT NULL default 0 comment '1:交易中,2:待发货,3：待买家确认收货,4:已收货' ,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
  `id` int(11) NOT NULL auto_increment,
	`book_id` int(11) NOT NULL,
	`order_id` varchar(32) NOT NULL,
 	`user_id` int(11) NOT NULL,
	`type` int NOT NULL default 0 comment '0:购书,1:购二手书,2:换书',
  `grade` int NOT NULL default 0 comment '1:非常不满,2:不满意,3:一般，4：满意，5：非常满意' ,
	`content` varchar(200) NULL,
	`create_time` datetime NOT NULL,
	`status` int NOT NULL default 0 comment '0:待审核,1:审核通过,2:审核不通过' ,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shopcart` (
  `id` int(11) NOT NULL auto_increment,
 	`user_id` int(11) NOT NULL,
	`book_id` int(11) NOT NULL,
	`price` double NOT NULL default 0,
	`num` int NOT NULL default 0,
	`total` double NOT NULL default 0,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
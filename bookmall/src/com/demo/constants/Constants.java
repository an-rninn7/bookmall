package com.demo.constants;

public interface Constants {
	String error_code_success = "0";
	String error_code_error = "1000";
	String error_code_email_exists = "1001";
	String error_code_inventory_insufficient = "2001";
	String error_code_blance_insufficient = "3001";

	String SESSION_LOGIN_USER = "session_user";

	int order_status_payment = 1;
	int order_status_delivery = 2;
	int order_status_receiving = 3;
	int order_status_success = 4;
	int order_status_close = 5;

	int order_comments_yes = 1;

	int order_item_status_nomarl = 1;
	int order_item_status_sold = 2;
	int order_item_status_exchange = 3;

	int user_status_auditing = 1;
	int user_status_pass = 2;
	int user_status_reject = 3;

	int user_type_admin = 1;

	int comments_type_order = 0;
	int comments_type_second = 1;
	int comments_type_exchange = 2;

	int comments_status_auditing = 0;
	int comments_status_pass = 1;
	int comments_status_reject = 2;

}

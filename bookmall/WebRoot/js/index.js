$(document).ready(function(){
	
	$('#addToCartBtn').click(function(){
		var bid = $('#bookid').val();
		var num = $('#booknum').val();
		doPost(getContextPath()+'/cart/add/'+bid+'-'+num, null, function(data){
			if (data.errorCode == '0') {
				showMsg('加入购物车成功。',true);
			} else {
				showMsg('加入购物车失败，请重新操作。',true);
			}
		});
	});
	
	$('button[id^=delFromCartBtn]').click(function(){
		var id = this.id.split('-')[1];
		confirmMsg('您确定要删除图书吗？',function(){
			doPost(getContextPath()+'/cart/remove/'+id, null, function(){
				window.location.reload();
			});
		});
	});
	
	$('#subOrderBtn').click(function(){
		if (isValidateForm()) {
			doPost(getContextPath()+'/order/submit', $('form:first').serialize(), function(data){
				if (data.errorCode == '0') {
					showMsg('提交订单成功。',true,function(){
						window.location.href = getContextPath()+'/order/list';
					});
				}else if (data.errorCode == '2001') {
					showMsg('提交订单失败，库库不足。',false);
				} else {
					showMsg('提交订单失败，请重新操作。',false);
				}
			});
		}
	});
});
$(document).ready(function(){
	
	$('button[id^=payBtn]').click(function(){
		var id = this.id.split('-')[1];
		confirmMsg('确定支付订单吗？',function(){
			doPost(getContextPath()+'/order/pay/'+id, null, function(data){
				if (data.errorCode == '0') {
					showMsg('支付订单成功。',true,function(){
						window.location.reload();
					});
				}else if (data.errorCode == '3001') {
					showMsg('支付订单失败，余额不足。',false);
				} else {
					showMsg('支付订单失败，请重新操作。',false);
				}
			});
		});
	});
	
	$('button[id^=recBtn]').click(function(){
		var id = this.id.split('-')[1];
		confirmMsg('确定已收到货吗？',function(){
			doPost(getContextPath()+'/order/received/'+id, null, function(){
				window.location.reload();
			});
		});
	});
	
	$('#subCommentsBtn').click(function(){
		doPost(getContextPath()+'/comments/submit', $('form:first').serialize(), function(data){
			if (data.errorCode == '0') {
				showMsg('评价成功。',true,function(){
					window.location.href = getContextPath()+'/order/list';
				});
			} else {
				showMsg('评价失败，请重新操作。',false);
			}
		});
	});
});
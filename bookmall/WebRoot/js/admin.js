$(document).ready(function(){
	
	$('button[id^=auidtBtn]').click(function(){
		var uid = this.id.split('-')[1];
		var stat = this.id.split('-')[2];
		confirmMsg('确定提交用户审核操作？',function(){
			doPost(getContextPath()+'/user/doaudit/'+uid+'-'+stat, null, function(){
				window.location.reload();
			});
		});
	});
	
	$('button[id^=delbookBtn]').click(function(){
		var id = this.id.split('-')[1];
		confirmMsg('确定删除图书操作吗？',function(){
			doPost(getContextPath()+'/book/delete/'+id, null, function(){
				window.location.reload();
			});
		});
	});
	
	$('button[id^=deliveryBtn]').click(function(){
		var id = this.id.split('-')[1];
		confirmMsg('确定已发货吗？',function(){
			doPost(getContextPath()+'/order/delivery/'+id, null, function(){
				window.location.reload();
			});
		});
	});
	
});
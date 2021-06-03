var validateSelector = 'input[required],select[required],textarea[required]';

$(document).ready(function(){
	
	$('#resetBtn').click(function(){
		$(this).parents('form').find('input').val(null);
	});
	
	$(document).keydown(function(event){ 
		if (event.keyCode == 13) {
			$('#subBtn').click();
		}
	});
	
	$(document).ajaxComplete(function(event,response){
		try{
			var result = $.parseJSON(response.responseText);
			if (result.errorCode == '10001') {
				showMsg('登录已失效，请重新登录。',false);
				$('#_msgModal').on('hidden.bs.modal', function (e) {
					window.location.href = getContextPath() + '/login';
				});
			}
		} catch(e){}
	});
	
	var xtypeReg = {'email':/^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/,
					'phone':/^\d{3,4}-?\d{7,8}|\d{11}$/,
					'idcard':/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/};
	$(validateSelector).blur(function(){
		if (this.value == '') {
			$(this).parent().addClass('has-error').attr('title','此项目为必填项。');
		} else {
			var xtype = $(this).attr('xtype');
			if (xtype != undefined && !xtypeReg[xtype].test(this.value)) {
				$(this).parent().addClass('has-error').attr('title','输入格式有误，请重新输入。');
			} else {
				$(this).parent().removeClass('has-error');
			}
		}
	});
	
	$('#logout').click(function(){
    	confirmMsg(_LOCALEMSG.getBaseMsg('logout'),function(){
    		window.location.href = getContextPath()+'/login';
    	});
    	return false;
    });
});

function isValidateForm(){
	$(validateSelector).blur();
	if ($('.has-error').length > 0) {
		showMsg(_LOCALEMSG.getBaseMsg('input_error'));
		return false;
	}
	return true;
}

function getContextPath() {
	var pathName = window.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0,index+1);
	return result;
}

function doPost(url,data,callback,type) {
	type = type || 'json';
	$.post(url,data,callback,type);
}

function doGet(url,data,callback,type) {
	type = type || 'json';
	$.get(url,data,callback,type);
}



function showMsg(msg,isSuccess,callback){
	if ($('#_msgModal').length == 0) {
		var html = '<div class="modal fade" id="_msgModal" tabindex="-1" role="dialog" aria-labelledby="_msgModalLabel" aria-hidden="true">'
			+'<div class="modal-dialog modal-sm">'
			+	'<div class="modal-content">'
			+		'<div class="modal-header">'
			+			'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+			'<h4 class="modal-title" id="_msgModalLabel">'
			+				'<span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;信息提示框'
			+			'</h4>'
			+		'</div>'
			+		'<div class="modal-body">'
			+			'<p class="text-success" id="_msgModal-success" style="font-size: 15px;"></p>'
			+			'<p class="text-danger" id="_msgModal-danger" style="font-size: 15px;"></p>'
			+		'</div>'
			+		'<div class="modal-footer">'
			+			'<button type="button" class="btn btn-default" data-dismiss="modal">关闭'
			+			'</button>'
			+		'</div>'
			+	'</div><!-- /.modal-content -->'
			+'</div><!-- /.modal -->'
			+'</div>';
		$('body').append(html);
	}
	if (isSuccess) {
		$('#_msgModal-success').html('<span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;'+msg);
		$('#_msgModal-danger').html(null);
	} else {
		$('#_msgModal-success').html(null);
		$('#_msgModal-danger').html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;'+msg);
	}
	$('#_msgModal').modal('show');
	if (callback) {
		$('#_msgModal').on('hidden.bs.modal', callback);
	}
}


function confirmMsg(msg,callback){
	if ($('#_confirmModal').length == 0) {
		var html = '<div class="modal fade" id="_confirmModal" tabindex="-1" role="dialog" aria-labelledby="_confirmModalLabel" aria-hidden="true">'
			+'<div class="modal-dialog modal-sm">'
			+	'<div class="modal-content">'
			+		'<div class="modal-header">'
			+			'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+			'<h4 class="modal-title" id="_confirmModalLabel">'
			+				'<span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;操作提示框'
			+			'</h4>'
			+		'</div>'
			+		'<div class="modal-body">'
			+			'<p class="text-primary" id="_confirmModal-success" style="font-size: 15px;"></p>'
			+		'</div>'
			+		'<div class="modal-footer">'
			+			'<button type="button" class="btn btn-default waves-effect" id="_confirmModal_okBtn">确认</button>'
			+			'<button type="button" class="btn btn-inverse waves-effect waves-light" data-dismiss="modal" id="_confirmModal_cancelBtn">关闭'
			+			'</button>'
			+		'</div>'
			+	'</div><!-- /.modal-content -->'
			+'</div><!-- /.modal -->'
			+'</div>';
		$('body').append(html);
		$('#_confirmModal_okBtn').click(function(){
			$('#_confirmModal').modal('hide');
			callback();
		});
	}
	$('#_confirmModal-success').html('<span class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;'+msg);
	$('#_confirmModal').modal('show');
}

function mockFormSubmit(params){
	var form = $('<form />', {action : params.url, method:"post", style:"display:none;"}).appendTo('body');	
	$.each(params, function(k, v) {
	      if ( k != "url" ){
	    	  form.append('<input type="hidden" name="' + k +'" value="' + v +'" />');
	      }
	});
	form.submit();
}

function genPaginationFooter(totalCount,currentPage,pageSize, callback,hideLastPage){   
    
    var begin = 1,
        end = 5;
    
    var maxPage = parseInt((totalCount - 1)/pageSize + 1);
    var pageSummray = "";
    
    if(totalCount == 0 || maxPage <= 1 ){
        return "";
    }
    if(currentPage <= 3){
        begin = 1;
    }else if(maxPage - currentPage <= 2 ){
        begin = maxPage - 4;
    }else{
        begin = currentPage - 2;
    }
    if(begin < 1){
        begin = 1;
    }
    if(maxPage > begin + 4){
        end = begin + 4;
    }else{
        end = maxPage;
    }
  
    var htmlBuffer = new StringBuffer();
    htmlBuffer.append("<li><a href='javascript:void(0);' onclick='", callback, "(", 1, ");'>");
    htmlBuffer.append('<span aria-hidden="true">首页</span>');
    htmlBuffer.append("</a></li>");
    
    if(currentPage == 1){
        htmlBuffer.append("<li class='disabled'><a page> <span aria-hidden='true'>上一页</span> </a></li>");
    }else{
        htmlBuffer.append("<li><a href='javascript:void(0);' onclick='", callback, "(", currentPage - 1, ");'> <span aria-hidden='true'>上一页</span> </a></li>");
    }    
      
    for (var i = begin; i <= end; i++){
        if(i == currentPage){
            htmlBuffer.append("<li  class='active'><a page href='javascript:void(0);'>");
        }else{
            htmlBuffer.append("<li><a href='javascript:void(0);' onclick='", callback, "(", i, ");'>");
        }
        htmlBuffer.append(i);
        htmlBuffer.append("</a></li>");
    }
    
    if(currentPage == maxPage){
        htmlBuffer.append("<li class='disabled'><a page> <span aria-hidden='true'>下一页</span> </a></li>");
    }else{
        htmlBuffer.append("<li><a href='javascript:void(0);' onclick='", callback, "(", currentPage + 1, ");'><span aria-hidden='true'>下一页</span></a></li>");
    }
    //hide the button when the search
    if (!hideLastPage) {
    	htmlBuffer.append("<li><a href='javascript:void(0);' onclick='", callback, "(", maxPage, ");'>");
    	htmlBuffer.append("<span aria-hidden='true'>末页</span>");
    	htmlBuffer.append("</a></li>");
    }
    
    return pageSummray + htmlBuffer.toString();
}


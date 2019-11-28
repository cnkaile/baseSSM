$('#login').click(function () {
    $('.tip-msg').remove();// 解决重复点击的情况
    let username = $('#username').val();
    let password = $('#password').val();
    if(username==''){
        tipMsg('请输入用户名');
    }else if(password==''){
        tipMsg('请输入密码');
    }else {
    	$.ajax({
    		url : '/pwd/confirm',
    		type : 'post',
    		data : {
    			pwd : password,
    			loginName : username,
    			brand:"warhorse"
    		},
    		dataType : 'json',
    		success : function(response) {
    			if(response.code == 200){
    				if(response.confirmUser == 1){
    					let userId = response.userId;
    					window.location.href = '/skip/html_index?userId=' + userId;
    				}else{	//
    					tipMsg('密码或登陆名错误');
    				}
    			}else if(response.code == 404){
    				tipMsg(response.info);
    			}else if (response.code == 302){
    				tipMsg("登陆过期,请重新登陆");
    				let url = "/skip/" + response.skipURI + "?returnUrl=" + encodeURIComponent(window.location.href);
    				window.location.replace(url);
    			}else{
    				tipMsg("未知错误,请稍后再试");
    			}
    		}
    	});
    }
});
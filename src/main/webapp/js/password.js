
$(function(){
	let userId = urlParam("userId");
	let pwdReg = /^[A-Za-z0-9_]+$/;

	$('#submit').click(function() {
		let oldPwd = $('#old-word').val();
		let newPwd = $('#new-word').val();
		if (oldPwd == '' || !pwdReg.test(oldPwd)) {
			tipMsg('请查看旧密码是否输入正确');
		} else if (newPwd == '' || !pwdReg.test(newPwd)) {
			tipMsg('新密码是否输入正确');
		} else {
			// 请求
			$.ajax({
				url : '/pwd/modify',
				type : 'post',
				data : {
					oldPwd : oldPwd,
					newPwd : newPwd,
					userId : userId
				},
				dataType : 'json',
				success : function(data) {
					if(data.code == 200){
						let loginUrl = "/skip/html_login";
						window.location.replace(loginUrl);
					}else if(data.code == 404){
						tipWindow(data.info);
					}else if (data.code == 302){
						tipWindow("登陆过期,请重新登陆");
						let url = "/skip/" + data.skipURI + "?returnUrl=" + encodeURIComponent(window.location.href);
						window.location.replace(url);
					}else{
						tipWindow("出现未知错误,请稍后再试");
					}
				}
			});
		}
	});
	
})

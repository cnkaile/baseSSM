$(function(){
	let userId = urlParam("userId");
	$.ajax({
		type:'post',
		url:'/info/myBatch',
		data:{
			userId : userId,
			page : 0,
			pageSize : 10
		},
		success:function (response) {
			if(response.code == 200){
				if(response.hasData == 1){
					let data = response.page.content;
					for (let i=0;i<data.length;i++){
						let resultUrl = "/skip/html_results?userId=" + userId + "&batchNo=" + data[i].batchno;
						$('.record').append('<a href="' + resultUrl + '"><div class="record-box"><div class="record-left">'+ data[i].batchno +'</div><div class="record-right">'+ data[i].commtimeStrYMD +'</div></div></a>');
					}
				}
			} else if(response.code == 404){
				tipWindow(reasponse.info);
			}else if (response.code == 302){
				tipWindow("登陆过期,请重新登陆");
				let url = "/skip/" + response.skipURI + "?returnUrl=" + encodeURIComponent(window.location.href);
				window.location.replace(url);
			}else{
				tipWindow("未知错误,请稍后再试");
			}
		}
	});
})

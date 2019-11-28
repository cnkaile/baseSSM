
/**
 * 刷新盖类型数量
 * @param data	该类型
 */
function capType(data, sign) {
	if(sign == 0){
		for(let i=0;i<data.length;i++){
			var type = '<p class="msg msg-border">' + data[i].showName + '&nbsp;:<span>&nbsp;'  + data[i].num + '</span> </p>';
			$('.main-content').append(type);
		}
	}else {
		for(let i=0;i<data.length;i++){
			var type = '<p class="msg msg-border">' + data[i].showName + '&nbsp;:<span>&nbsp;'  + data[i].num + '</span> </p>';
			$('.main-bottom').append(type);
		}
		$('.main-bottom').append('<a href="#" id="showDetails">二维码不存在明细</a>');
	}
}

$(function () {
	let userId = urlParam("userId");
	let batchNo = urlParam("batchNo");
	
	$('#showIndex').attr('href',"/skip/html_index?userId=" + userId);
	
	$.ajax({
		type:'post',
		url:'/info/batchInfo',
		data:{
			 batchNo:batchNo,
			 userId:userId
		},
		success:function (response) {
			if(response.code == 200){
				let batch = response.batchInfo;
				$('.batch-nub').text(batch.batchno);		//批次号
				$('.complete-nub').text(batch.totalnum);	//总
				$('.start-time').text(batch.createTimeStr);	//开始时间
				$('.end-time').text(batch.commtimeStr);		//提交结束时间
				$('.effective-nub').text(batch.effectivenum);	//有效数量
				$('.invalid-nub').text(batch.invalidnum);		//无效数量
				capType(batch.effectiveList, 0);
				capType(batch.invalidList, 1);
				let k = response.k;
				let t = response.t;
				$('#showDetails').attr('href',"/skip/html_details?userId=" + userId + "&batchNo=" + batchNo + "&t=" + t + "&k=" + k);
			}else if (response.code == 404){
				tipWindow(response.info);
			}else if (response.code == 302){
				tipWindow("登陆过期,请重新登陆");
				let url = "/skip/" + response.skipURI + "?returnUrl=" + encodeURIComponent(window.location.href);
				window.location.replace(url);
			}else{
				tipWindow("未知错误,请稍后再试");
			}

		}
	})
});
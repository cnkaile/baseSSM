/**
 * 下一页：加载更多点击事件
 * @param showStatus
 * @param userId
 * @param batchNo
 * @param errType
 * @param page
 * @param pageSize
 * @returns
 */
function clickMore(showStatus, userId, batchNo, errType, page, pageSize){
	if(showStatus){
        $('.more').show()
        // 加载更多
        $('.more').one('click',function () {
        	load(userId, batchNo, errType, page, pageSize)
        });
    }else {
        $('.more').hide();
    }
}
/**
 * 每一条设置点击
 * @returns
 */
function errDetailClickEven() {
	$('.info-code').click(function() {
		let msg = $(this).text();
		tipWindow(msg);
	})
}
/**
 * 请求并加载数据
 * 
 * @param userId
 * @param batchNo
 * @returns
 */
function load(userId, batchNo, errType, page, pageSize){
	$.ajax({
		type:'post',
		url:'/info/errorInfo',
		data:{
			batchNo:batchNo,
			userId:userId,
			errType:errType,
			page:page,
			pageSize:pageSize
		},
		success:function (response) {
			if(response.code == 200){
				console.log(response);
				let infoPage = response.errInfoPage;
				if(infoPage.first && response.hasData == 1){ // 有数据页面
					$('.content').append($('#show-msg')[0].innerHTML);
				}else if(infoPage.first && response.hasData != 1){ // 无数据页面
					$('.content').append($('#no-msg')[0].innerHTML);
				}
				if(response.hasData == 1){
					let data = infoPage.content;			//数据
					let total = infoPage.totalElements;		//总数
					$('.on-scan').text(total);
					// 遍历未查到的二维码
					for (let i = 0; i < data.length; i++){
						$('#info').append('<li><div class="info-detail">'+((page * pageSize) + (i+1))+'</div><div class="info-detail info-code">'+data[i].scancontent+'</div></li>')
					}
					errDetailClickEven();
//					infoPage.first : 是否是第一页
//					infoPage.last : 是否是最后一页
					clickMore(!(infoPage.last), userId, batchNo, errType, (page + 1), pageSize);
				}
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
}


$(function () {
	let userId = urlParam("userId");
	let batchNo = urlParam("batchNo");
	let page = 0;
	const pageSize = 10;
	const errType = "noexist";
	
	load(userId, batchNo, errType, page, pageSize);
	
   
});


/**
 * 初始化微信扫码插件
 */
function initWechatScan(appId, timestamp, nonceStr, signature){
	wx.config({
		debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来
		appId : appId, // 必填，公众号的唯一标识
		timestamp : timestamp, // 必填，生成签名的时间戳
		nonceStr : nonceStr, // 必填，生成签名的随机串
		signature : signature,// 必填，签名，见附录1
		jsApiList: [
            'scanQRCode'
        ]
	});
	wx.ready(function () {
		//tipWindow("ready--->");
	});
	wx.error(function(res) {
		//tipWindow("error--->" + res.errMsg);
	});
}

/**
 * 刷新盖类型数量
 * @param data	该类型
 */
function capType(data) {
	$('.scan-results').empty();
    for(let i=0;i<data.length;i++){
    	var type = "<p>" + data[i].showName + "</p><p>有效数：" + data[i].num + "</p>";
    	$('.scan-results').prepend(type);
    }
}

/**
 * 刷新大框数量
 * @param time
 */
function showNub(time){
    let data = time.toString().split('');
    if(time<10){
        $('.count-nub:nth-child(1)').text(0);
        $('.count-nub:nth-child(2)').text(0);
        $('.count-nub:nth-child(3)').text(0);
        $('.count-nub:nth-child(4)').text(data);
    }else if(time<100){
        $('.count-nub:nth-child(1)').text(0);
        $('.count-nub:nth-child(2)').text(0);
        $('.count-nub:nth-child(3)').text(data[0]);
        $('.count-nub:nth-child(4)').text(data[1]);
    }else if(time<1000){
        $('.count-nub:nth-child(1)').text(0);
        $('.count-nub:nth-child(2)').text(data[0]);
        $('.count-nub:nth-child(3)').text(data[1]);
        $('.count-nub:nth-child(4)').text(data[2]);
    }else if (time<10000){
        $('.count-nub:nth-child(1)').text(data[0]);
        $('.count-nub:nth-child(2)').text(data[1]);
        $('.count-nub:nth-child(3)').text(data[2]);
        $('.count-nub:nth-child(4)').text(data[3]);
    }else if(time>9999){
        tipWindow('页面显示已到达上限,以后台记录为准,请尽快提交本批次。\n有效数：' + time);
    }
}

/**
 * 刷新批次信息
 * @param batch	批次
 */
function refreshBatchInfo(batch){
	$(".scan-msg > span").text(batch.batchno);						//批次号
	$('.scan-data:nth-child(1) > span').text(batch.totalnum);		//已清点数量
	$('.scan-data:nth-child(2) > span').text(batch.effectivenum);	//有效数量
	$('.scan-data:nth-child(3) > span').text(batch.invalidnum);		//无效数量
	showNub(batch.effectivenum);									//大框有效数量
	capType(batch.effectiveList);									//有效类型
}

/**
 * 初始化页面请求
 * @param pageUrl
 * @param userId
 */
function initScan(pageUrl, userId){
	
	$.ajax({
		url : '/scan/initScanPage',
		type : 'post',
		data : {
			pageUrl : pageUrl,
			userId : userId
		},
		dataType : 'json',
		success : function(data) {
			if(data.code == 200){
				//初始化批次常量
				var batch = data.batchInfo;
				refreshBatchInfo(batch);
				//初始化微信
				initWechatScan(data.appId, data.timestamp, data.nonceStr, data.signature);
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
};

/**
 * 提交每次扫码请求
 */
function commScan(batchNo, userId, info, wuxiao, youxiao){
	$.ajax({
        type:'post',
        url:'/scan/submitScanInfo2',
        data:{
        	scanInfo:info,
        	batchNo:batchNo,
        	userId:userId
        },
        success:function (response) {
        	if(response.code == 200){
        		if(response.whether == 0){	//无效
        			wuxiao.play();   //无效播放
        		}else{	//有效
        			youxiao.play();  //扫码成功语音
        		}
        		refreshBatchInfo(response.batchInfo);
        		//重新调用
                startScan(batchNo, userId, wuxiao, youxiao);
        	}else if(response.code == 404 || response.code == 201){
        		tipWindow(response.info);
        		$('.box-bottom').click(function () {
	        		if(response.code == 201){
	        			wechatScan(batchNo, userId, wuxiao, youxiao);
//	        			startScan(batchNo, userId, wuxiao, youxiao);
	        		}
        	    })
        	}else if (response.code == 302){
				tipWindow("登陆过期,请重新登陆");
				let url = "/skip/" + response.skipURI + "?returnUrl=" + encodeURIComponent(window.location.href);
				window.location.replace(url);
			}else{
        		tipWindow("未知异常,请稍后再试");
        	}
        },
        error:function () {
        	tipWindow("请重试");
//            wuxiao.play();   //无效播放
        }
    })
}
/**
 * 调用微信函数,发起扫描
 * @param wuxiao	声音
 * @param youxiao
 */
function wechatScan(batchNo, userId, wuxiao, youxiao){
    wx.scanQRCode({
        needResult: 1,
        desc: '扫码返回结果',
        success: function (res) {
        	//res类型：{"resultStr":"http://67b8.cn/sjdfoijeof","errMsg":"scanQRCode:ok"}
            let info = JSON.stringify(res);
            commScan(batchNo, userId, info, wuxiao, youxiao);
        }
    });
}

function startScan(batchNo, userId, wuxiao, youxiao){
	setTimeout(function(){
		wechatScan(batchNo, userId, wuxiao, youxiao);
	},500);
}

/**
 * 提交保存该批次
 * @returns
 */
function commBatch(batchNo, userId){
	$.ajax({
        type:'post',
        url:'/scan/submitBatch2',
        data:{
        	batchNo:batchNo,
        	userId:userId
        },
        success:function (response) {
        	if(response.code == 200){
        		window.location = "/skip/html_results?userId=" + userId + "&batchNo=" + batchNo; 
        	}else if(response.code == 404){
        		tipWindow(response.info);
        	}else if (response.code == 302){
				tipWindow("登陆过期,请重新登陆");
				let url = "/skip/" + response.skipURI + "?returnUrl=" + encodeURIComponent(window.location.href);
				window.location.replace(url);
			}else{
        		tipWindow("出现未知错误, 请重试");
        	}
        }
    })
}

$(function(){
	let userId = urlParam("userId");
	const wuxiao = document.getElementById('wuxiao');
	const youxiao = document.getElementById('youxiao');
	initScan(window.location.href, userId);
	//mine url
	let mineUrl = "/skip/html_mine?userId=" + userId;
	$('.my-info').attr('href', mineUrl);
	
	//点击事件
	document.querySelector('#startScan').onclick = function(){
		alert("更新4");
		let batchNo = $(".scan-msg > span").text();
		wechatScan(batchNo, userId, wuxiao, youxiao);
//		startScan(batchNo,userId,wuxiao, youxiao);
	}
	
	// 保存清点
	$('#saveBatch').click(function () {
		let batchNo = $(".scan-msg > span").text();
		commBatch(batchNo, userId);
	});
	
})

function tipMsg(msg) {
    $('body').append('<div class="tip-msg"><span>'+msg+'</span></div>');
    setTimeout(function () {
        $('.tip-msg').remove();
    },3000)
}

function tipWindow(data) {
    $('body').append('<div class="bgc"><div class="box"><div class="box-top"><p>'+data+'</p></div><div class="box-bottom">我知道了</div></div></div>');
    $('.box-bottom').click(function () {
        $('.bgc').remove();
    })
}

/**
 * 获取url参数
 * @param name
 */
function urlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
	if (r != null){
		return unescape(r[2]);
	}
    return null;
}

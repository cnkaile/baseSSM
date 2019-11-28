//默认展示0次
let timer = 0;
wx.ready(function () {
    // 9 微信原生接口
    // 9.1.2 扫描二维码并返回结果
    document.querySelector('#qCode').onclick = function () {
        wx.scanQRCode({
            needResult: 1,
            desc: '扫码返回结果',
            success: function (res) {
                // 每扫描成功一次次数加1
                $('span').text(++timer);
            }
        });
    };
});
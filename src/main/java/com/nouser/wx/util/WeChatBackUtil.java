package com.nouser.wx.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nouser.utils.net.HttpClient;
import com.nouser.wx.WechatConfig;

/**
 * 微信授权完成需要的工具
 * 
 * @author lv
 * @date 2018年10月17日
 * @version 1.0
 */
public class WeChatBackUtil {
	
	/**
	 * 102 获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public static String getUserInfo(String accessToken,String openId) throws Exception {
		return HttpClient.httpGet(buildInfoUrl(accessToken,openId));
	}
	/**
	 * 101 构建获取用户信息的URL
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	private static String buildInfoUrl(String accessToken,String openId) {
		StringBuilder infoUrl = new StringBuilder("https://api.weixin.qq.com/sns/userinfo?access_token=")
				.append(accessToken).append("&openid=").append(openId).append("&lang=zh_CN");
		return infoUrl.toString();
	}
	
	/**
	 * 103 解析获取
	 * 003 解析获取到openID和Token
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> parseResult(String result){
		Map<String, Object> map = JSON.parseObject(result);
		return map;
	}
	
	/**
	 * 002 通过code获取openID和Token
	 * 
	 * @param code 用户同意授权后，返回的code，示例：021gpMG627ZIoK0T6yE62kK9H62gpMG1
	 * @return 返回的json数据，示例：{"access_token":"14_nY3...","expires_in":7200,"refresh_token":"14_A0...","openid":"o25j...","scope":"snsapi_userinfo"}
	 * @throws Exception
	 */
	public static String getOpenIdAndToken(String code) throws Exception {
		return HttpClient.httpGet(buildTokenUrl(code));
	}
	
	/**
	 * 001 拼接获取【网页授权access_token】的URL
	 * 
	 * 参照微信开发文档
	 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
	 * 
	 * @param code 用户同意授权后，返回的code
	 * @return 换取网页授权access_token所需要的url
	 */
	private static String buildTokenUrl(String code) {
		StringBuilder access_token_url = new StringBuilder();
		access_token_url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").append(WechatConfig.APP_ID)
				.append("&secret=").append(WechatConfig.APP_SECRET).append("&code=").append(code)
				.append("&grant_type=authorization_code");
		return access_token_url.toString();
	}

}

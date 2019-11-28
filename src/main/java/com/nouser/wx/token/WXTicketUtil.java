package com.nouser.wx.token;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.nouser.utils.net.HttpClient;

public class WXTicketUtil {

	private static Logger logger = LoggerFactory.getLogger(WXTicketUtil.class);

	private static final String ticketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

	/**
	 * 通过AccessToken 获取Ticket
	 * 
	 * { "errcode":0, "errmsg":"ok",
	 * "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
	 * "expires_in":7200 }
	 * 
	 * @param access_token
	 * @return
	 */
	public static Map<String, Object> getWXTicket(String access_token) {
		if (access_token == null) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder(ticketURL).append("?access_token=").append(access_token)
				.append("&type=jsapi");
		try {
			String result = HttpClient.httpGet(sBuilder.toString());
			if (StringUtils.isNotEmpty(result)) {
				Map<String, Object> map = JSON.parseObject(result);
				return map;
			}
		} catch (Exception e) {
			logger.error("ticket刷新异常", e);
		}
		return null;

	}
}

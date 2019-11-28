package com.nouser.utils.message.cty;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nouser.utils.UtilsConstants;
import com.nouser.utils.net.HttpClient;

/**
 * 畅天游手机消息工具类, 需要安全ip
 * 
 * @Title: ChangTianYouMSGUtils.java
 * @Package com.nouser.utils.message.cty
 * @author: zhoukl
 * @date: 2019年11月27日 下午2:12:02
 * @version V1.0
 */
public class ChangTianYouMSGUtils {

	/* 调用畅天游发送短信的接口 */
	public static Map<String, Object> sendMSG(String mobile, String msg_str) {
		Map<String, Object> result = new HashMap<>();
		String changUrl = buildCTUMSGUrl(mobile, msg_str);
		String response = HttpClient.httpGet(changUrl);
		if (response != null) {
			Map<String, String> res = praseMsgResult(response);
			result.putAll(res);
			String sendResCode = res.get("Result");
			switch (sendResCode) {
			case "1":
				result.put("code", 200);
				result.put("errorInfo", "短信发送成功");
				break;
			case "-1":
				result.put("code", 400);
				result.put("errorInfo", "用户名或密码错误");
				break;
			case "-2":
				result.put("code", 400);
				result.put("errorInfo", "手机号不正确");
				break;
			case "-3":
				result.put("code", 400);
				result.put("errorInfo", "msg参数为空");
				break;
			case "-4":
				result.put("code", 400);
				result.put("errorInfo", "短信字数超长");
				break;
			case "-5":
				result.put("code", 400);
				result.put("errorInfo", "群发手机号码个数超限");
				break;
			case "-6":
				result.put("code", 400);
				result.put("errorInfo", "黑名单号码");
				break;
			case "-7":
				result.put("code", 400);
				result.put("errorInfo", "请求参数为空");
				break;
			case "-8":
				result.put("code", 400);
				result.put("errorInfo", "短信内容含有屏蔽词");
				break;
			case "-9":
				result.put("code", 400);
				result.put("errorInfo", "短信账户不存在");
				break;
			case "-10":
				result.put("code", 400);
				result.put("errorInfo", "短信账户已经停用");
				break;
			case "-11":
				result.put("code", 400);
				result.put("errorInfo", "短信账户余额不足");
				break;
			case "-12":
				result.put("code", 400);
				result.put("errorInfo", "密码错误");
				break;
			case "-16":
				result.put("code", 400);
				result.put("errorInfo", "IP服务器鉴权错误");
				break;
			case "-17":
				result.put("code", 400);
				result.put("errorInfo", "单发手机号码个数超限");
				break;
			case "-21":
				result.put("code", 400);
				result.put("errorInfo", "提交速度超限");
				break;
			case "-22":
				result.put("code", 400);
				result.put("errorInfo", "手机号达到当天发送限制");
				break;
			case "-99":
			default:
				result.put("code", 400);
				result.put("errorInfo", "系统异常");
				break;
			}
		} else {
			result.put("code", 400);
			result.put("errorInfo", "短信发送失败");
		}
		result.put("ctu_response", response);
		return result;
	}

	/* 拼接短信URL */
	private static String buildCTUMSGUrl(String mobile, String msg_str) {
		Map<String, String> map = buildMSGParams(mobile, msg_str);
		String url;
		try {
			url = buildUrl(UtilsConstants.CTY_URL, null, map);
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(String.format("畅天游URL格式化出错！语句：%s", ""), e);
		}
		return url;
	}

	/* 短信参数 */
	private static Map<String, String> buildMSGParams(String mobile, String msg_str) {
		Map<String, String> map = new HashMap<>();
		map.put("un", UtilsConstants.CTY_USER);// 用户名
		map.put("pwd", UtilsConstants.CTY_PWD);// 接口密码
		map.put("mobile", mobile);// 手机号
		map.put("msg", msg_str);//
		return map;
	}

	/* 构建Url-采用GB2312转码 */
	private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(host);
		if (!StringUtils.isBlank(path)) {
			sbUrl.append(path);
		}
		if (null != querys) {
			StringBuilder sbQuery = new StringBuilder();
			for (Map.Entry<String, String> query : querys.entrySet()) {
				if (0 < sbQuery.length()) {
					sbQuery.append("&");
				}
				if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
					sbQuery.append(query.getValue());
				}
				if (!StringUtils.isBlank(query.getKey())) {
					sbQuery.append(query.getKey());
					if (!StringUtils.isBlank(query.getValue())) {
						sbQuery.append("=");
						sbQuery.append(URLEncoder.encode(query.getValue(), "gb2312"));
					}
				}
			}
			if (0 < sbQuery.length()) {
				sbUrl.append("?").append(sbQuery);
			}
		}

		return sbUrl.toString();
	}

	/* 解析发送短信的结果 */
	private static Map<String, String> praseMsgResult(String str) {
		HashMap<String, String> map = new HashMap<>();
		try {
			Document doc = DocumentHelper.parseText(str);
			// 指向根节点
			Element root = doc.getRootElement();
			Element resultE = root.element("Result");// 返回状态码
			Element CtuIdE = root.element("CtuId");// 流水号
			String result = resultE.getStringValue();
			String CtuId = CtuIdE.getStringValue();
			map.put("Result", result);
			map.put("CtuId", CtuId);
			return map;
		} catch (DocumentException e) {
			throw new RuntimeException(String.format("畅天游短信解析异常！语句：%s", ""), e);
		}
	}

	private ChangTianYouMSGUtils() {
	}
}

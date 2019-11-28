package com.nouser.wx.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 通过授权码获取：authorizer_refresh_token、authorizer_access_token返回的Bean
 * 
 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=e9e0907473c9471c37979a9a05be34b49e64abf5&lang=zh_CN
 * 
 * @author qcodelv
 *
 */
public class AuthInfoBean implements Serializable {
	private static final long serialVersionUID = -7217831525923418445L;
	private AuthorizationInfoBean authorization_info;// 授权信息

	public AuthorizationInfoBean getAuthorization_info() {
		return authorization_info;
	}

	public void setAuthorization_info(AuthorizationInfoBean authorization_info) {
		this.authorization_info = authorization_info;
	}

	public static class AuthorizationInfoBean {

		private String authorizer_appid;// 授权方appid
		private String authorizer_access_token;// 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
		private int expires_in;// 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
		private String authorizer_refresh_token;// 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值）
		private List<FuncInfoBean> func_info;// 授权给开发者的权限集列表，ID为1到26分别代表

		public String getAuthorizer_appid() {
			return authorizer_appid;
		}

		public void setAuthorizer_appid(String authorizer_appid) {
			this.authorizer_appid = authorizer_appid;
		}

		public String getAuthorizer_access_token() {
			return authorizer_access_token;
		}

		public void setAuthorizer_access_token(String authorizer_access_token) {
			this.authorizer_access_token = authorizer_access_token;
		}

		public int getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(int expires_in) {
			this.expires_in = expires_in;
		}

		public String getAuthorizer_refresh_token() {
			return authorizer_refresh_token;
		}

		public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
			this.authorizer_refresh_token = authorizer_refresh_token;
		}

		public List<FuncInfoBean> getFunc_info() {
			return func_info;
		}

		public void setFunc_info(List<FuncInfoBean> func_info) {
			this.func_info = func_info;
		}

		public static class FuncInfoBean {
			/**
			 * funcscope_category : {"id":1}
			 */

			private FuncscopeCategoryBean funcscope_category;

			public FuncscopeCategoryBean getFuncscope_category() {
				return funcscope_category;
			}

			public void setFuncscope_category(FuncscopeCategoryBean funcscope_category) {
				this.funcscope_category = funcscope_category;
			}

			public static class FuncscopeCategoryBean {
				/**
				 * id : 1
				 */

				private int id;

				public int getId() {
					return id;
				}

				public void setId(int id) {
					this.id = id;
				}
			}
		}
	}
}

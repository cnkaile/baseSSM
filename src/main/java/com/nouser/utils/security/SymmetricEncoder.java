package com.nouser.utils.security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * AES对称加密和解密
 */
@SuppressWarnings("restriction")
public class SymmetricEncoder {

	private static final Logger logger = LoggerFactory.getLogger(SymmetricEncoder.class);

	/**
	 * 不要改
	 */
	private static final String secretKey = "sfffffff2@3rvfdf";

	private static final BASE64Encoder base64Encoder = new BASE64Encoder();//加密
	
	private static final BASE64Decoder base64Decoder = new BASE64Decoder();//解密

	private static Cipher cipherEncode = null;//加密

	private static Cipher cipherDncode = null;//解密

	static {
		try {
			byte[] raw = secretKey.getBytes("utf-8");
			//5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			//6.根据指定算法AES自成密码器
			cipherEncode = Cipher.getInstance("AES/ECB/PKCS5Padding");
			//7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipherEncode.init(Cipher.ENCRYPT_MODE, key);
			
			cipherDncode = Cipher.getInstance("AES/ECB/PKCS5Padding");
			//7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipherDncode.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	//加密
	public static String AESEncode(String content) {
		try {
			byte[] byte_AES = cipherEncode.doFinal(content.getBytes("utf-8"));
			//在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
			String AES_encode = new String(base64Encoder.encode(byte_AES));
			return AES_encode;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	//解密
	public static String AESDncode(String content) {
		try {
			//8.将加密并编码后的内容解码成字节数组
			byte[] byte_content = base64Decoder.decodeBuffer(content);
			byte[] byte_decode = cipherDncode.doFinal(byte_content);//解密
			String AES_decode = new String(byte_decode, "utf-8");
			return AES_decode;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
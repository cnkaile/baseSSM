/**
 * @Title:  XmlRequestResposne.java
 * @Package	com.nouser.controller
 * @author:	zhoukl
 * @date:	2019年11月27日 下午5:10:44
 * @version	V1.0
 */
package com.nouser.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nouser.common.entity.EmailUser;


/**
 * @Title:  XmlRequestResposne.java
 * @Package	com.nouser.controller
 * @author:	zhoukl
 * @date:	2019年11月27日 下午5:10:44
 * @version	V1.0
 */
@RestController
@RequestMapping("/xml")
public class XmlRequestResposne {
	
	public String getXml() {
		
		return null;
	}
	
	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public Map<String, Object> testRestMethod(EmailUser user){
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
	

	@RequestMapping(value = "/testUrlParam/{page}")
	public Map<String, Object> testUrlParam(@PathVariable String page){
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
}

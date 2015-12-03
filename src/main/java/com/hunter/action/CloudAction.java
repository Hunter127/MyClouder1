package com.hunter.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.hunter.util.HUtils;
import com.hunter.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

@Controller("cloudAction")
@Scope("prototype")
public class CloudAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String input;
	private String output;
	
	
	
	
	
	public void upload(){
		Map<String,Object> map = HUtils.upload(input, HUtils.getHDFSPath(HUtils.SOURCEFILE));
		Utils.write2PrintWriter(JSON.toJSONString(map));
		return;
		
	}
	public void download(){
		Map<String,Object> map = HUtils.downLoad(input,Utils.getRootPathBasedPath(output) );
		Utils.write2PrintWriter(JSON.toJSONString(map));
		return;
	}

}

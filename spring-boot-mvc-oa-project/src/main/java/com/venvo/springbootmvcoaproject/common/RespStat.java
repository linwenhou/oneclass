package com.venvo.springbootmvcoaproject.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class RespStat {

	/**
	 * JSON报文
	 * 状态码 
	 * 用于前端判断   200  = 成功
	 * 400\500 出错
	 * 
	 * msg = 信息
	 */
	
	private int code;
	private String msg;
	private Object data;
	
		
	public RespStat() {
		
		super();
	}
	
	public RespStat(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public static RespStat build(int i) {
		return new RespStat(200, "ok", "meiyou");
	}

	public static RespStat ok(int code,String msg,Object data) {
		return new RespStat(code, msg, data);
	}
	
	
	
	
}

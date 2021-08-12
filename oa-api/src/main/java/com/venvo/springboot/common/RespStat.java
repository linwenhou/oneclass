package com.venvo.springboot.common;


import java.io.Serializable;

public class RespStat implements Serializable {

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

	public static RespStat ok(int code, String msg, Object data) {
		return new RespStat(code, msg, data);
	}
	
	
	
	
}

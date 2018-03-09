package com.vcyber.user.util;

import java.io.Serializable;

public class HttpMessageVO implements Serializable {

	/**
	 * @Fields serialVersionUID	:TODO
	 */
	private static final long serialVersionUID = 3062632828392374597L;

	/**
	 * HTTP 请求的返回状态码
	 */
	private String code;
	
	/**
	 * 状态码对应的信息 200：成功，500失败等等
	 */
	private String message;
	
	/**
	 * 请求的返回内容
	 */
	private String content;

	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

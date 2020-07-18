package com.cognizant.api;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-19T15:35:34.885+05:30[Asia/Calcutta]")
public class NotFoundException extends ApiException {
	private int code;

	public NotFoundException(int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}

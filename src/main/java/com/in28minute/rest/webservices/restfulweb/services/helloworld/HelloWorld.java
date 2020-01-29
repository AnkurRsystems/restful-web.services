package com.in28minute.rest.webservices.restfulweb.services.helloworld;

public class HelloWorld  {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloWorld(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("HelloWorld [message=%s]" , message );
	}
	
	
	
}

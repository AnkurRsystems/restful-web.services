package com.in28minute.rest.webservices.restfulweb.services.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/hello-world")
	public String getHelloWorld() {
		return "Hello World";
	}

	@GetMapping(path="/hello-world-bean")
	public HelloWorld getHelloWorldBean() {
		return new HelloWorld("Hello World");
	}
	
//	@GetMapping(path="/hello-world-internationalized")
//	public String getHelloWorldInernationalization(@RequestHeader (name="Accept-Language" , required=false)  Locale locale) {
//		return messageSource.getMessage("good.morning.message", null,locale);
//	}

	@GetMapping(path = "/hello-world-internationalized")
	public String getHelloWorldInernationalization() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorld getHelloWorldBeanPathParams(@PathVariable String name) {
		return new HelloWorld(String.format("Hello World %s",name));
	}
}
 
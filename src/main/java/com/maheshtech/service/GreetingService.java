package com.maheshtech.service;

public class GreetingService {

public String getMessage(String name) {
	if(name==null) {
	name= "Web Mvc";	
	}
return "Hello,"+name;
}
}

package com.user.ums.util;

import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller
public class ResponseStructure<T> {
	
	private int status;
	private String message;
	private T data;
	

}

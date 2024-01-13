package com.user.ums.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
//NO NEED TO CREATE THIS CLASS
@Getter
@Setter
@Component
public class ExceptionStructure<T> {
	private String message;
	private int status;
	private String rootSructure;
}

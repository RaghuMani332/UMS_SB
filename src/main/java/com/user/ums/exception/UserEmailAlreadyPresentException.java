package com.user.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailAlreadyPresentException extends RuntimeException {
	String message ;
	
}

package com.user.ums.service;

import org.springframework.http.ResponseEntity;

import com.user.ums.requestdto.UserRequest;
import com.user.ums.responsedto.UserResponse;
import com.user.ums.util.ResponseStructure;

public interface UserService {
	
	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest, int userId);

	ResponseEntity<ResponseStructure<UserResponse>> getUserById(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId);

}

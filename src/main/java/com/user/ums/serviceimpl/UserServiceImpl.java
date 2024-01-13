package com.user.ums.serviceimpl;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.user.ums.entity.User;
import com.user.ums.exception.UserEmailAlreadyPresentException;
import com.user.ums.exception.UserNotFoundException;
import com.user.ums.repository.UserRepository;
import com.user.ums.requestdto.UserRequest;
import com.user.ums.responsedto.UserResponse;
import com.user.ums.service.UserService;
import com.user.ums.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ResponseStructure<UserResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user;
		try {
			user = userRepository.save(mapToUser(userRequest));
		} catch (RuntimeException e) {
			throw new UserEmailAlreadyPresentException("user Email is Already Present in dataBase please try with other user ");
		}
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User data saved !!!");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}

	private User mapToUser(UserRequest request) {
		return User.builder()
				.userName(request.getUserName())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
	}
	
	private UserResponse mapToUserResponse(User response) {
		return UserResponse.builder()
				.userId(response.getUserId())
				.userName(response.getUserName())
				.email(response.getEmail())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest, int userId) {
// way 1
//		User sch = userRepository.findById(userId).orElseThrow(() -> new RuntimeException() );
//	    user.setUserId(userId);
//		sch=userRepository.save(user);

//		way 2
		User user=mapToUser(userRequest);
		User user2;
		user2= userRepository.findById(userId)
				.map(u -> {
			    user.setUserId(userId);
			    return userRepository.save(user);
		}).orElseThrow(() -> new UserNotFoundException("User is not ther in database...!!!!"));
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("User data updated !!!");
		responseStructure.setData(mapToUserResponse(user2));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);

	}

	@Override 
	public ResponseEntity<ResponseStructure<UserResponse>> getUserById(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User is not ther in database...!!!!"));
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("User data get By Id !!!");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User is not ther in database...!!!!"));
		userRepository.deleteById(userId);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("User data deleted !!!");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}

}

package com.user.ums.responsedto;

import com.user.ums.requestdto.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private int userId;
	private String userName;
	private String email;
}

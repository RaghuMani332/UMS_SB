package com.user.ums.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserRequest {
	
//	@NotNull(message = "user name should not be null")
	@NotEmpty(message = "username should not be empty or null")
	@Pattern(regexp = "^[A-Z][a-zA-Z]*([ ][A-Z][a-zA-Z]*)?$", 
    message = "Username should start with an uppercase letter, and if there are two names, the second name should start with an uppercase letter.")
	private String userName;
	
	@NotBlank(message = "emaail canot be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	
	 @Size(min = 8,max = 20,message = "pasword must be 8 to 20 character")
	 @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
	            message = "Password must have 1 uppercase, 1 lowercase, 1 number, 1 special character, and be at least 8 characters long")
	private String password;

}

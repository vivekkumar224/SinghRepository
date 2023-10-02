package com.hcl.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4, message = "name must be minimum four character")
	private String name;
	@Email(message = "your email address is not valid")
	private String email;
	@NotEmpty
	@Size(min = 5,message = "your password must be minimum 5 character")
	private String password;
	@NotEmpty
	private String about;

}

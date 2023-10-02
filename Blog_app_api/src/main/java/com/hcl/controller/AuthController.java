package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.config.JWTTokentHelper;
import com.hcl.exception.ApiException;
import com.hcl.model.JWTRequest;
import com.hcl.model.JWTResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private JWTTokentHelper tokenHelper;
	@Autowired
	private UserDetailsService userdetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	 
	@PostMapping("/login")
	public ResponseEntity<JWTResponse> createToken(@RequestBody JWTRequest request)
	{
		this.authrnticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userdetailsService.loadUserByUsername(request.getUsername());
		String token = this.tokenHelper.generateToken(userDetails);
		
		JWTResponse response=new JWTResponse();
		response.setToken(token);
		
		return new ResponseEntity<JWTResponse>(response,HttpStatus.OK);
	}

	
	private void authrnticate(String username, String password) {
		// TODO Auto-generated method stub
		
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

		} catch (BadCredentialsException e) {
			// TODO: handle exception
			
			throw new ApiException("invalid username and password ! ");
		}
		
	}

}

package com.moview.UserRegistrationService.Utility;

import lombok.Data;

@Data
public class JWTResponse {

	private String jwtToken;
	
	public JWTResponse() {
		
	}

	public JWTResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}

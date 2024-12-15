package com.back.pid_grupo01.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {	
	private String token;
	private boolean isDniUnique;
	private boolean isEmailUnique;
	private boolean isUsernameUnique;
	private String suggestion;


}

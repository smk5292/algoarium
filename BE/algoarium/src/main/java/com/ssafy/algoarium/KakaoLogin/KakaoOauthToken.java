package com.ssafy.algoarium.KakaoLogin;

import lombok.Data;

@Data
public class KakaoOauthToken {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private int refresh_token_expires_in;
	private String scope;

}

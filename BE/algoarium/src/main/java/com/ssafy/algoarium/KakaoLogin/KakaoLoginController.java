package com.ssafy.algoarium.KakaoLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("")
public class KakaoLoginController {

	//접속 창 이동
	//https://kauth.kakao.com/oauth/authorize
	//?response_type=code&client_id=1e6e787538b273266b9e8054397aec13
	//&redirect_uri=http://localhost:8080/auth/kakao/callback
	@Autowired
	KakaoLoginService kakaoLoginService;

	@ResponseBody
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code){

		KakaoOauthToken kakaoOauthToken = kakaoLoginService.findKakaoToken(code);
		// System.out.println("카카오 엑세스 토큰 : " + kakaoOauthToken.getAccess_token());
		return kakaoOauthToken.getAccessToken();
	}


	@ResponseBody
	@GetMapping("/auth/kakao/{accessToken}")
	public KakaoDto findKakaoInfo(@PathVariable String accessToken){

		KakaoInfo profile = kakaoLoginService.findKakaoInfo(accessToken);

		KakaoDto profileDto = kakaoLoginService.sendKakaoDto(profile);

		return profileDto;
	}

}

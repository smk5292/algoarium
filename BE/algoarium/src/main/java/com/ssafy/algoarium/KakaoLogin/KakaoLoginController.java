package com.ssafy.algoarium.KakaoLogin;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
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

	@ResponseBody
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code){

		// code 를 받은 것으로 post url 에 맞춰서 보내면 token 반환
		// POST URI : https://kauth.kakao.com/oauth/token
		// POST Content-type : Content-type: application/x-www-form-urlencoded;charset=utf-8

		// 스프링 안에서 post 반환 값을 불러오는 방법은
		//Retrofit2, OkHttp, RestTemplate

		// RestTemplate rt = new RestTemplate();
		// HttpHeaders headers = new HttpHeaders();
		//
		// headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		//
		// MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		// params.add("grant_type","authorization_code");
		// params.add("client_id","1e6e787538b273266b9e8054397aec13");
		// params.add("redirect_uri","localhost:8080");
		// params.add("code",code);
		//
		// HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest
		// 	= new HttpEntity<>(params,headers);
		//
		// ResponseEntity<String> response = rt.exchange(
		// 	"https://kauth.kakao.com/oauth/token"
		// 	, HttpMethod.POST
		// 	,kakaoRequest
		// 	,String.class
		// );

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type" , "authorization_code");
		params.add("client_id" , "1e6e787538b273266b9e8054397aec13");
		params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
		params.add("code",code);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
			new HttpEntity<>(params,headers);

		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token"
			,HttpMethod.POST
			,kakaoTokenRequest
			,String.class
		);

		//Gson , Json, Simple, Object Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoOauthToken kakaoOauthToken =null;
		try {
			kakaoOauthToken = objectMapper.readValue(response.getBody(),KakaoOauthToken.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("카카오 엑세스 토큰 : " + kakaoOauthToken.getAccess_token());


		//token 으로 데이터 정보 가지고 오는 법

		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization" , "Bear "+kakaoOauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		System.out.println("111111111111111111111111111111111");
		MultiValueMap<String, String> params2 = new LinkedMultiValueMap<>();

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
			new HttpEntity<>(null, headers2);

		System.out.println("2222222222222222222222222222222222222");
		// ResponseEntity<String> response2 = rt2.exchange(
		// 	"https://kapi.kakao.com/v2/user/me"
		// 	,HttpMethod.POST
		// 	,kakaoProfileRequest
		// 	,String.class
		// );
		ResponseEntity<String> responseProfile = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me"
			,HttpMethod.POST
			,kakaoProfileRequest
			,String.class
		);
		System.out.println("333333333333333333333333333333333333333333");




		return responseProfile.getBody();
	}






}

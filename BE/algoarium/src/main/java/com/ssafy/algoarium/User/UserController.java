package com.ssafy.algoarium.User;



public class UserController {

	//accessToken, refreshToken, 만료시간 2개를 준다 ->
	//다시 카카오 API 에 요청해서 KakaoUser Data 를 받고
	// 이를 UserDto로 변환 후 DB 에 있는지 찾는다
	//-> 없다면 새로 만들고 로그인 진행
	// 로그인
	// 이 유저에 맞는 AccessToken을 key로 하여, 만료시간을 설정 한 후 Redis에 저장
	// 요청이 있을 때

}

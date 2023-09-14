package com.ssafy.algoarium.User;

import org.springframework.stereotype.Service;

import com.ssafy.algoarium.Redis.RedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final RedisRepository redisRepository;

	public Long saveUser(UserDto userDto){
		return (userRepository.save(UserEntity.builder()
			.kakaoId(userDto.getKakaoId())
			.kakaoNickname(userDto.getKakaoNickname())
			.refreshToken(userDto.getRefreshToken())
			.profileImage(userDto.getProfileImage())
			.build()).getUserId());
	}




}

package com.ssafy.algoarium.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.algoarium.Redis.RedisRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final RedisRepository redisRepository;

	@Transactional
	public Long saveUser(UserDto userDto){
		return (userRepository.save(UserEntity.builder()
			.kakaoId(userDto.getKakaoId())
			.kakaoNickname(userDto.getKakaoNickname())
			.refreshToken(userDto.getRefreshToken())
			.profileImage(userDto.getProfileImage())
			.build()).getUserId());
	}

	@Transactional
	public UserEntity updateUser(UserDto userDto){
		UserEntity userEntity = userRepository.findByKakaoId(userDto.getKakaoId());

		UserEntity updateUserEntity = UserEntity.builder()
			.userId(userEntity.getUserId())
			.kakaoId(userEntity.getKakaoId())
			.kakaoNickname(userDto.getKakaoNickname())
			.preTier(userEntity.getPreTier())
			.profileImage(userDto.getProfileImage())
			.refreshToken(userDto.getRefreshToken())
			.build();

		userRepository.save(updateUserEntity);
		return updateUserEntity;
	}
	@Transactional
	public UserEntity getUserById(int userId){
		return userRepository.findById(userId).orElseThrow(()
			-> new EntityNotFoundException("not found"));

	}



}

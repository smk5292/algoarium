package com.ssafy.algoarium.User;

import java.util.List;

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

	public Long saveUser(UserDto userDto){
		return (userRepository.save(UserEntity.builder()
			.kakaoId(userDto.getKakaoId())
			.kakaoNickname(userDto.getKakaoNickname())
			.refreshToken(userDto.getRefreshToken())
			.profileImage(userDto.getProfileImage())
			.preTier(1)
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
	public UserEntity getUserById(long userId){
		return userRepository.findById(userId).orElseThrow(()
			-> new EntityNotFoundException("not found"));

	}

	@Transactional
	public UserEntity getUserByEmail(String email){
		return userRepository.findByKakaoId(email);
	}

	@Transactional
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

}

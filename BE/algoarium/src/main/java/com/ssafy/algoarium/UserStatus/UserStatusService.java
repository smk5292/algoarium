package com.ssafy.algoarium.UserStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.algoarium.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserStatusService {

	private final UserStatusRepository userStatusRepository;
	private final UserRepository userRepository;

	public UserStatusEntity toEntity(UserStatusDTO userStatusDTO){
		return UserStatusEntity.builder()
			.user(userRepository.findById(userStatusDTO.getUserId()).get())
			.userStatus1(userStatusDTO.getUserStatus1())
			.userStatus2(userStatusDTO.getUserStatus2())
			.userStatus3(userStatusDTO.getUserStatus3())
			.userStatus4(userStatusDTO.getUserStatus4())
			.userStatus5(userStatusDTO.getUserStatus5())
			.build();
	}

	@Transactional
	public UserStatusEntity getStatusById(long userId){;
		UserStatusEntity userStatusEntity = userStatusRepository.findById(userId).get();
		// userStatusDTO.ifPresent();
		return userStatusEntity;
	}

	@Transactional
	public UserStatusEntity getAvgStatusByTier(int tier){
		return userStatusRepository.findAvgByTier(tier).get();
	}


	@Transactional
	public void saveStatus(UserStatusDTO userStatusDTO){
		userStatusRepository.save(toEntity(userStatusDTO));
	}


	@Transactional
	public long saveInit(UserStatusEntity userStatusEntity){
		return UserStatusEntity.builder()
			.userStatus1(1)
			.userStatus2(1)
			.userStatus3(1)
			.userStatus4(1)
			.userStatus5(1)
			.build().getUser().getUserId();
	}
}

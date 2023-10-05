package com.ssafy.algoarium.UserStatus;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.algoarium.Problem.ProblemRepository;
import com.ssafy.algoarium.SolvedProblemHistory.SolvedProblemHistoryRepository;
import com.ssafy.algoarium.User.UserRepository;
import com.ssafy.algoarium.User.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserStatusService {

	private final UserStatusRepository userStatusRepository;
	private final UserRepository userRepository;
	private final UserService userService;
	private final SolvedProblemHistoryRepository solvedProblemHistoryRepository;
	private final ProblemRepository problemRepository;

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
	public void updateStatus(UserStatusDTO userStatusDTO){
		userStatusRepository.save(toEntity(userStatusDTO));
	}


	@Transactional
	public long saveInit(long userId){
		UserStatusEntity userStatusEntity = UserStatusEntity.builder()
			.user(userService.getUserById(userId))
			.userStatus1(1)
			.userStatus2(1)
			.userStatus3(1)
			.userStatus4(1)
			.userStatus5(1)
			.build();

		userStatusRepository.save(userStatusEntity);

		return userStatusEntity.getUser().getUserId();
	}

	@Transactional
	public void initStatus(String baekjoonId){
		URI uri = UriComponentsBuilder
			.fromUriString("https://solved.ac/api/v3/user/show")
			.queryParam("handle" , baekjoonId)
			.encode()
			.build()
			.toUri();

		System.out.println(uri.toString());



	}




}

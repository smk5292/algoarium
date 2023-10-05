package com.ssafy.algoarium.UserStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class UserStatusController {

	private final UserStatusService userStatusService;


	public static UserStatusDTO toDto(UserStatusEntity userStatusEntity){
		return UserStatusDTO.builder()
			.userId(userStatusEntity.getUser().getUserId())
			.userStatus1(userStatusEntity.getUserStatus1())
			.userStatus2(userStatusEntity.getUserStatus2())
			.userStatus3(userStatusEntity.getUserStatus3())
			.userStatus4(userStatusEntity.getUserStatus4())
			.userStatus5(userStatusEntity.getUserStatus5())
			.build();
	}

	@GetMapping("/stat/{userId}")
	public UserStatusDTO getStatusById(@PathVariable long userId){
		return toDto(userStatusService.getStatusById(userId));
	}

	@GetMapping("/stat/average/{tier}")
	public UserStatusDTO getAvgStatueByTier(@PathVariable int tier){
		System.out.println(userStatusService.getAvgStatusByTier(tier).toString());
		return toDto(userStatusService.getAvgStatusByTier(tier));
	}


	@GetMapping("/stat/init/{baekjoonId}")

}

package com.ssafy.algoarium.UserRanking;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.algoarium.User.UserEntity;
import com.ssafy.algoarium.User.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class UserRankingContoller {

	private final UserRankingService userRankingService;
	private final UserService userService;

	public static UserRankingDTO toDto(UserRankingEntity userRankingEntity){
		return UserRankingDTO.builder()
			.userRankingId(userRankingEntity.getUserRankingId())
			.userId(userRankingEntity.getUser().getUserId())
			.kakaoNickname(userRankingEntity.getUser().getKakaoNickname())
			.profileImage(userRankingEntity.getUser().getProfileImage())
			.score(userRankingEntity.getScore())
			.tier(userRankingEntity.getTier())
			.ranking(userRankingEntity.getRanking())
			.build();
	}

	@GetMapping("/{tier}")
	public List<UserRankingDTO> getRankingListByTier(@PathVariable int tier){
		List<UserRankingEntity> userRankingEntities = userRankingService.getListByTier(tier);

		return userRankingEntities.stream().map(UserRankingContoller::toDto).toList();
	}

	@GetMapping("/top/{tier}")
	public UserRankingDTO getRankingTopByTier(@PathVariable int tier){
		return toDto(userRankingService.getTopUserRankingEntity(tier));
	}

	@GetMapping("/my/{userId}")
	public UserRankingDTO getMyRankingById(@PathVariable long userId){
		UserEntity user = userService.getUserById(userId);
		return UserRankingDTO.builder()
			.kakaoNickname(user.getKakaoNickname())
			.profileImage(user.getProfileImage())
			.score(user.getUserRanking().getScore())
			.tier(user.getUserRanking().getTier())
			.ranking(user.getUserRanking().getRanking())
			.build();
	}


}

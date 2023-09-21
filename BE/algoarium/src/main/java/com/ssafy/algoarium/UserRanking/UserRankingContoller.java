package com.ssafy.algoarium.UserRanking;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rank")
public class UserRankingContoller {

	private final UserRankingService userRankingService;

	public static UserRankingDTO toDto(UserRankingEntity userRankingEntity){
		return UserRankingDTO.builder()
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



}

package com.ssafy.algoarium.UserRanking;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.algoarium.UserStatus.UserStatusDTO;
import com.ssafy.algoarium.UserStatus.UserStatusEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRankingService {

	private final UserRankingRepository userRankingRepository;

	public List<UserRankingEntity> getListByTier(int tier){
		return userRankingRepository.findByTier(tier);
	}

	public UserRankingEntity getTopUserRankingEntity(int tier){
		return userRankingRepository.findTopByOrderByUserIdDesc(tier).get();
	}

}

package com.ssafy.algoarium.UserRanking;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRankingService {

	private final UserRankingRepository userRankingRepository;

	public List<Long> getTierStatusAvg(int tier){
		return userRankingRepository.findAvgStatueByTier(tier);
	}

	public UserRankingDTO getUserRankingStatus(long userId){
		UserRankingDTO userRankingDTO;


	}




}

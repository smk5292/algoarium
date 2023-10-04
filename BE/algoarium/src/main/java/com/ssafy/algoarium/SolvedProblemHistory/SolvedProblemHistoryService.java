package com.ssafy.algoarium.SolvedProblemHistory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolvedProblemHistoryService {

	private final SolvedProblemHistoryRepository solvedProblemHistoryRepository;

	public SolvedProblemHistoryEntity toEntity(SolvedProblemHistoryDTO solvedProblemHistoryDTO){
		return SolvedProblemHistoryEntity.builder()
			.solvedProblemHistoryId(solvedProblemHistoryDTO.getSolvedProblemHistoryId())
			.solvedOrNot(solvedProblemHistoryDTO.getSolvedOrNot())
			.userEntity(solvedProblemHistoryDTO.getUser())
			.problemEntity(solvedProblemHistoryDTO.getProblem())
			.build();
	}


	public void saveSolvedProbelm(SolvedProblemHistoryDTO solvedProblemHistoryDTO){
		solvedProblemHistoryRepository.save(toEntity(solvedProblemHistoryDTO));
	}
}

package com.ssafy.algoarium.SolvedProblemHistory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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


	public void testBrowse(String baekjoonId){

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Authorization" , "bear dtBsYylntbwOQqEYFT_YF2CzNJQH8ROzFF26TcOgCisM1AAAAYpkUq77");

		String firstUrl ="https://solved.ac/api/v3/search/problem";
		// String endUrl = "&page=1";
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query" , "s%40smk921");
		params.add("page" , "1");

		HttpEntity<MultiValueMap<String, String>> httpEntity =
			new HttpEntity<>(params, headers);




	}
}


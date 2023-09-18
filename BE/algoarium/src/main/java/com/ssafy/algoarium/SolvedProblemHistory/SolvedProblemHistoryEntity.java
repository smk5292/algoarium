package com.ssafy.algoarium.SolvedProblemHistory;

import com.ssafy.algoarium.Problem.ProblemEntity;
import com.ssafy.algoarium.User.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "solved_problem_history")
public class SolvedProblemHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long solvedProblemHistoryId;

	@Column(name = "solved_or_not")
	private Boolean solvedOrNot = false;

	// 보강 필요
	@OneToMany
	private UserEntity user;

	@OneToMany
	private ProblemEntity problem;


}

package com.ssafy.algoarium.UserRanking;

import com.ssafy.algoarium.User.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_ranking")
public class UserRankingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userRankingId;

	@Column(name = "score" , nullable = false)
	private Integer score = 0;

	@Column(name = "tier" , nullable = false)
	private Integer tier = 1;

	@Column(name = "ranking", nullable = false)
	private Integer ranking;

	//보강 필요
	@OneToOne
	private UserEntity user;
}

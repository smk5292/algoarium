package com.ssafy.algoarium.UserStatus;

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
@Table(name = "user_status")
public class UserStatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_status_id;

	//enum class 만들어서 처리
	@Column(name =  "1번 능력치" , length = 50, nullable = false)
	private int userStatus1 = 0;

	@Column(name =  "2번 능력치" , length = 50, nullable = false)
	private int userStatus2 = 0;

	@Column(name =  "3번 능력치" , length = 50, nullable = false)
	private int userStatus3 = 0;

	@Column(name =  "4번 능력치" , length = 50, nullable = false)
	private int userStatus4 = 0;

	@Column(name =  "5번 능력치" , length = 50, nullable = false)
	private int userStatus5 = 0;

	// 보강 필요
	@OneToOne
	private UserEntity user;

}

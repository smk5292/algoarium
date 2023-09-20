package com.ssafy.algoarium.UserStatus;

import com.ssafy.algoarium.User.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@Column(name =  "wis" , length = 50, nullable = false)
	private int userStatus1 = 0;

	@Column(name =  "con" , length = 50, nullable = false)
	private int userStatus2 = 0;

	@Column(name =  "str" , length = 50, nullable = false)
	private int userStatus3 = 0;

	@Column(name =  "luk" , length = 50, nullable = false)
	private int userStatus4 = 0;

	@Column(name =  "sma" , length = 50, nullable = false)
	private int userStatus5 = 0;

	// 보강 필요
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id") // 연관되는 컬럼 지정
	private UserEntity user;

}

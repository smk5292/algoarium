package com.ssafy.algoarium.UserStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.algoarium.User.UserEntity;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {

//	@Query(value = "SELECT AVG(us.wis) AS wis, AVG(us.con) AS con, AVG(us.str) AS str"
//		+ ", AVG(us.luk) AS luk, AVG(us.sma) AS sma " +
//		"FROM user_status us " +
//		"JOIN user u on us.user_id = u.user_id " + // 여기서 user는 UserStatusEntity 클래스의 user 필드를 가리킵니다.
//		"JOIN user_ranking ur on u.user_id = ur.user_id " +
//		"WHERE ur.tier = :tier" , nativeQuery = true)
//	Optional<UserStatusEntity> findAvgByTier(@Param("tier") int tier);

	UserStatusEntity findByUser(UserEntity user);

}

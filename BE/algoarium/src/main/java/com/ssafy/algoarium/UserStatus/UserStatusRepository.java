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

	@Query(value = "SELECT CAST(AVG(wis) AS SIGNED) AS user_status_id," +
			"CAST(AVG(wis) AS SIGNED) AS wis, " +
			"CAST(AVG(con) AS SIGNED) AS con, " +
			"CAST(AVG(str) AS SIGNED) AS str, " +
			"CAST(AVG(luk) AS SIGNED) AS luk, " +
			"CAST(AVG(sma) AS SIGNED) AS sma, " +
			"CAST(AVG(sma) AS SIGNED) AS user_id" +
			"FROM user_status " +
			"JOIN user ON user_status.user_id = user.user_id " +
			"JOIN user_ranking ON user.user_id = user_ranking.user_id " +
			"WHERE tier = :tier" , nativeQuery = true)
	UserStatusEntity findAvgByTier(@Param("tier") int tier);

	UserStatusEntity findByUser(UserEntity user);

}

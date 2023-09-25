package com.ssafy.algoarium.UserStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {

	@Query(value = "SELECT AVG(us.wis) AS wis, AVG(us.con) AS con, AVG(us.str) AS str"
		+ ", AVG(us.luk) AS luk, AVG(us.sma) AS sma " +
		"FROM user_status us " +
		"WHERE us.tier = :tier")
	Optional<UserStatusEntity> findAvgByTier(@Param("tier") int tier);

}

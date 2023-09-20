package com.ssafy.algoarium.UserRanking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRankingRepository extends JpaRepository<UserRankingEntity, Long> {
	@Query("SELECT AVG(u.wis), AVG(u.con), AVG(u.str), AVG(u.luk), AVG(u.sma) FROM UserRankinEntity u where tier = :tier")
	List<Long> findAvgStatueByTier(Integer tier);
}

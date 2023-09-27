package com.ssafy.algoarium.UserRanking;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRankingRepository extends JpaRepository<UserRankingEntity, Long> {

	@Query(value = "SELECT ur FROM UserRankingEntity ur WHERE ur.tier = :tier")
	List<UserRankingEntity> findByTier(@Param("tier") int tier);

	@Query(value = "SELECT ur FROM UserRankingEntity ur WHERE ur.tier = :tier ORDER BY ur.score DESC")
	Optional<UserRankingEntity> findTopByOrderByUserIdDesc(@Param("tier") int tier);


}

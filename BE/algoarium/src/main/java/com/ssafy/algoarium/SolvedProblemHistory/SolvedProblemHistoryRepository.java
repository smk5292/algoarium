package com.ssafy.algoarium.SolvedProblemHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolvedProblemHistoryRepository extends JpaRepository<SolvedProblemHistoryEntity, Long> {

}

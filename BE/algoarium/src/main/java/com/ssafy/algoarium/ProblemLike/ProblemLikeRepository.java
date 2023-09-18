package com.ssafy.algoarium.ProblemLike;

import com.ssafy.algoarium.Problem.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemLikeRepository extends JpaRepository<ProblemLikeEntity, Integer> {
    // 추가적인 쿼리 메서드가 필요하다면 여기에 작성할 수 있습니다.
}
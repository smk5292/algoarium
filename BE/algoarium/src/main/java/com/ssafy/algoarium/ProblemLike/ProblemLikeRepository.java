package com.ssafy.algoarium.ProblemLike;

import com.ssafy.algoarium.Problem.ProblemEntity;
import com.ssafy.algoarium.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemLikeRepository extends JpaRepository<ProblemLikeEntity, Integer> {
    ProblemLikeEntity findByProblemEntityAndUserEntity(ProblemEntity problemEntity, UserEntity userEntity);
}


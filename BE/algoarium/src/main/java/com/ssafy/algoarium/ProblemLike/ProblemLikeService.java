// ProblemLikeService

package com.ssafy.algoarium.ProblemLike;

import com.ssafy.algoarium.Problem.ProblemEntity;
import com.ssafy.algoarium.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemLikeService {

    private final ProblemLikeRepository problemLikeRepository;

    @Autowired
    public ProblemLikeService(ProblemLikeRepository problemLikeRepository) {
        this.problemLikeRepository = problemLikeRepository;
    }

    public ProblemLikeEntity likeProblem(ProblemEntity problemEntity, UserEntity userEntity, Boolean likeType) {
        ProblemLikeEntity problemLikeEntity = new ProblemLikeEntity(problemEntity, userEntity);
        problemLikeEntity.setLikeType(likeType);

        return problemLikeRepository.save(problemLikeEntity);
    }

    public ProblemLikeEntity getProblemLikeByProblemAndUser(ProblemEntity problemEntity, UserEntity userEntity) {
        return problemLikeRepository.findByProblemEntityAndUserEntity(problemEntity, userEntity);
    }

    public ProblemLikeEntity updateProblemLike(ProblemLikeEntity problemLikeEntity) {
        return problemLikeRepository.save(problemLikeEntity);
    }

    public ProblemLikeEntity createMemo(ProblemEntity problemEntity, UserEntity userEntity, String memo) {
        ProblemLikeEntity problemLikeEntity = new ProblemLikeEntity(problemEntity, userEntity);
        problemLikeEntity.setMemo(memo);

        return problemLikeRepository.save(problemLikeEntity);
    }
}

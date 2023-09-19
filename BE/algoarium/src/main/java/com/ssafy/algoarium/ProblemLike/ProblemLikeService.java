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

    public ProblemLikeEntity likeProblem(ProblemEntity problemEntity, UserEntity userEntity, Boolean likeType, String memo) {
        ProblemLikeEntity problemLikeEntity = new ProblemLikeEntity(problemEntity, userEntity);
        problemLikeEntity.setLikeType(likeType);
        problemLikeEntity.setMemo(memo);

        return problemLikeRepository.save(problemLikeEntity);
    }

    public ProblemLikeEntity updateMemo(Integer problemLikeId, String memo) {
        ProblemLikeEntity problemLikeEntity = problemLikeRepository.findById(problemLikeId).orElse(null);
        if (problemLikeEntity != null) {
            problemLikeEntity.setMemo(memo);
            return problemLikeRepository.save(problemLikeEntity);
        }
        return null;
    }
}

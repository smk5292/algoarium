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

    public ProblemLikeEntity createMemo(ProblemEntity problemEntity, UserEntity userEntity, Boolean likeType, String memo) {
        ProblemLikeEntity problemLikeEntity = new ProblemLikeEntity(problemEntity, userEntity);
        problemLikeEntity.setLikeType(likeType);
        problemLikeEntity.setMemo(memo);

        return problemLikeRepository.save(problemLikeEntity);
    }

    // ProblemEntity와 UserEntity에 해당하는 메모와 좋아요 여부 가져오기
    public ProblemLikeDTO getProblemLikeInfo(ProblemEntity problemEntity, UserEntity userEntity) {
        ProblemLikeEntity problemLikeEntity = problemLikeRepository.findByProblemEntityAndUserEntity(problemEntity, userEntity);
        if (problemLikeEntity != null) {
            return ProblemLikeDTO.builder()
                    .likeType(problemLikeEntity.getLikeType())
                    .memo(problemLikeEntity.getMemo())
                    .build();
        } else {
            // 해당하는 데이터가 없을 경우 처리
            return ProblemLikeDTO.builder()
                    .likeType(false)
                    .memo(null)
                    .build();
        }
    }
}

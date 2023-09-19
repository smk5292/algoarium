// ProblemLikeController

package com.ssafy.algoarium.ProblemLike;

import com.ssafy.algoarium.Problem.ProblemEntity;
import com.ssafy.algoarium.Problem.ProblemService;
import com.ssafy.algoarium.User.UserEntity;
import com.ssafy.algoarium.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problemLikes")
public class ProblemLikeController {

    private final ProblemLikeService problemLikeService;
    private final ProblemService problemService; // 추가
    private final UserService userService; // 추가

    @Autowired
    public ProblemLikeController(ProblemLikeService problemLikeService, ProblemService problemService, UserService userService) {
        this.problemLikeService = problemLikeService;
        this.problemService = problemService;
        this.userService = userService;
    }

    @PostMapping("/like")
    public ProblemLikeEntity likeProblem(@RequestBody ProblemLikeDTO problemLikeDTO) {
        Integer problemId = problemLikeDTO.getProblemId();
        Integer userId = problemLikeDTO.getUserId();
        Boolean likeType = problemLikeDTO.getLikeType();

        ProblemEntity problemEntity = problemService.getProblemById(problemId);
        UserEntity userEntity = userService.getUserById(userId);

        ProblemLikeEntity existingLike = problemLikeService.getProblemLikeByProblemAndUser(problemEntity, userEntity);

        if (existingLike != null) {
            existingLike.setLikeType(likeType);

            return problemLikeService.updateProblemLike(existingLike);
        } else {
            return problemLikeService.likeProblem(problemEntity, userEntity, likeType);
        }
    }

    @PostMapping("/updateMemo")
    public ProblemLikeEntity set_update_Memo(@RequestBody ProblemLikeDTO problemLikeDTO) {
        Integer problemId = problemLikeDTO.getProblemId();
        Integer userId = problemLikeDTO.getUserId();
        String memo = problemLikeDTO.getMemo();

        ProblemEntity problemEntity = problemService.getProblemById(problemId);
        UserEntity userEntity = userService.getUserById(userId);

        ProblemLikeEntity existingLike = problemLikeService.getProblemLikeByProblemAndUser(problemEntity, userEntity);

        if (existingLike != null) {
            existingLike.setMemo(memo);

            return problemLikeService.updateProblemLike(existingLike);
        }

        return problemLikeService.createMemo(problemEntity, userEntity, memo);
    }


}

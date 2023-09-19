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
        String memo = problemLikeDTO.getMemo();

        ProblemEntity problemEntity = problemService.getProblemById(problemId);
        UserEntity userEntity = userService.getUserById(userId);

        return problemLikeService.likeProblem(problemEntity, userEntity, likeType, memo);
    }


    @PostMapping("/updateMemo/{problemLikeId}")
    public ProblemLikeEntity updateMemo(
            @PathVariable Integer problemLikeId,
            @RequestBody ProblemLikeDTO updatedProblemLike // @RequestParam 대신 @RequestBody를 사용합니다.
    ) {
        return problemLikeService.updateMemo(problemLikeId, updatedProblemLike.getMemo());
    }

}

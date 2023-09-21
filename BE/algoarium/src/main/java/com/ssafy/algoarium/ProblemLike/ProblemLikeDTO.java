package com.ssafy.algoarium.ProblemLike;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemLikeDTO {
    private Integer problemLikeId;
    private Integer userEntity;
    private Integer problemEntity;
    private Boolean likeType;
    private String memo;
    private Integer problemId;
    private Integer userId; // 이 부분을 추가합니다.

    public Integer getProblemId() {
        return this.problemId;
    }

    public Integer getUserId() {
        return this.userId;
    }
}


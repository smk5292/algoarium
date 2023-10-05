// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.ssafy.algoarium.UserStatus;
import java.util.List;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStatusResponse {
    @JsonProperty("rating")
    private long rating;
    @JsonProperty("solvedCount")
    private long solvedCount;
    @JsonProperty("maxStreak")
    private long maxStreak;
    @JsonProperty("maxStreak")
    private long stardusts;

    @JsonProperty("ratingBySolvedCount")
    private long ratingBySolvedCount;

}

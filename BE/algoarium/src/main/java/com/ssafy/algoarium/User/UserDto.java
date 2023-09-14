package com.ssafy.algoarium.User;

import jakarta.persistence.*;
import lombok.*;
/**
 * 회원 Collection
 */


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer userId;
    private String kakaoId;
    private String kakaoNickname;
    private String profileImage;
    private String preTier;
    private String refreshToken;

    public static UserEntity toUserEntity(UserEntity userDTO) {
        return UserEntity.builder()
            .kakaoId(userDTO.getKakaoId())
            .kakaoNickname(userDTO.getKakaoNickname())
            .profileImage(userDTO.getProfileImage())
            .preTier(userDTO.getPreTier())
            .refreshToken(userDTO.getRefreshToken())
            .build();
    }

}


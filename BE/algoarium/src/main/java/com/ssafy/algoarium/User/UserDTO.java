package com.ssafy.algoarium.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer userId;
    private String kakaoId;
    private String kakaoNickname;
    private String profileImage;
    private String preTier;
    private String refreshToken;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .userId(userEntity.getUserId())
                .kakaoId(userEntity.getKakaoId())
                .kakaoNickname(userEntity.getKakaoNickname())
                .profileImage(userEntity.getProfileImage())
                .preTier(userEntity.getPreTier())
                .refreshToken(userEntity.getRefreshToken())
                .build();
    }
}

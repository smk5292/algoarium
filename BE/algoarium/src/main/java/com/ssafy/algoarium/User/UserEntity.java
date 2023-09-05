package com.ssafy.algoarium.User;

import jakarta.persistence.*;
import lombok.*;
/**
 * 회원 Collection
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    private Integer userId;
    private String kakaoId;
    private String kakaoNickname;
    private String profileImage;
    private String preTier;
    private String refreshToken;

    public static UserEntity toUserEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .userId(userDTO.getUserId())
                .kakaoId(userDTO.getKakaoId())
                .kakaoNickname(userDTO.getKakaoNickname())
                .profileImage(userDTO.getProfileImage())
                .preTier(userDTO.getPreTier())
                .refreshToken(userDTO.getRefreshToken())
                .build();
    }

}



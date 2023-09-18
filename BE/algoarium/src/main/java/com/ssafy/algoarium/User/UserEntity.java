package com.ssafy.algoarium.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "kakao_id", nullable = false, length = 100)
    private String kakaoId;

    @Column(name = "kakao_nickname", nullable = false, length = 50)
    private String kakaoNickname;

    @Column(name = "profile_image", nullable = false, length = 200)
    private String profileImage;

    @Column(name = "pre_tier", nullable = false, length = 20)
    private Integer preTier = 1;

    @Column(name = "refresh_token", nullable = false, length = 100)
    private String refreshToken;

    @Builder
    public UserEntity(Long userId , String kakaoId, String kakaoNickname, String profileImage,
        Integer preTier, String refreshToken) {
        this.userId = userId;
        this.kakaoId = kakaoId;
        this.kakaoNickname = kakaoNickname;
        this.profileImage = profileImage;
        this.preTier = preTier;
        this.refreshToken = refreshToken;
    }
}

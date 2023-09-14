package com.ssafy.algoarium.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByKakaoId(String kakaoId);
    Optional<UserEntity> findByUserId(Integer userId);
}

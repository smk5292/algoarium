package com.ssafy.algoarium.BaekjoonUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaekjoonUserRepository extends JpaRepository<BaekjoonUserEntity, Integer> {
    BaekjoonUserEntity findByBjId(String bjId);
}

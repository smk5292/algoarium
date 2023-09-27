package com.ssafy.algoarium.BaekjoonUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaekjoonUserScheduler {

    private final BaekjoonUserService baekjoonUserService;
    private final BaekjoonUserRepository baekjoonUserRepository; // BaekjoonUserRepository 주입

    @Autowired
    public BaekjoonUserScheduler(BaekjoonUserService baekjoonUserService, BaekjoonUserRepository baekjoonUserRepository) {
        this.baekjoonUserService = baekjoonUserService;
        this.baekjoonUserRepository = baekjoonUserRepository; // 주입된 Repository
    }

    // 매일 6시에 실행
//    @Scheduled(cron = "0 0 6 * * ?")
    @Scheduled(cron = "0 48 9 * * ?")
    public void updateBaekjoonUsers() {
        System.out.println("모든 백준유저 업데이트 시작");
        // 여기에서 모든 bjId를 가져와서 initializeBaekjoonUser() 메서드 호출
        List<String> allBjIds = baekjoonUserRepository.findAllBjIds(); // 이 메서드는 BaekjoonUserRepository에 추가되어야 합니다.

        for (String bjId : allBjIds) {
            baekjoonUserService.fetchUserAndSaveToDatabase(bjId);
        }
    }
}

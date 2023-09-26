package com.ssafy.algoarium.BaekjoonUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/BaekjoonUser")
public class BaekjoonUserController {

    private final BaekjoonUserService baekjoonUserService;

    @Autowired
    public BaekjoonUserController(BaekjoonUserService baekjoonUserService) {
        this.baekjoonUserService = baekjoonUserService;
    }

    @GetMapping("/initialize/{bjId}")
    public Boolean initializeBaekjoonUser(@PathVariable String bjId) {
        baekjoonUserService.fetchUserAndSaveToDatabase(bjId);
        return true;
    }

    @GetMapping("/bio/{bjId}")
    public String bioBaekjoonUser(@PathVariable String bjId) {
        return baekjoonUserService.fetchBio(bjId);
    }
}

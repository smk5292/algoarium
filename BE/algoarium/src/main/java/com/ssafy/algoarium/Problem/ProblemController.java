package com.ssafy.algoarium.Problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/fetch")
    public void fetchProblemsAndSaveToDatabase() {
        problemService.fetchProblemsAndSaveToDatabase();
    }
}

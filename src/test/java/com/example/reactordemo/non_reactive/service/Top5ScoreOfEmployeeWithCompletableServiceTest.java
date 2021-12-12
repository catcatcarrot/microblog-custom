package com.example.reactordemo.non_reactive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Top5ScoreOfEmployeeWithCompletableServiceTest {

    @Autowired
    private Top5ScoreOfEmployeeWithCompletableService top5ScoreOfEmployeeWithCompletableService;


    @Test
    void return_top5_score_of_employee_when_use_completable_future() {
        System.out.println(top5ScoreOfEmployeeWithCompletableService.getTop5ScoreOfEmployee());
    }
}
package com.example.reactordemo.reactive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
class Top5ScoreOfEmployeeServiceTest {

    @Autowired
    private Top5ScoreOfEmployeeService top5ScoreOfEmployeeService;

    @Test
    void return_top5_score_of_employee_when_use_flux() {
        top5ScoreOfEmployeeService.getTop5ScoreOfEmployee()
                .subscribeOn(Schedulers.boundedElastic())
                .log()
                .subscribe(System.out::println);
    }
}

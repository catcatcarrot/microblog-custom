package com.example.reactordemo.reactive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
class Top5ScoreOfEmployeeServiceTest {

    @Autowired
    private Top5ScoreOfTeacherService top5ScoreOfTeacherService;

    @Test
    void return_top5_score_of_teacher_when_use_flux() {
        top5ScoreOfTeacherService.getTop5ScoreOfTeacher()
                .subscribeOn(Schedulers.boundedElastic())
                .log()
                .subscribe(System.out::println);
    }
}

package com.example.reactordemo.reactive.service;

import com.example.reactordemo.vo.SearchInfoFlux;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
class SearchPageServiceTest {

    @Autowired
    private SearchPageServiceFlux searchPageService;

    @Test
    void return_search_page_by_reactor() {
        Mono<SearchInfoFlux> searchInfoByReactor = searchPageService.getSearchInfoByReactor(true);
        searchInfoByReactor.subscribeOn(Schedulers.parallel()).subscribe(item -> {
            item.getHotInfos().subscribe(System.out::println);
            item.getRecommendedInfos().subscribe(System.out::println);
        });
    }
}
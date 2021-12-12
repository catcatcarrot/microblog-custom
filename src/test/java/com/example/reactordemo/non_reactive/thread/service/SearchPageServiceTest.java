package com.example.reactordemo.non_reactive.thread.service;

import com.example.reactordemo.vo.SearchInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class SearchPageServiceTest {

    @Autowired
    private SearchPageService service;

    @Test
    void return_search_page() throws ExecutionException, InterruptedException {
        SearchInfo searchInfo = service.getSearchInfo(false);

        System.out.println(searchInfo.getHotInfos());
        System.out.println(searchInfo.getRecommendedInfos());
    }
}

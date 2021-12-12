package com.example.reactordemo.non_reactive.service;

import com.example.reactordemo.vo.SearchInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class SearchPageServiceTest {

    @Autowired
    private SearchPageService service;

    @Test
    void return_search_page_by_future() throws ExecutionException, InterruptedException {
        SearchInfo searchInfo = service.getSearchInfoByFuture(false);

        System.out.println(searchInfo.getHotInfos());
        System.out.println(searchInfo.getRecommendedInfos());
    }

    @Test
    void return_search_page_by_completable_future() throws ExecutionException, InterruptedException {
        SearchInfo searchInfo = service.getSearchInfoByCompletableFuture(false);

        System.out.println(searchInfo.getHotInfos());
        System.out.println(searchInfo.getRecommendedInfos());
    }

}

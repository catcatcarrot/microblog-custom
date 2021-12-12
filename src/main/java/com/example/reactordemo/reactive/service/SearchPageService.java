package com.example.reactordemo.reactive.service;

import com.example.reactordemo.reactive.repository.HotInfoRepository;
import com.example.reactordemo.reactive.repository.RecommendedInfoRepository;
import com.example.reactordemo.vo.SearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class SearchPageService {

    private final HotInfoRepository hotInfoRepository;
    private final RecommendedInfoRepository recommendedInfoRepository;

    public SearchPageService(HotInfoRepository hotInfoRepository, RecommendedInfoRepository recommendedInfoRepository) {
        this.hotInfoRepository = hotInfoRepository;
        this.recommendedInfoRepository = recommendedInfoRepository;
    }

    public Mono<SearchInfo> getSearchInfoByReactor() {
        SearchInfo searchInfo = new SearchInfo(new ArrayList<>(), new ArrayList<>());

        return Mono.empty();
    }
}

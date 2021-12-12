package com.example.reactordemo.reactive.service;

import com.example.reactordemo.entity.HotInfo;
import com.example.reactordemo.entity.RecommendedInfo;
import com.example.reactordemo.reactive.repository.HotInfoRepositoryFlux;
import com.example.reactordemo.reactive.repository.RecommendedInfoRepositoryFlux;
import com.example.reactordemo.vo.SearchInfoFlux;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SearchPageServiceFlux {

    private final HotInfoRepositoryFlux hotInfoRepositoryFlux;
    private final RecommendedInfoRepositoryFlux recommendedInfoRepositoryFlux;

    public SearchPageServiceFlux(HotInfoRepositoryFlux hotInfoRepositoryFlux, RecommendedInfoRepositoryFlux recommendedInfoRepositoryFlux) {
        this.hotInfoRepositoryFlux = hotInfoRepositoryFlux;
        this.recommendedInfoRepositoryFlux = recommendedInfoRepositoryFlux;
    }

    public Mono<SearchInfoFlux> getSearchInfoByReactor(boolean isTeenager) {
        SearchInfoFlux searchInfoFlux = new SearchInfoFlux();
        searchInfoFlux.setHotInfos(hotInfoRepositoryFlux.getHotInfos()
                .filter(i -> !isTeenager || !i.getSensitive())
                .map(HotInfo::getContent));
        searchInfoFlux.setRecommendedInfos(recommendedInfoRepositoryFlux.getRecommendedInfos()
                .filter(i -> !isTeenager || !i.getSensitive())
                .map(RecommendedInfo::getContent));
        return Mono.just(searchInfoFlux);
    }
}

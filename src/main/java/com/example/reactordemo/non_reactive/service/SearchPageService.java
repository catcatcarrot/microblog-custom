package com.example.reactordemo.non_reactive.service;

import com.example.reactordemo.entity.HotInfo;
import com.example.reactordemo.entity.RecommendedInfo;
import com.example.reactordemo.non_reactive.repository.HotInfoRepository;
import com.example.reactordemo.non_reactive.repository.RecommendedInfoRepository;
import com.example.reactordemo.vo.SearchInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class SearchPageService {

    private final HotInfoRepository hotInfoRepository;
    private final RecommendedInfoRepository recommendedInfoRepository;

    public SearchPageService(HotInfoRepository hotInfoRepository, RecommendedInfoRepository recommendedInfoRepository) {
        this.hotInfoRepository = hotInfoRepository;
        this.recommendedInfoRepository = recommendedInfoRepository;
    }

    public SearchInfo getSearchInfoByFuture(boolean isTeenager) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<List<HotInfo>> hotInfoFuture = executorService.submit(hotInfoRepository::getHotInfos);
        Future<List<RecommendedInfo>> recommendedInfoFuture = executorService.submit(recommendedInfoRepository::getRecommendedInfos);

        List<HotInfo> hotInfos = hotInfoFuture.get();
        List<RecommendedInfo> recommendedInfos = recommendedInfoFuture.get();
        return SearchInfo.builder()
                .hotInfos(hotInfos.stream()
                        .filter(i -> !isTeenager || !i.getSensitive())
                        .limit(5)
                        .collect(Collectors.toList())
                )
                .recommendedInfos(recommendedInfos)
                .build();
    }
}

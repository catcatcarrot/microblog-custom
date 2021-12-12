package com.example.reactordemo.non_reactive.service;

import com.example.reactordemo.entity.HotInfo;
import com.example.reactordemo.entity.RecommendedInfo;
import com.example.reactordemo.non_reactive.repository.HotInfoRepository;
import com.example.reactordemo.non_reactive.repository.RecommendedInfoRepository;
import com.example.reactordemo.vo.SearchInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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
                .hotInfos(getHotInfosContent(isTeenager, hotInfos)
                )
                .recommendedInfos(getRecommendedInfosContent(isTeenager, recommendedInfos))
                .build();
    }

    public SearchInfo getSearchInfoByCompletableFuture(boolean isTeenager) throws ExecutionException, InterruptedException {
        CompletableFuture<List<String>> hotInfosCompletableFuture = CompletableFuture.supplyAsync(hotInfoRepository::getHotInfos)
                .thenApplyAsync(item -> getHotInfosContent(isTeenager, item));
        CompletableFuture<List<String>> recommendedInfosCompletableFuture = CompletableFuture.supplyAsync(recommendedInfoRepository::getRecommendedInfos)
                .thenApplyAsync(item -> getRecommendedInfosContent(isTeenager, item));
        return hotInfosCompletableFuture.
                thenCombineAsync(
                        recommendedInfosCompletableFuture,
                        (hot, rec) -> SearchInfo.builder().hotInfos(hot).recommendedInfos(rec).build()
                )
                .get();
    }

    private List<String> getRecommendedInfosContent(boolean isTeenager, List<RecommendedInfo> recommendedInfos) {
        return recommendedInfos.stream()
                .filter(i -> !isTeenager || !i.getSensitive())
                .map(RecommendedInfo::getContent)
                .collect(Collectors.toList());
    }

    private List<String> getHotInfosContent(boolean isTeenager, List<HotInfo> hotInfos) {
        return hotInfos.stream()
                .filter(i -> !isTeenager || !i.getSensitive())
                .limit(5)
                .map(HotInfo::getContent)
                .collect(Collectors.toList());
    }
}

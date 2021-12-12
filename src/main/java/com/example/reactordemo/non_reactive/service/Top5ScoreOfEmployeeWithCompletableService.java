package com.example.reactordemo.non_reactive.service;

import com.example.reactordemo.entity.Review;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class Top5ScoreOfEmployeeWithCompletableService {

    private final Map<String, Integer> selfScoreWithId = Map.of("1", 1, "2", 2, "3", 3);
    private final Map<String, Integer> otherScoreWithId = Map.of("1", 1, "2", 2, "3", 3);

    public List<String> getTop5ScoreOfEmployee() {
        CompletableFuture<List<String>> ids = getAllEmployeeIds();

        CompletableFuture<List<String>> result = ids.thenComposeAsync(list -> {
            List<CompletableFuture<Review>> combinationList = list.stream()
                    .map(id -> {
                        CompletableFuture<Integer> selfAssessment = getSelfAssessment(id);
                        CompletableFuture<Integer> othersAssessment = getOthersAssessment(id);

                        return selfAssessment.thenCombineAsync(othersAssessment, (sa, oa) -> {
                            double grade = sa * 0.3 + oa * 0.7;
                            return new Review(id, grade);
                        });
                    }).collect(Collectors.toList());

            return CompletableFuture.allOf(combinationList.toArray(new CompletableFuture[0]))
                    .thenApply(v -> combinationList.stream()
                            .map(CompletableFuture::join)
                            .sorted(Comparator.comparingDouble(Review::getGrade).reversed())
                            .limit(5)
                            .map(r -> String.format("Congrats: id: %s -> score: %f", r.getId(), r.getGrade()))
                            .collect(Collectors.toList()));
        });

        return result.join();
    }

    private CompletableFuture<Integer> getOthersAssessment(String id) {
        return CompletableFuture.supplyAsync(() -> otherScoreWithId.getOrDefault(id, 0));
    }

    private CompletableFuture<Integer> getSelfAssessment(String id) {
        return CompletableFuture.supplyAsync(() -> selfScoreWithId.getOrDefault(id, 0));
    }

    private CompletableFuture<List<String>> getAllEmployeeIds() {
        return CompletableFuture.supplyAsync(() -> List.of("1", "2", "3",
                "4", "5", "6", "7", "8"));
    }
}

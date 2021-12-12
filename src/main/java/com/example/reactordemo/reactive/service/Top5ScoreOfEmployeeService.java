package com.example.reactordemo.reactive.service;

import com.example.reactordemo.entity.Review;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Map;

@Service
public class Top5ScoreOfEmployeeService {

    private final Map<String, Integer> selfScoreWithId = Map.of("1", 1, "2", 2, "3", 3);
    private final Map<String, Integer> otherScoreWithId = Map.of("1", 1, "2", 2, "3", 3);

    public Flux<String> getTop5ScoreOfEmployee() {
        Flux<String> employeeIds = getAllEmployeeIds();

        Flux<Review> reviewFlux = employeeIds.flatMap(id -> {
            Mono<Integer> selfAssessment = getSelfAssessment(id);
            Mono<Integer> othersAssessment = getOthersAssessment(id);

            return selfAssessment.zipWith(othersAssessment,
                    (sa, oa) -> {
                        double grade = sa * 0.3 + oa * 0.7;
                        return new Review(id, grade);
                    }
            );
        });

        return reviewFlux.sort(Comparator.comparingDouble(Review::getGrade).reversed())
                .take(5)
                .map(r -> String.format("Congrats: id: %s -> score: %f", r.getId(), r.getGrade()));
    }

    private Mono<Integer> getOthersAssessment(String id) {
        return Mono.just(otherScoreWithId.getOrDefault(id, 0));
    }

    private Mono<Integer> getSelfAssessment(String id) {
        return Mono.just(selfScoreWithId.getOrDefault(id, 0));
    }

    private Flux<String> getAllEmployeeIds() {
        return Flux.just("1", "2", "3",
                "4", "5", "6", "7", "8");
    }

}

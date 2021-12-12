package com.example.reactordemo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchInfoFlux {

    private Flux<String> hotInfos;

    private Flux<String> recommendedInfos;
}

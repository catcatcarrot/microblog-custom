package com.example.reactordemo.controller;

import com.example.reactordemo.reactive.service.SearchPageServiceFlux;
import com.example.reactordemo.vo.SearchInfoFlux;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactor")
public class SearchPageController {

    private final SearchPageServiceFlux searchPageServiceFlux;

    public SearchPageController(SearchPageServiceFlux searchPageServiceFlux) {
        this.searchPageServiceFlux = searchPageServiceFlux;
    }

    @GetMapping
    public Mono<SearchInfoFlux> search() {
        return searchPageServiceFlux.getSearchInfoByReactor(true);
    }
}

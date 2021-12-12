package com.example.reactordemo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchInfo {

    private List<String> hotInfos;
    private List<String> recommendedInfos;
}

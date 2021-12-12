package com.example.reactordemo.vo;

import com.example.reactordemo.entity.HotInfo;
import com.example.reactordemo.entity.RecommendedInfo;
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

    private List<HotInfo> hotInfos;
    private List<RecommendedInfo> recommendedInfos;
}

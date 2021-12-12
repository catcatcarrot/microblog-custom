package com.example.reactordemo.non_reactive.repository;

import com.example.reactordemo.entity.RecommendedInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendedInfoRepository {

    public List<RecommendedInfo> getRecommendedInfos() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return List.of(
                RecommendedInfo.builder().content("按揉两个穴位可以瘦肚子").sensitive(false).build(),
                RecommendedInfo.builder().content("西安确诊1例").sensitive(false).build(),
                RecommendedInfo.builder().content("惊！🍓竟然100一斤").sensitive(false).build(),
                RecommendedInfo.builder().content("辅导女儿作业被气死").sensitive(false).build()
        );
    }
}

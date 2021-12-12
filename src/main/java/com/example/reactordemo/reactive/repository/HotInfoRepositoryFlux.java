package com.example.reactordemo.reactive.repository;

import com.example.reactordemo.entity.HotInfo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Repository
public class HotInfoRepositoryFlux {

    public Flux<HotInfo> getHotInfos() {
        return Flux.fromIterable(Arrays.asList(
                HotInfo.builder().content("双十二退款").sensitive(false).build(),
                HotInfo.builder().content("本人对2022的感受").sensitive(false).build(),
                HotInfo.builder().content("迪士尼").sensitive(true).build(),
                HotInfo.builder().content("狗带给人的幸福感").sensitive(false).build(),
                HotInfo.builder().content("吴磊").sensitive(true).build(),
                HotInfo.builder().content("原来猫咪也会心疼人").sensitive(false).build()
        ));
    }
}

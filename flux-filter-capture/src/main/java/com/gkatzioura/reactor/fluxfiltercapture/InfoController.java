package com.gkatzioura.reactor.fluxfiltercapture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class InfoController {


    @PostMapping("/info")
    public Mono<InfoResponse> getInfo(@RequestBody Info info) {
        return Mono.just(InfoResponse.builder().success(true).build());
    }

}

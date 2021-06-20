package com.gkatzioura.reactor.fluxfiltercapture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class InfoController {

    public static final String APPLICATION_DESCRIPTION = "Application Description";

    @PostMapping("/info")
    public Mono<Info> getInfo(@RequestBody Info info) {
        return Mono.just(info);
    }

}

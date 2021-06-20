package com.gkatzioura.reactor.fluxfiltercapture;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class CustomWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(serverWebExchange);
        return webFilterChain.filter(bodyCaptureExchange).doOnSuccess( (se) -> {
            System.out.println("Body request "+bodyCaptureExchange.getRequest().getFullBody());
            System.out.println("Body response "+bodyCaptureExchange.getResponse().getFullBody());
        });
    }

}


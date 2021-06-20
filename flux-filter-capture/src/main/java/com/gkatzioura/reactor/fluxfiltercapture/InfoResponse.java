package com.gkatzioura.reactor.fluxfiltercapture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoResponse {

    private boolean success;

    public static InfoResponse successful() {
        return InfoResponse.builder().success(true).build();
    }
}

package com.pack_s.mijung_noodles.redis.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Message {

    private String time;
    private String serviceId;
    private String replicaId;
    private String method;
    private MessageRequest request;

    @Builder
    public Message(
        LocalDateTime time,
        String serviceId,
        String replicaId,
        String method,
        MessageRequest request
    ) {
        this.time = time.toString();
        this.serviceId = serviceId;
        this.replicaId = replicaId;
        this.method = method;
        this.request = request;
    }
}

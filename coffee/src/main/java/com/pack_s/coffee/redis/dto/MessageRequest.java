package com.pack_s.coffee.redis.dto;

import com.pack_s.coffee.menu.domain.Menu;
import com.pack_s.coffee.menu.infra.http.response.MenuResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequest {

    private Long key;
    private MenuResponse data;

    @Builder
    public MessageRequest(Long key, Menu data) {
        this.key = key;
        this.data = data != null ? data.toResponse() : null;
    }
}

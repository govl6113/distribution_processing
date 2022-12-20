package com.pack_s.coffee.menu.infra.http.response;

import com.pack_s.coffee.menu.domain.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuResponse {

    Long id;
    String createdAt;
    String updatedAt;
    String name;
    String description;
    Long price;

    @Builder
    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.createdAt = menu.getCreatedAt().toString();
        this.updatedAt = menu.getUpdatedAt().toString();
        this.name = menu.getName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
    }
}

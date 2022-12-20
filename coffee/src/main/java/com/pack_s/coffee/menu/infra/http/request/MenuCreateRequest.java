package com.pack_s.coffee.menu.infra.http.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuCreateRequest {

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotNull
    Long price;

    @Builder
    public MenuCreateRequest(
        String name,
        String description,
        Long price
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

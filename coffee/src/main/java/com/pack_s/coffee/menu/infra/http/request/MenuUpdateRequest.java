package com.pack_s.coffee.menu.infra.http.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MenuUpdateRequest {

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotNull
    Long price;
}
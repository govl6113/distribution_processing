package com.pack_s.coffee.menu.infra.http;

import com.pack_s.coffee.menu.application.MenuServiceImpl;
import com.pack_s.coffee.menu.domain.Menu;
import com.pack_s.coffee.menu.infra.http.request.MenuCreateRequest;
import com.pack_s.coffee.menu.infra.http.request.MenuUpdateRequest;
import com.pack_s.coffee.menu.infra.http.response.MenuResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuServiceImpl menuService;

    @PostMapping("/new")
    public ResponseEntity<MenuResponse> create(
        @Valid @RequestBody MenuCreateRequest request
    ) {
        return ResponseEntity.ok().body(menuService.save(request).toResponse());
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponse> update(
        @PathVariable("menuId") Long menuId,
        @Valid @RequestBody MenuUpdateRequest request
    ) {
        return ResponseEntity.ok().body(menuService.update(menuId, request).toResponse());
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponse> get(
        @PathVariable("menuId") Long menuId
    ) {
        return ResponseEntity.ok().body(menuService.get(menuId).toResponse());
    }

    @GetMapping("/list")
    public ResponseEntity<List<MenuResponse>> getList() {
        return ResponseEntity.ok().body(
            menuService.getList()
                .stream().map(Menu::toResponse)
                .collect(Collectors.toList())
        );
    }

    @DeleteMapping("/{menuId}")
    public Boolean delete(
        @PathVariable("menuId") Long menuId
    ) {
        return menuService.delete(menuId);
    }
}

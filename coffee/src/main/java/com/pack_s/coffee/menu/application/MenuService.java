package com.pack_s.coffee.menu.application;

import com.pack_s.coffee.menu.domain.Menu;
import com.pack_s.coffee.menu.infra.http.request.MenuCreateRequest;
import com.pack_s.coffee.menu.infra.http.request.MenuUpdateRequest;
import java.util.List;

public interface MenuService {

    Menu save(MenuCreateRequest request);

    Menu update(Long menuId, MenuUpdateRequest request);

    Menu get(Long menuId);

    List<Menu> getList();

    Boolean delete(Long menuId);
}

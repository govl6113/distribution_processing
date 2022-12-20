package com.pack_s.coffee.menu.infra.persistence;

import com.pack_s.coffee.menu.domain.Menu;
import java.util.List;
import java.util.Optional;

public interface MenuRepository {

    Menu save(Menu menu);

    Optional<Menu> getById(Long menuId);

    List<Menu> findAll();

    void deleteById(Long menuId);
}

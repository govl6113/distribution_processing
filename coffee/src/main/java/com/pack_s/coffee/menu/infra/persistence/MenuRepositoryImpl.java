package com.pack_s.coffee.menu.infra.persistence;

import com.pack_s.coffee.menu.domain.Menu;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuJpaRepository menuJpaRepository;

    @Override
    public Menu save(Menu menu) {
        return menuJpaRepository.save(menu);
    }

    @Override
    public Optional<Menu> getById(Long menuId) {
        return menuJpaRepository.findById(menuId);
    }

    @Override
    public List<Menu> findAll() {
        return menuJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long menuId) {
        menuJpaRepository.deleteById(menuId);
    }
}

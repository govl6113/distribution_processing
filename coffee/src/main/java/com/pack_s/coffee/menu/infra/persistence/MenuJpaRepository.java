package com.pack_s.coffee.menu.infra.persistence;

import com.pack_s.coffee.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepository extends JpaRepository<Menu, Long> {
}

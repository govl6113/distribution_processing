package com.pack_s.coffee.menu.application;

import com.pack_s.coffee.menu.domain.Menu;
import com.pack_s.coffee.menu.exception.NotFoundMenuException;
import com.pack_s.coffee.menu.infra.http.request.MenuCreateRequest;
import com.pack_s.coffee.menu.infra.http.request.MenuUpdateRequest;
import com.pack_s.coffee.menu.infra.persistence.MenuRepositoryImpl;
import com.pack_s.coffee.redis.application.RedisPubServiceImpl;
import com.pack_s.coffee.redis.dto.Message;
import com.pack_s.coffee.redis.dto.MessageRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepositoryImpl menuRepository;
    private final RedisPubServiceImpl redisPubService;

    @Value("${metadata.service-id}")
    private String serviceId;

    @Value("${metadata.replica-id}")
    private String replicaId;


    @Override
    @Transactional
    public Menu save(MenuCreateRequest request) {
        Menu menu = menuRepository.save(
            Menu.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build()
        );
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("POST")
                .request(
                    MessageRequest.builder()
                        .key(menu.getId())
                        .data(menu)
                        .build()
                )
                .build()
        );
        return menu;
    }

    @Override
    @Transactional
    public Menu update(Long menuId, MenuUpdateRequest request) {
        Menu menu = menuRepository.getById(menuId).orElseThrow(NotFoundMenuException::new);
        Menu updatedMenu = menu.update(request.getName(), request.getDescription(), request.getPrice());

        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("PUT")
                .request(
                    MessageRequest.builder()
                        .key(menu.getId())
                        .data(menu)
                        .build()
                )
                .build()
        );
        return updatedMenu;
    }

    @Override
    public Menu get(Long menuId) {
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("GET")
                .request(
                    MessageRequest.builder()
                        .key(menuId)
                        .data(null)
                        .build()
                )
                .build()
        );
        return menuRepository.getById(menuId).orElseThrow(NotFoundMenuException::new);
    }

    @Override
    public List<Menu> getList() {
        return menuRepository.findAll();
    }

    @Override
    public Boolean delete(Long menuId) {
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("DELETE")
                .request(
                    MessageRequest.builder()
                        .key(menuId)
                        .data(null)
                        .build()
                )
                .build()
        );
        menuRepository.deleteById(menuId);
        return true;
    }
}

package com.pack_s.coffee.menu.domain;

import com.pack_s.coffee.menu.infra.http.response.MenuResponse;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Menu {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Long price;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;


    @Builder
    public Menu(
        String name,
        String description,
        Long price
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Menu update(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
        return this;
    }

    public MenuResponse toResponse() {
        return MenuResponse.builder()
            .menu(this)
            .build();
    }
}

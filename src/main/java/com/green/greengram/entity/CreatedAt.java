package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass //부모클래스랑 매핑 가능하게 함
@EntityListeners(AuditingEntityListener.class) //auditing은 감시
public class CreatedAt {

    @Column(nullable = false)
    @CreatedDate // JPA가 insert때 현재 일시 값 주입
    private LocalDateTime createdAt;

}

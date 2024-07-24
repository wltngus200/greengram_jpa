package com.green.greengram.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdatedAt extends CreatedAt {
    @LastModifiedDate //JPA가 update 때 현재 일시 값을 update
    private LocalDateTime updatedAt;
}

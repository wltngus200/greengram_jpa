package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = { //필드명과 컬럼명 구분
                @UniqueConstraint(columnNames = {"feed_id","user_id"})
        }
)
public class FeedFavorite extends CreatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedFavoriteId;

    @ManyToOne
    @JoinColumn(name="feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}

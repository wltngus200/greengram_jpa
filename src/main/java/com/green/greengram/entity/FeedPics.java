package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FeedPics extends CreatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedPicId;

    @ManyToOne
    @JoinColumn(name="feed_id", nullable = false)
    private Feed feed;

    @Column(length = 70, nullable = false)
    private String pic;


}

package com.green.greengram.feedcomment.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class FeedCommentGetRes {
    private Long feedCommentId; // pk=>feedId와 userId를 알 수 있다
    private String comment; //표시
    private String createdAt;
    //조인
    private String writerNm;
    private String writerPic;
    private Long writerId;
}

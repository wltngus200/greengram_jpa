package com.green.greengram.feedcomment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import java.beans.ConstructorProperties;

@Getter
@EqualsAndHashCode
public class FeedCommentDeleteReq {
    @Schema(name="feed_comment_id") //swagger
    private long feedCommentId;
    @JsonIgnore
    private long signedUserId;

    @ConstructorProperties({"feed_comment_id", "signed_user_id"})
    //쿼리 스트링으로 넘어오니 대문자는 안 쓰고 싶음 위의 에노테이션이 없다면 아래 파라미터와 같은 이름으로 받아야 함
    public FeedCommentDeleteReq(long feedCommentId, long signedUserId) {
        this.feedCommentId = feedCommentId;
        this.signedUserId = signedUserId;
    }
}

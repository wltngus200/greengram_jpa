package com.green.greengram.feedcomment;

import com.green.greengram.entity.FeedComment;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //자동으로 들어감 있어도 없어도 별 영향 없음
//interface 외에도 entity 매니저 등 도 있음
public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {

//    @Query(value = "insert into feed_comment(feedId, userId, comment, created_at) values (:feedId, :userId, :comment, now())",  nativeQuery = true)
//    FeedComment saveFeedComment(Long feedId, Long userId, String comment);
//
//    @Query(value = "select fc from FeedComment fc where fc.feed.feedId=:feedId")
//    List<FeedCommentGetRes> findFeedCommentsByFeedId(Long feedId);
//
//    @Query(value="delete from FeedComment fc where fc.feed.feedId=:feedId AND fc.user.userId=:userId", nativeQuery = true)
//    int deleteByComment(Long feedId, Long userId);

}

package com.green.greengram.feedcomment;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentGetResInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //자동으로 들어감 있어도 없어도 별 영향 없음
//interface 외에도 entity 매니저 등 도 있음
public interface FeedCommentRepository extends JpaRepository<FeedComment/*쿼리메소드들의 리턴값*/, Long> {

//    @Query(value = "insert into feed_comment(feedId, userId, comment, created_at) values (:feedId, :userId, :comment, now())",  nativeQuery = true)
//    FeedComment saveFeedComment(Long feedId, Long userId, String comment);
//
    //쿼리 메소드 : 리턴 값이 위와 다를 경우 오브젝트 리스트 <Object[]>
    //네이티브 쿼리
    @Query(value = " SELECT fc.feed_comment_id as feedCommentId, fc.comment, fc.created_at as createdAt" +
            " , u.user_id as writerId, u.nm as writerNm, u.pic as writerPic  " +
            " FROM feed_comment fc " +
            " INNER JOIN feed f ON fc.feed_id = f.feed_id " +
            " INNER JOIN user u ON fc.user_id = u.user_id " +
            " WHERE f.feed_id = :feedId " +
            " ORDER BY fc.feed_comment_id ASC" +
            " LIMIT 3, 9999 ", nativeQuery = true)
    List<FeedCommentGetResInterface> findAllByFeedCommentLimit3AndInfinity (Long feedId);

    //find = get FeedId가 WHERE절에 들어감 ORDER BY 정렬, ASC 정렬
//
//    @Query(value="delete from FeedComment fc where fc.feed.feedId=:feedId AND fc.user.userId=:userId", nativeQuery = true)
//    int deleteByComment(Long feedId, Long userId);


}

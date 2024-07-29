package com.green.greengram.feedfavorite;

import com.green.greengram.entity.FeedFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedFavoriteRepository extends JpaRepository<FeedFavorite, Long> {
            //AS 준 테이블의 모든 컬럼 출력
    @Query("select ff from FeedFavorite ff where ff.feed.feedId = :feedId and ff.user.userId=:userId")
    FeedFavorite findFeedFavoriteByFeedIdAndSignedUserId(Long feedId/*where절의 :뒤와 이름이 같아야 함*/, Long userId);
                                                        //순서가 중요
    @Query(value="insert into feed_favorite(feed_id, user_id, created_at) VALUES (:feedId, :userId, now())", nativeQuery = true)
    void saveFeedFavorite(Long feedId, Long userId);
}

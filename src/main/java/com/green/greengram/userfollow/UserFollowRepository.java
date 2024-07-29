package com.green.greengram.userfollow;

import com.green.greengram.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
                                                          /*Entity , PK Type*/

    //JPQL -> 영속성 없이 바로 PK주는 것도 가능
    @Query("select uf from UserFollow uf where uf.fromUser.userId = :fromUserId and uf.toUser.userId = :toUserId")
    UserFollow findUserFollowByFromUserAndToUser(Long fromUserId, Long toUserId);
}

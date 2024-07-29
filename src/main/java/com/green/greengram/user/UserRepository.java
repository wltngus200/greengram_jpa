package com.green.greengram.user;

import com.green.greengram.entity.User;
import com.green.greengram.security.SignInProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>/*Entity, PK Type*/ /*어지간한 건 구현 되어있음*/{
    //쿼리 메소드 : 메소드 이름으로 쿼리 생성
    //get(find)를 붙이면 SELECT가 됨 User는 Entity명 BY where절
    //Order By, And 등 도 가능
    //User getUserByUid/*가져오고 싶은 컬럼이름*/(String uid);
    //SELECT * FROM User WHERE uid=#{uid}

    //파라미터 순서가 중요
    User findUserByProviderTypeAndUid(SignInProviderType/*Enum*/ providerType, String uid);
    User findUserByUserId(long userId);
}

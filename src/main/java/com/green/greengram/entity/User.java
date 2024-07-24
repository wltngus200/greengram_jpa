package com.green.greengram.entity;

import com.green.greengram.security.SignInProviderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity //이 class가 Entity(=table)로 사용될 것이란 의미
@Getter
@Setter
public class User extends UpdatedAt{
    @Id //PK라는 표시
    @GeneratedValue(strategy= GenerationType.IDENTITY) //my batis maria DB만 가능, Oracle은 sequence줘야함
    private Long userId;
    // wapper 타입 권장

    @ColumnDefault("4")//디폴트 값
    @Column(nullable = false)
    private SignInProviderType providerType;
    @Column (length=50, nullable = false) //속성 주고 싶을 때
    private String uid;
    @Column (length=100, nullable = false) //속성 주고 싶을 때
    private String upw;
    @Column (length=50, nullable = false) //속성 주고 싶을 때
    private String nm;
    @Column (length=200) //속성 주고 싶을 때
    private String pic;

}

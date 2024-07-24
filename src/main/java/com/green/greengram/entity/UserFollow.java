package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"from_user_id", "to_user_id"})})
public class UserFollow extends CreatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFollowId;


    //from 과 to Long 타입을 줘야할 것 같지만 객체 지향적으로 생각하면 FK는 User의 PK값이 들어가야함

    @ManyToOne //many가 클래스 one이 멤버필드
    @JoinColumn(name="from_user_id") //컬럼명
    private User fromUser; //1:다 관계 //필드명으로만 존재

    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User toUser;

}

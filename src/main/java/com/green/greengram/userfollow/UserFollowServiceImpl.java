package com.green.greengram.userfollow;

import com.green.greengram.entity.User;
import com.green.greengram.entity.UserFollow;
import com.green.greengram.entity.UserRole;
import com.green.greengram.security.AuthenticationFacade;
import com.green.greengram.security.MyUser;
import com.green.greengram.security.SignInProviderType;
import com.green.greengram.user.UserRepository;
import com.green.greengram.user.UserRoleRepository;
import com.green.greengram.userfollow.model.UserFollowReq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFollowServiceImpl implements UserFollowService {
    private final UserFollowMapper mapper;
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final UserFollowRepository followRepository;

    public int postUserFollow(UserFollowReq p){
        p.setFromUserId(authenticationFacade.getLoginUserId());
        UserFollow follow=new UserFollow();
        User fromUser=userRepository.findUserByUserId(authenticationFacade.getLoginUserId());
        User toUser=userRepository.findUserByUserId(p.getToUserId());
        follow.setFromUser(fromUser);
        follow.setToUser(toUser);
        followRepository.save(follow);
        return 1;
    }
    public int deleteUserFollow(UserFollowReq p){
        p.setFromUserId(authenticationFacade.getLoginUserId());
        UserFollow follow=new UserFollow();
        User fromUser=userRepository.findUserByUserId(authenticationFacade.getLoginUserId());
        User toUser=userRepository.findUserByUserId(p.getToUserId());
        follow.setFromUser(fromUser);
        follow.setToUser(toUser);
        followRepository.delete(follow);
        return 1;
    }
}

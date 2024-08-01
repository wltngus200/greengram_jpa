package com.green.greengram.feedcomment;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import com.green.greengram.exception.CustomException;
import com.green.greengram.exception.MemberErrorCode;
import com.green.greengram.feed.FeedRepository;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetResInterface;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.security.AuthenticationFacade;
import com.green.greengram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedCommentServiceImpl implements FeedCommentService{
    private final FeedCommentMapper mapper;
    private final AuthenticationFacade authenticationFacade;
    private final FeedCommentRepository repository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    public long insFeedComment(FeedCommentPostReq p){
        //FeedComment fc=repository.saveFeedComment(p.getFeedId(), authenticationFacade.getLoginUserId(), p.getComment());
        //p.setUserId(authenticationFacade.getLoginUserId()); -> PK값을 못 받아옴
        //int affectedRows= mapper.insFeedComment(p); -> mybatis
        //영속성을 가진 Entity들 -> fk를 위해서 영속성이 있는 상태여야함
        Feed feed=feedRepository.getReferenceById(p.getFeedId());
        User user=userRepository.getReferenceById(authenticationFacade.getLoginUserId());


        FeedComment fc=new FeedComment();
        fc.setFeed(feed);
        fc.setUser(user);
        fc.setComment(p.getComment());

        FeedComment fc2=repository.save(fc);

        log.info("fc equals fc2: {}", fc==fc2); // ==비교는 주소값 비교
        return fc.getFeedCommentId();
    }

    public int deleteFeedComment(FeedCommentDeleteReq p){

        FeedComment fc=repository.getReferenceById(p.getFeedCommentId());
        //fc.getUser().getUserId()를 그래프 탐색이라 호칭
        if(fc.getUser().getUserId()!=authenticationFacade.getLoginUserId()){//로그인 사용자와 같은지
            throw new CustomException(MemberErrorCode.UNAUTHENTICATED);
        }
        repository.delete(fc);

        return 1;
    }

    @Override
    public List<FeedCommentGetResInterface> feedCommentListGet(long feedId) {
        return repository.findAllByFeedCommentLimit3AndInfinity(feedId);
    }
}

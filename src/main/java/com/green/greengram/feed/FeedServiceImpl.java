package com.green.greengram.feed;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedPics;
import com.green.greengram.entity.User;
import com.green.greengram.feed.model.*;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.security.AuthenticationFacade;
import com.green.greengram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.green.greengram.common.GlobalConst.COMMENT_SIZE_PER_FEED;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final FeedRepository repository;
    private final UserRepository userRepository;
    private final FeedPicsRepository feedPicsRepository;

    @Transactional
    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p){
        //p.setUserId(authenticationFacade.getLoginUserId());

        User signedUser=userRepository.getReferenceById(authenticationFacade.getLoginUserId());

        Feed feed=new Feed();
        feed.setUser(signedUser);
        feed.setContents(p.getContents());
        feed.setLocation(p.getLocation());
        feed.setCreatedAt(LocalDateTime.now());

        repository.save(feed); //PK값 자동으로 들어감
        //int result=mapper.postFeed(p); //내용과 위치를 데이터베이스에 올림

        if(pics==null){
            return FeedPostRes.builder()
                    .feedId(feed.getFeedId())
                    .build();
        }
        //DB에 사진  저장 //Builder가 있다!
        //사진을 올리기 위해 요구되는 정보는 multipartfile과 FeedPostReq에 존재
        //그것을 재구성 해서 PicReq를 제조 Req에는 이미 객체화 되어있다
        FeedPostPicReq req= FeedPostPicReq.builder()
                            .feedId(feed.getFeedId()).build();//PK값 세팅

        List<String> picList=new ArrayList();
        try {
            List<FeedPics> feedPicsList=new ArrayList();
            String path=String.format("feed/%s", p.getFeedId());
            customFileUtils.makeFolders(path);//폴더 생성

            for(MultipartFile pic:pics) {
                String fileName= customFileUtils.makeRandomFileName(pic);
                String target=String.format("%s/%s", path, fileName);
                customFileUtils.transferTo(pic, target);

                picList.add(fileName);

                FeedPics feedPics=new FeedPics();
                feedPicsList.add(feedPics);

                feedPics.setFeed(feed);
                feedPics.setPic(fileName);
            }feedPicsRepository.saveAll(feedPicsList);
            /*
            JPA Batch Insert(대량 insert)는 쓰기 지연을 사용해 동작.
            이는 DB에 insert 후 id가 할당되는 identity 전략을 사용하면 사용할 수 없다는 의미
            yaml 파일에 " # JPA Batch 작업(Insert, Update) " 주석인 부분과
            DB URL에 파라미터값 추가 " rewriteBatchedStatements=true "
             */

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(".·´¯`(>▂<)´¯`·. 업로드에 실패해쪙");
        }
        return FeedPostRes.builder().feedId(req.getFeedId()).pics(req.getFileNames()).build();
    }

    public List<FeedGetRes> getFeed(FeedGetReq p){
        p.setSignedUserId(authenticationFacade.getLoginUserId());
        List<FeedGetRes> list=mapper.getFeed(p);
        for(FeedGetRes res:list){
            //사진
            List<String> pics=mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);

            //코멘트
            List<FeedCommentGetRes> comments=mapper.getFeedComment(res.getFeedId());
            if(comments.size()== COMMENT_SIZE_PER_FEED){
                res.setIsMoreComment(1);
                comments.remove(COMMENT_SIZE_PER_FEED-1);
            }
            res.setComments(comments);
        }

        return list;
    }

    public int deleteFeed(long feedId){
        String.format("");
        customFileUtils.deleteFolder("");
        return mapper.deleteFeed(feedId);
    }
}

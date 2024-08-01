package com.green.greengram.feedcomment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.greengram.common.model.MyResponse;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentGetResInterface;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/comment")
@Tag(name="피드 댓글")
public class FeedCommentControllerImpl implements FeedCommentController {
    private final FeedCommentService service;
    private final ObjectMapper om;

    @PostMapping
    public void postFeedComment(HttpServletResponse res, @RequestBody FeedCommentPostReq p) throws IOException {
        //long result=service.insFeedComment(p);
        long result= 10;
        MyResponse<Long> obj=MyResponse.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("악플 ᕦ(ò_óˇ)ᕤ 안돼!")
                .resultData(result)
                .build();
        String json=om.writeValueAsString(obj);

        res.resetBuffer();
        res.setStatus(200);
        res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.setCharacterEncoding("UTF-8");
        res.getOutputStream().write(json.getBytes("UTF-8"));
        res.flushBuffer();

    }


    @DeleteMapping
    public MyResponse<Integer> deleteFeedComment(@ParameterObject @ModelAttribute FeedCommentDeleteReq p){
        //@RequestBody(Json, 파일)=Post Put에 적절(노출 되어서는 안 되는 데이터)
        //@PathVariable(적은 데이터)=Get Delete에 적절
        int result=service.deleteFeedComment(p);
        return MyResponse.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("ψ(._. )> 가는 거야?")
                .resultData(result)
                .build();
    }
    @GetMapping
    public MyResponse<List<FeedCommentGetResInterface>> feedCommentListGet(long feedId){
        List<FeedCommentGetResInterface> result=service.feedCommentListGet(feedId);
        return MyResponse.<List<FeedCommentGetResInterface>>builder()
                .statusCode(HttpStatus.OK)
                .resultData(result)
                .resultMsg("ヽ(゜▽゜　)－")
                .build();

    }
}


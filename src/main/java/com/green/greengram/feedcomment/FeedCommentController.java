package com.green.greengram.feedcomment;

import com.green.greengram.common.model.MyResponse;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public interface FeedCommentController {
    //MyResponse<Long> insFeedComment(@RequestBody FeedCommentPostReq p);
    void postFeedComment(HttpServletResponse res, FeedCommentPostReq p) throws IOException;
    MyResponse<Integer> deleteFeedComment(FeedCommentDeleteReq p);
    MyResponse<List<FeedCommentGetRes>> feedCommentListGet(long feedId);
}

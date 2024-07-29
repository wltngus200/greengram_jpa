package com.green.greengram.feedfavorite;

import com.green.greengram.entity.FeedFavorite;
import com.green.greengram.entity.User;
import com.green.greengram.feed.FeedRepository;
import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import com.green.greengram.security.AuthenticationFacade;
import com.green.greengram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedFavoriteServiceImpl implements FeedFavoriteService {
    private final FeedFavoriteMapper mapper;
    private final AuthenticationFacade authenticationFacade;
    private final FeedFavoriteRepository repository;

    public int toggleFavorite(FeedFavoriteToggleReq p){
        FeedFavorite feedFavorite=repository.findFeedFavoriteByFeedIdAndSignedUserId(p.getFeedId(), authenticationFacade.getLoginUserId());
        if(feedFavorite==null){
            repository.saveFeedFavorite(p.getFeedId(), authenticationFacade.getLoginUserId());
            return 1;
        }
        repository.delete(feedFavorite);
        return 1;
    }

}

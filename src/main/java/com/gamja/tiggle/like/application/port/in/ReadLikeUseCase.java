package com.gamja.tiggle.like.application.port.in;

import com.gamja.tiggle.like.adapter.in.web.response.ReadMyLikeResponse;
import com.gamja.tiggle.user.domain.User;

import java.util.List;

public interface ReadLikeUseCase {
    List<ReadMyLikeResponse> readMyLikes(User user);
}

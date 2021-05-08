package com.crio.starter.service;

import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.exchanges.PostMemeRequest;

public interface MemeService {
    public PostMemeResponse postMeme(PostMemeRequest postMemeRequest);
}

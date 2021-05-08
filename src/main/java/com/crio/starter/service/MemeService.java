package com.crio.starter.service;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;

public interface MemeService {
  public PostMemeResponse postMeme(PostMemeRequest postMemeRequest);
}

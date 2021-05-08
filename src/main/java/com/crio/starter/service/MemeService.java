package com.crio.starter.service;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import java.util.List;


public interface MemeService {
  public PostMemeResponse postMeme(PostMemeRequest postMemeRequest);
  
  public List<Meme> getLatestMemes();
}

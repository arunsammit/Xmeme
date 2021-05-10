package com.crio.starter.service;

import com.crio.starter.exceptions.DublicateMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.exchanges.GetMemeResponse;
import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import java.time.LocalDateTime;
import java.util.List;



public interface MemeService {
  public PostMemeResponse postMeme(PostMemeRequest postMemeRequest, LocalDateTime dateTime) 
      throws DublicateMemeException;
  
  public List<GetMemeResponse> getLatestMemes(int memeCnt);

  public GetMemeResponse getMemeById(String id) throws MemeNotFoundException;
}

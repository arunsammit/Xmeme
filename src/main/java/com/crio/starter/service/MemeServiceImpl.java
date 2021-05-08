package com.crio.starter.service;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.repositoryservice.RepositoryService;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemeServiceImpl implements MemeService {
  
  private final RepositoryService repositoryService; 

  @Override
  public PostMemeResponse postMeme(PostMemeRequest postMemeRequest) {
    UUID id = UUID.randomUUID();
    Meme meme = new Meme(id.toString(), 
        postMemeRequest.getName(),postMemeRequest.getUrl(),postMemeRequest.getCaption());
    repositoryService.saveMeme(meme);
    PostMemeResponse postMemeResponse = new PostMemeResponse(id.toString());
    return postMemeResponse;
  }

  @Override
  public List<Meme> getLatestMemes() {
    // TODO Auto-generated method stub
    return null;
  }
  
}

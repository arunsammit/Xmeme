package com.crio.starter.service;

import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.exchanges.GetMemeResponse;
import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.repositoryservice.RepositoryService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService {
  
  private final RepositoryService repositoryService; 
  private final ModelMapper modelMapper;
  /** Constructor.
   * @param repositoryService repositoryService
   * @param modelMapper modelMapper
   */

  public MemeServiceImpl(RepositoryService repositoryService, ModelMapper modelMapper) {
    this.repositoryService = repositoryService;
    this.modelMapper = modelMapper;
    modelMapper.typeMap(Meme.class, GetMemeResponse.class)
        .addMapping(Meme::getMemeId, GetMemeResponse::setId);
  }

  @Override
  public PostMemeResponse postMeme(PostMemeRequest postMemeRequest, LocalDateTime dateTime) {
    UUID id = UUID.randomUUID();
    Meme meme = modelMapper.map(postMemeRequest, Meme.class);
    meme.setMemeId(id.toString());
    meme.setDateTime(dateTime);
    repositoryService.saveMeme(meme);
    PostMemeResponse postMemeResponse = new PostMemeResponse(id.toString());
    return postMemeResponse;
  }

  @Override
  public List<GetMemeResponse> getLatestMemes(int memeCnt) {
    List<Meme> memes = repositoryService.getRecentMemes(memeCnt);
    List<GetMemeResponse> memeResponses = memes.stream()
        .map(x -> modelMapper.map(x, GetMemeResponse.class))
        .collect(Collectors.toList());

    return memeResponses;
  }

  @Override
  public GetMemeResponse getMemeById(String id) {
    Meme meme = repositoryService.getMemeById(id);
    if (meme == null) {
      throw new MemeNotFoundException(
          "repositoryService layer couldn't find the meme corresponding to given id",
          new RuntimeException());
    }
    GetMemeResponse memeResponse = modelMapper.map(meme, GetMemeResponse.class);
    return memeResponse;
  }
  
}

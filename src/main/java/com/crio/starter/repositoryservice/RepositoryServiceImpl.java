package com.crio.starter.repositoryservice;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.model.Meme;
import com.crio.starter.repository.MemeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RepositoryServiceImpl implements RepositoryService {
  private final MemeRepository memeRepository;
  private final ModelMapper modelMapper;

  @Override
  public void saveMeme(Meme meme) {
    MemeEntity memeEntity = modelMapper.map(meme, MemeEntity.class);
    memeRepository.save(memeEntity);
  }

  @Override
  public List<Meme> getRecentMemes(int maxCnt) {
    Page<MemeEntity> memePage = 
        memeRepository.findAll(PageRequest.of(0,maxCnt,Sort.Direction.DESC,"dateTime"));
    List<Meme> memeList = memePage.stream().map(
        x -> modelMapper.map(x, Meme.class))
        .collect(Collectors.toList());
    return memeList;
  }

  @Override
  public Meme getMemeById(String id) {
    MemeEntity memeEntity = 
        memeRepository.findOneByMemeId(id);
    Meme meme = modelMapper.map(memeEntity, Meme.class);
    return meme;
  }
    
}

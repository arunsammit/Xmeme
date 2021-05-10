package com.crio.starter.repositoryservice;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.model.Meme;
import com.crio.starter.repository.MemeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
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
  public Meme getMemeById(String id) throws MemeNotFoundException {
    MemeEntity memeEntity = 
        memeRepository.findOneByMemeId(id);
    if (memeEntity == null) {
      throw new MemeNotFoundException("id is not valid");
    }
    Meme meme = modelMapper.map(memeEntity, Meme.class);
    return meme;
  }

  @Override
  public Boolean memeExists(Meme meme) {
    MemeEntity entity = new MemeEntity(null,
        meme.getMemeId(),
        meme.getName(),
        meme.getUrl(),
        meme.getCaption(),
        meme.getDateTime());
    Example<MemeEntity> example = Example.of(entity);
    Boolean exists = memeRepository.exists(example);
    return exists;
  }
    
}

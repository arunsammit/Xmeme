package com.crio.starter.repositoryservice;


import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.repository.MemeRepository;

import org.modelmapper.ModelMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private final MemeRepository memeRepository;
    private final ModelMapper modelMapper;
    @Override
    public void saveMeme(Meme meme) {
        MemeEntity memeEntity = modelMapper.map(meme, MemeEntity.class);
        memeRepository.save(memeEntity);
    }
    
}

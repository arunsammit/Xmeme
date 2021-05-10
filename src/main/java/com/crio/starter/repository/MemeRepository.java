package com.crio.starter.repository;

import com.crio.starter.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<MemeEntity, String> {
  
  MemeEntity findOneByMemeId(String memeId);
  
  long deleteOneByMemeId(String lastname);
}
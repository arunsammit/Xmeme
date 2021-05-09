package com.crio.starter.repository;

import com.crio.starter.data.MemeEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<MemeEntity, String> {

}

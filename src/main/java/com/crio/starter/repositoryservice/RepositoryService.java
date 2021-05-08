package com.crio.starter.repositoryservice;

import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;

public interface RepositoryService {
    void saveMeme(Meme meme);
}

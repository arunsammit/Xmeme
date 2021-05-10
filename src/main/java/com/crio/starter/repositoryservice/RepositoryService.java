package com.crio.starter.repositoryservice;

import com.crio.starter.exceptions.DublicateMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.model.Meme;
import java.util.List;



public interface RepositoryService {
  void saveMeme(Meme meme) throws DublicateMemeException;

  List<Meme> getRecentMemes(int maxCnt);

  Meme getMemeById(String id) throws MemeNotFoundException;
  
  Boolean memeExists(Meme meme);
}

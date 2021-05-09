package com.crio.starter.repositoryservice;

import com.crio.starter.model.Meme;
import java.util.List;



public interface RepositoryService {
  void saveMeme(Meme meme);

  List<Meme> getRecentMemes(int maxCnt);
}

package com.crio.starter.controller;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.service.MemeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemeController {

  private final MemeService memeService;


  @PostMapping("/memes")
  public PostMemeResponse postMeme(@RequestBody PostMemeRequest postMemeRequest) {
    return memeService.postMeme(postMemeRequest);
  }

  @GetMapping("/memes")
  public List<Meme> getMemes() {
    return memeService.getLatestMemes();
  }
  
}

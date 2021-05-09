package com.crio.starter.controller;

import com.crio.starter.exchanges.GetMemeResponse;
import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.service.MemeService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemeController {

  private final MemeService memeService;
  private final int memeCnt = 100;


  @PostMapping("/memes")
  public PostMemeResponse postMeme(@RequestBody PostMemeRequest postMemeRequest) {
    LocalDateTime currentDateTime = LocalDateTime.now();
    return memeService.postMeme(postMemeRequest,currentDateTime);
  }

  @GetMapping("/memes")
  public List<GetMemeResponse> getMemes() {
    return memeService.getLatestMemes(memeCnt);
  }

  @GetMapping("/memes/{id}")
  public GetMemeResponse getMemeById(@PathVariable String id) {
    return memeService.getMemeById(id);
  }
  
}

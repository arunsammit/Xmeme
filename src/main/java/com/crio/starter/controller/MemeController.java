package com.crio.starter.controller;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.service.MemeService;
import com.crio.starter.service.MemeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemeController {

  private final MemeService memeService;


  @PostMapping("/meme")
  public PostMemeResponse postMeme(@RequestBody PostMemeRequest postMemeRequest) {
    return memeService.postMeme(postMemeRequest);
  }
}

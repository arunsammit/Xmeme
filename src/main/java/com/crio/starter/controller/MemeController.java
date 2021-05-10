package com.crio.starter.controller;

import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.exchanges.GetMemeResponse;
import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.service.MemeService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

  /**Post a meme.
   * 
   * @param postMemeRequest The request object
   * @return
   */
  @PostMapping("/memes")
  public PostMemeResponse postMeme(@RequestBody PostMemeRequest postMemeRequest) {
    if (postMemeRequest.getName() == null 
        || postMemeRequest.getUrl() == null
        || postMemeRequest.getCaption() == null) {
      throw new IllegalArgumentException();
    }
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
  
  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<String> onIncorrectParameterException(IllegalArgumentException ex){
    return ResponseEntity.badRequest().body(
      "invalid request: some parameters are missing in the post request\n");
  }

  @ExceptionHandler(MemeNotFoundException.class)
  ResponseEntity<String> onIncorrectParameterException(MemeNotFoundException ex){
    return ResponseEntity.notFound().build();
  }

}

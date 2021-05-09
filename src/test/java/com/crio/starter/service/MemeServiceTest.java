package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.crio.starter.App;
import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.repositoryservice.RepositoryService;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
class MemeServiceTest {

  @MockBean
  private RepositoryService repositoryService;
  @Autowired
  private MemeService memeService;

  @Test
  void postMemeTest() throws MalformedURLException {
    //Given
    String name = "ram";
    URL url = new URL("http://falcon.fly/wings.jpg");
    String caption = "wings of freedom";
    LocalDateTime dateTime = LocalDateTime.of(1999,2,3,18,0);
    PostMemeRequest memeRequest = new PostMemeRequest(name,url,caption);
    //When
    ArgumentCaptor<Meme> valueCapture = ArgumentCaptor.forClass(Meme.class);
    final PostMemeResponse memeResponse = memeService.postMeme(memeRequest,dateTime);
    verify(repositoryService).saveMeme(valueCapture.capture());
    Meme meme = valueCapture.getValue();
    //Then
    final PostMemeResponse expected = new PostMemeResponse(meme.getMemeId());
    assertEquals(name, meme.getName());
    assertEquals(url.toString(), meme.getUrl());
    assertEquals(caption, meme.getCaption());
    assertEquals(dateTime, meme.getDateTime());
    assertEquals(expected, memeResponse);
  }

  @Test
  void getLatestMemesTest(){
    //TODO: Test MemeServiceImpl::getLatestMemes method

  }
}
package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.crio.starter.App;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.repositoryservice.RepositoryService;
import com.crio.starter.model.Meme;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class MemeServiceTest {

  @Mock
  private RepositoryService repositoryService;
  @InjectMocks
  private MemeServiceImpl memeService;

  @Test
  void postMemeTest() throws MalformedURLException {
    //Given
    String name = "ram";
    URL url = new URL("http://falcon.fly/wings.jpg");
    String caption = "wings of freedom";
    PostMemeRequest memeRequest = new PostMemeRequest(name,url,caption);
    //When
    ArgumentCaptor<Meme> valueCapture = ArgumentCaptor.forClass(Meme.class);
    PostMemeResponse memeResponse = memeService.postMeme(memeRequest);
    verify(repositoryService).saveMeme(valueCapture.capture());
    Meme meme = valueCapture.getValue();
    //Then
    PostMemeResponse expected = new PostMemeResponse(meme.getMemeId());
    assertEquals(name, meme.getName());
    assertEquals(url.toString(), meme.getUrl());
    assertEquals(caption, meme.getCaption());
    assertEquals(expected, memeResponse);

  }
}
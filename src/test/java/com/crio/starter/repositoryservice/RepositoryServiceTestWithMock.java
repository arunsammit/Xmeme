package com.crio.starter.repositoryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import com.crio.starter.App;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.model.Meme;
import com.crio.starter.repository.MemeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/** Unit tests for RepositoryServiceImpl.java .
 * RepositoryServiceUnitTest
 */
@SpringBootTest(classes = {App.class})
@DirtiesContext
@ActiveProfiles("test")
public class RepositoryServiceTestWithMock {
  @MockBean MemeRepository memeRepository;
  @Autowired RepositoryService repositoryService;

  @Test
  void saveMemeTest() throws IOException {
    //given
    UUID id = UUID.randomUUID();
    Meme expected = new Meme(id.toString(),"madan",
        "http://flt.falt/p.jpg",
        "he he he", 
        LocalDateTime.of(1800, 11, 13, 12, 00, 00));
    //when
    repositoryService.saveMeme(expected);
    //then
    ArgumentCaptor<MemeEntity> argumentCaptor =
        ArgumentCaptor.forClass(MemeEntity.class);
    verify(memeRepository).save(argumentCaptor.capture());

    MemeEntity actual = argumentCaptor.getValue();
    assertEquals(expected.getMemeId(),actual.getMemeId());
    assertEquals(expected.getName(),actual.getName());
    assertEquals(expected.getUrl(),actual.getUrl());
    assertEquals(expected.getCaption(),actual.getCaption());
    assertEquals(expected.getDateTime(),actual.getDateTime());
  }

  @Test
  void getMemeByIdTest() {
    //given
    UUID id = UUID.randomUUID();
    MemeEntity expected = new MemeEntity(id.toString(),
        id.toString(),"madan",
        "http://flt.falt/p.jpg",
        "he he he", 
        LocalDateTime.of(1800, 11, 13, 12, 00, 00));
    Mockito.doReturn(expected).when(memeRepository)
        .findOneByMemeId("23");
    //when
    Meme actual = repositoryService.getMemeById("23");
    //then
    assertEquals(expected.getMemeId(),actual.getMemeId());
    assertEquals(expected.getName(),actual.getName());
    assertEquals(expected.getUrl(),actual.getUrl());
    assertEquals(expected.getCaption(),actual.getCaption());
    assertEquals(expected.getDateTime(),actual.getDateTime());

  }

}
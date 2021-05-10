package com.crio.starter.repositoryservice;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.crio.starter.App;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.model.Meme;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.utils.FixtureHelpers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = {App.class})
@DirtiesContext
@ActiveProfiles("test")
public class RepositoryServiceTest {
  private static final String FIXTURES = "fixtures/exchanges";
  @Autowired
  private RepositoryService repositoryService;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private MemeRepository memeRepository;

  @BeforeEach
  void setup() throws IOException {
    List<MemeEntity> entities = listOfMemeEntity();
    entities.forEach(x -> mongoTemplate.save(x));
  }

  @AfterEach
  void teardown() throws IOException {
    mongoTemplate.dropCollection("meme");
  }
  
  private List<MemeEntity> listOfMemeEntity() throws IOException {
    String fixture =
        FixtureHelpers.fixture(FIXTURES + "/initial_list_of_meme_entity.json");
    return objectMapper.readValue(fixture, new TypeReference<List<MemeEntity>>(){});
  }

  private List<Meme> listOfMeme() throws IOException {
    String fixture =
        FixtureHelpers.fixture(FIXTURES + "/meme_list.json");
    return objectMapper.readValue(fixture, new TypeReference<List<Meme>>(){});
  }

  @Test
  void getRecentMemesTest() throws IOException {
    List<Meme> memes = 
        listOfMemeEntity().stream()
        .map(x -> modelMapper.map(x,Meme.class))
        .collect(Collectors.toList());
    Collections.reverse(memes);
    List<Meme> memesExpected = memes.subList(0,10);
    List<Meme> memesActual = repositoryService.getRecentMemes(10);
    assertEquals(memesExpected, memesActual);
  }

  @Test
  void saveMemeTest() throws IOException {
    //TODO: implement test for saveMeme method
    //given
    UUID id = UUID.randomUUID();
    Meme expected = new Meme(id.toString(),"madan",
        "http://flt.falt/p.jpg",
        "he he he", 
        LocalDateTime.of(1800, 11, 13, 12, 00, 00));
    //when
    repositoryService.saveMeme(expected);
    //then
    MemeEntity actual = memeRepository.findOneByMemeId(id.toString());
    assertEquals(expected.getMemeId(),actual.getMemeId());
    assertEquals(expected.getName(),actual.getName());
    assertEquals(expected.getUrl(),actual.getUrl());
    assertEquals(expected.getCaption(),actual.getCaption());
    assertEquals(expected.getDateTime(),actual.getDateTime());
  }
  
  void getMemeByIdTest() {
    //TODO: implement test for getMemeById method
  }
}

package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.model.Meme;
import com.crio.starter.service.MemeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;


@WebMvcTest(MemeController.class)
class MemeControllerTest {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MemeServiceImpl memeService;

  @Test
  void sayHello() throws Exception {
    //given
    PostMemeRequest memeRequest = new PostMemeRequest("ram",new URL("http://falcon.com/img1"),"here is the pain");
    Mockito.doReturn(new PostMemeResponse("23"))
        .when(memeService).postMeme(memeRequest);

    // when

    MockHttpServletResponse response = mvc.perform(
        post("/meme").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(memeRequest)))
        .andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    PostMemeResponse responseDto = objectMapper.readValue(responseStr, PostMemeResponse.class);
    PostMemeResponse ref = new PostMemeResponse("23");

    assertEquals(responseDto, ref);
    Mockito.verify(memeService, Mockito.times(1)).postMeme(memeRequest);
  }
}
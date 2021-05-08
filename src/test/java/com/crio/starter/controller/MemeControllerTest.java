package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.crio.starter.exchanges.PostMemeRequest;
import com.crio.starter.exchanges.PostMemeResponse;
import com.crio.starter.service.MemeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(MemeController.class)
class MemeControllerTest {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MemeServiceImpl memeService;

  @Test
  void postMemeTest() throws Exception {
    //given
    PostMemeRequest memeRequest = new PostMemeRequest("ram",new URL("http://falcon.com/img1"),"here is the pain");
    Mockito.doReturn(new PostMemeResponse("23"))
        .when(memeService).postMeme(memeRequest);

    // when

    MockHttpServletResponse response = mvc
        .perform(
        post("/memes").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(memeRequest)))
        .andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    PostMemeResponse responseDto = objectMapper.readValue(responseStr, PostMemeResponse.class);
    PostMemeResponse ref = new PostMemeResponse("23");

    assertEquals(responseDto, ref);
    Mockito.verify(memeService, Mockito.times(1)).postMeme(memeRequest);
  }
}
package com.crio.starter.utils;

import com.crio.starter.data.MemeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GenerateData {
  static ObjectMapper mapper = new ObjectMapper();

  /**Main method to generate the json MemeEntity file for testing. 
   * 
   * @param args args
   * @throws IOException exception
   */
  public static void main(String[] args) throws IOException {
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    List<MemeEntity> memeEntityList = new ArrayList<>();
    File file = new File("src/test/resources/fixtures/exchanges/initial_list_of_meme_entity.json");
    for (int i = 0;i < 150; i++) {
      MemeEntity memeEntity = new MemeEntity(i + "200",
          i + "",
          "erwin_" + i,
          "http://falcon.fly/img" + i + ",jpg",
          "attack on titan " + i,
          LocalDateTime.of(1800 + i, i % 12 + 1, i % 28 + 1, 18, 00,00));
      memeEntityList.add(memeEntity);
    }
    mapper.writeValue(file, memeEntityList);
  }
}

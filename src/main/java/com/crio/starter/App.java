/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package com.crio.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean // Want a new obj every time
  @Scope("prototype")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
  
  /**
   * Use to supply a new instance of ObjectMapper for use in desearilizing classes 
   * containing LocalDateTime objects.
   * @return ObjectMapper
   */
  @Bean
  @Scope("prototype")
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return new ObjectMapper();
  }
}

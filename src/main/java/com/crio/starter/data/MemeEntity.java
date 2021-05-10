package com.crio.starter.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Document(collection = "meme")
@NoArgsConstructor
@AllArgsConstructor
public class MemeEntity {
  @Id
  private String id;
  private String memeId;
  private String name;
  private String url;
  private String caption;
  private LocalDateTime dateTime;
}

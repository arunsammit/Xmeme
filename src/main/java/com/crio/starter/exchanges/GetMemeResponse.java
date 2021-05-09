package com.crio.starter.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMemeResponse {
  @NonNull
  private String id;
  @NonNull
  private String name;
  @NonNull
  private String url;
  @NonNull
  private String caption;
}

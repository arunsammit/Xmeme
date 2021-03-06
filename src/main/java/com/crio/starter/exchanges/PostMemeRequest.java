package com.crio.starter.exchanges;

import java.net.MalformedURLException;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMemeRequest {
  private String name;
  private URL url;
  private String caption;

  public String getUrl() {
    return url.toString();
  }

  public void setUrl(String url) throws MalformedURLException {
    this.url = new URL(url);
  }
}

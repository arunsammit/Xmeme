package com.crio.starter.exchanges;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PostMemeResponse {
    @NonNull
    private String id;
}

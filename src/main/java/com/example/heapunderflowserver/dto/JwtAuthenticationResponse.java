package com.example.heapunderflowserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JwtAuthenticationResponse {
    String token;
}

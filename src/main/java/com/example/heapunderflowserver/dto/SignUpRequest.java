package com.example.heapunderflowserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpRequest {
    String fullName;
    String displayName;
    String email;
    String password;
    String avatarUrl;
}

package com.chotonakib.auth.controller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
  @NotNull
  private final String userName;
  private final String userDisplayName;
  private final String userPhotoUrl;
  private final Integer userAwardPoint;
  @NotNull
  private final String email;
}

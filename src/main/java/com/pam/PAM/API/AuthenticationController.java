package com.pam.PAM.API;

import com.pam.PAM.dto.request.RequestAuthenticateDTO;
import com.pam.PAM.dto.response.ResponseAuthenticateSucessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticateService service;


  @PostMapping("authenticate")
  public ResponseEntity<ResponseAuthenticateSucessDTO> authenticate(
      @RequestBody RequestAuthenticateDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }



}

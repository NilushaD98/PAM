package com.pam.PAM.API;

import com.pam.PAM.dto.request.RequestAuthenticateDTO;
import com.pam.PAM.dto.response.ResponseAuthenticateSucessDTO;
import com.pam.PAM.service.serviceIMPL.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
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

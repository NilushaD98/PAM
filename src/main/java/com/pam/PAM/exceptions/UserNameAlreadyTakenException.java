package com.pam.PAM.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "username already taken")
public class UserNameAlreadyTakenException extends RuntimeException{
}

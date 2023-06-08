package com.pam.PAM.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "machine not in database")
public class MachineNotFoundExcepyion extends RuntimeException{
}

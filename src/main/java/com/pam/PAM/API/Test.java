package com.pam.PAM.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
@RequiredArgsConstructor
public class Test {

    @GetMapping("test")
    public String test(){
        return "test successful";
    }
}

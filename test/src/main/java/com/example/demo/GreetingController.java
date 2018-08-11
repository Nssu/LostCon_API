package com.example.demo;


import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("drawable/b.png");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @RequestMapping(value = "/test/{user_key}",method = RequestMethod.GET)
    public String test(@PathVariable(value="user_key") int user_key){
        System.out.println("user_key : "+user_key);
        return "hi";
    }
}
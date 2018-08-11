package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class test {
    @GetMapping("/")
    public Person sayHello(@RequestParam String name){
        Person person = new Person();
        person.setName(name);
        return person;
    }

    @PostMapping("/")
    public void SayHello(
            @RequestBody Person person
    ){

    }
}

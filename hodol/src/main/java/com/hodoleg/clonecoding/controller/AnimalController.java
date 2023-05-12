package com.hodoleg.clonecoding.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AnimalController {

    @GetMapping("/sound")
    public String sound(@RequestParam String animalType){
        if(animalType.equals("CAT")){
            return "야옹";
        }
        return "멍멍";
    }
}

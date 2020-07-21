package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/camelize")
    public String camelString(@RequestParam("original") String original,
                           @RequestParam(value = "initialCap", defaultValue = "false") Boolean initialCap) {
        String[] splitString = original.split("_");
        String output = "";
        int startIndex = 0;

        if (!initialCap) {
            startIndex = 1;
            output = splitString[0];
        }
        for (int i = startIndex; i < splitString.length; i++) {
            output += splitString[i].substring(0,1).toUpperCase() + splitString[i].substring(1);
        }
        return output;
    }

}

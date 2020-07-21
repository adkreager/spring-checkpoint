package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    public String fixBadWords(int length) {
        String output = "";
        for (int i = 0; i < length; i++) {
            output += "*";
        }
        return output;
    }

    @GetMapping("/redact")
    public String redactString(@RequestParam("original") String original,
                               @RequestParam(value = "badWord", required = false, defaultValue = "") List<String> badWord) {
        String[] splitString = original.split(" ");
        for (int i = 0; i < splitString.length; i++) {
            if (badWord.contains(splitString[i])) {
                splitString[i] = fixBadWords(splitString[i].length());
            }
        }
        return String.join(" ", splitString);
    }

}

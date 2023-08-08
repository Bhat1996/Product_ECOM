package com.example.Product.service.helper;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SavingCache {

    @Cacheable(value = "matcher1", key = "#text")
    public String searchPatternForSearchText(String text){

        String excludePattern = "\\b(?:is|and|to|a|the|of|this|an|for|in|as|by|it)\\b";
        Pattern pattern = Pattern.compile(excludePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll("");
    }
}

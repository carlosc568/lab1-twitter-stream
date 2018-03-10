package es.unizar.tmdad.lab1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import es.unizar.tmdad.lab1.service.TwitterLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    @Autowired
    TwitterLookupService twitter;

    
    @RequestMapping("/")
    public String greeting() {
        return "index";
    }
    @MessageMapping("/search")
    public void search(String q){
    	//Thread.sleep(1000); 
        twitter.search(q);
    }
    
    @MessageMapping("/unsub")
    public void unsub(String q) {
        twitter.unsubscribe(q);
    }
}
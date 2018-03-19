package es.unizar.tmdad.lab1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchMetadata;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Service
public class TwitterLookupService {
	
private String consumerKey = System.getenv("twitter.consumerKey");

    
    private String consumerSecret = System.getenv("twitter.consumerSecret");

    
    private String accessToken = System.getenv("twitter.accessToken");

    
    private String accessTokenSecret = System.getenv("twitter.accessTokenSecret");
    /*
	@Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;*/

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    //private HashMap<String, Stream> canales = new HashMap<String, Stream>();
    //private HashMap<String, StreamListener> canales2 = new HashMap<String, StreamListener>();
    private ArrayList<String> lquerys = new ArrayList<String>();
    private List<StreamListener> list = new ArrayList<StreamListener>();
    private Stream s;
    public void search(String query) {
    	if(!lquerys.contains(query) && !query.equals("") && !query.equals(" ")){
	        if(lquerys.size()>=10){
	        	lquerys.remove(0);
	        	list.remove(0);
	        	
	        	
	        }
	        //canales.forEach((k,v)-> System.out.println(k));
	        System.out.println("Creando stream para: "+query);
    		Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	        StreamListener st = new SimpleStreamListener(messagingTemplate, query);
	        list.add(st);
	        lquerys.add(query);
	        //canales2.put(query+'l',st);
	        if(s!=null){
	        	s.close();
	        }
	        s = twitter.streamingOperations().filter(String.join(" , ", lquerys), list);
	        
    	}
    
    }

    public void unsubscribe(String query){
    	//nada
    	
    }
    
    public SearchResults emptyAnswer() {
        return new SearchResults(Collections.emptyList(), new SearchMetadata(0,0 ));
    }
}


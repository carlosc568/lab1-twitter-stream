package es.unizar.tmdad.lab1.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.util.MimeTypeUtils;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamWarningEvent;

import org.springframework.social.twitter.api.StreamListener;

public class SimpleStreamListener implements StreamListener {
	
   private SimpMessageSendingOperations ops;
   private String query;

   public SimpleStreamListener(SimpMessageSendingOperations  ops, String query){
       this.ops = ops;
       this.query = query;
   }

   
   @Override
   public void onTweet(Tweet tweet) {
	   //System.exit(0);
       Map<String,Object> map = new HashMap<>();
       map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
       if(tweet.getUnmodifiedText().contains(query) || tweet.getText().contains(query)){
    	   //System.out.println("Mandando tweet de query: "+query);
    	   ops.convertAndSend("/queue/search/" + query, tweet, map);
       }
   }
   
   	@Override
   	public void onWarning(StreamWarningEvent e) {}
    @Override
    public void onDelete(StreamDeleteEvent e) {}
    @Override
    public void onLimit(int n) {}
}

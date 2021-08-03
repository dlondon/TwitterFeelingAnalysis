/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testetwitter;

import static com.mycompany.testetwitter.SentimentAnalyzer.findSentiment;
import java.util.List;
import java.util.stream.Collectors;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author SISTRAM4
 */
public class Main {
    
    

    static Twitter twitter = null;

    public static void main(String[] args) throws TwitterException {
        
        
        SentimentAnalyzer.init();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Key")
                .setOAuthConsumerSecret("Consumersecret")
                .setOAuthAccessToken("token")
                .setOAuthAccessTokenSecret("AccessTokenSecret");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Query query = new Query("passeata");
        query.setCount(100);

        //Execute the search method in the twitter object. 
        QueryResult result = twitter.search(query);

        
       
        
        int num_neutral = 0;
        int num_negative = 0;
        int num_realnegative = 0;
        int num_positive = 0;
        int num_realpositive = 0;
        String sentiment = "abc";
        String tweet = "abc";

        for (int i = 0; i < result.getTweets().size(); i++) {
            tweet = result.getTweets().get(i).getText();
            
            System.out.println("->"+tweet);
            try
            {
                sentiment = findSentiment(tweet);
                if (sentiment.equalsIgnoreCase("Neutral")) {
                    num_neutral++;
                } else if (sentiment.equalsIgnoreCase("Negative")) {
                    num_negative++;
                } else if (sentiment.equalsIgnoreCase("Very Negative")) {
                    num_realnegative++;
                } else if (sentiment.equalsIgnoreCase("Very Positive")) {
                    num_realpositive++;
                } else {
                    num_positive++;
                }
                System.out.println(sentiment);
            }
            catch(Exception e)
            {e.printStackTrace();}
        }

        System.out.println("Neutra: "+num_neutral);
        System.out.println("Positiva: "+num_positive);
        System.out.println("Negativa: "+num_negative);      
      
        
        
       
       

        
      /*  System.out.println(result.getTweets().size());
        for (Status status : result.getTweets())
        {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
        
        
        
        
      
        for (int i = 0; i < result.getTweets().size(); i++)
        {
            System.out.println("Usuario: "+result.getTweets().get(i).getUser().getScreenName());
            System.out.println("Local do usuario: "+result.getTweets().get(i).getUser().getLocation());
            System.out.println("Local do tweet: "+result.getTweets().get(i).getGeoLocation());
            System.out.println("Hora do tweet: "+result.getTweets().get(i).getCreatedAt());
            System.out.println("Texto: "+result.getTweets().get(i).getText());
            System.out.println("==========================================");
        }*/
       
        
        
    }

}

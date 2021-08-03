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
public class AnaliseSentimentoStanford {
    
     static Twitter twitter = null;

    public static void main(String[] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("GPnTGUI7JxqI68y5iOj0nWujR")
                .setOAuthConsumerSecret("AfIM3fDSpDUe6lPoOFsLLryrrFYnkNu44UuPxrA8UkY6WjyXsi")
                .setOAuthAccessToken("1284604293102829573-KmjrhjyvyFzvZHzsfajI5tM8EhaXYr")
                .setOAuthAccessTokenSecret("OLyiBkDTvJvCiS4hHSibYYKqp0J3cMDYd0KdWWI5q154j");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Query query = new Query("covaxin");
        query.setCount(100);

        //Execute the search method in the twitter object. 
        QueryResult result = twitter.search(query);
       //The following lines of code initialize variables that represent the number of tweets of each sentiment. 
    
    int num_neutral = 0;
    int num_negative = 0;
    int num_realnegative = 0;
    int num_positive = 0;
    int num_realpositive = 0;
    
    for(int i=0; i<result.getTweets().size(); i++){
        String tweet = result.getTweets().get(i).getText();
            String sentiment = findSentiment(tweet);
            if(sentiment.equalsIgnoreCase("Neutral")){
                num_neutral++;
            }
            else if(sentiment.equalsIgnoreCase("Negative")){
                num_negative++;
            }
            else if(sentiment.equalsIgnoreCase("Very Negative")){
                num_realnegative++;
            }
            else if(sentiment.equalsIgnoreCase("Very Positive")){
                num_realpositive++;
            }
            else{
                num_positive++;
            }       
    }
    
    System.out.println(num_neutral);
    System.out.println(num_negative);
    System.out.println(num_realnegative);
    System.out.println(num_realpositive);
    System.out.println(num_realnegative);

    }

    
}

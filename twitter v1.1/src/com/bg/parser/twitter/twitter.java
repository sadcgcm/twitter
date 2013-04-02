/**
 * 
 */
package com.bg.parser.twitter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.bg.bd.mysql.mysql;

import facebook4j.User;

/**
 * @author KRISTIAN
 * to define the class uses for crawling the twitter
 *
 */

public class twitter {
	private int DEPTH_LEVEL;
	private String CONSUMER_KEY;
	private String CONSUMER_SECRET;
	private String ACCESS_TOKEN;
    private String ACCESS_TOKEN_SECRET;
    private Twitter twitter_;
    private User_twitter user;
    
    //constructor
    public twitter(){
    	CONSUMER_KEY = twitterGlobals.CONSUMER_KEY;
    	CONSUMER_SECRET = twitterGlobals.CONSUMER_SECRET;
    	ACCESS_TOKEN = twitterGlobals.ACCESS_TOKEN;
    	ACCESS_TOKEN_SECRET = twitterGlobals.ACCESS_TOKEN_SECRET;
    	DEPTH_LEVEL = twitterGlobals.DEFAULT_DEPTH_LEVEL;
    }

    public twitter(int DEPTH_LEVEL_){
    	CONSUMER_KEY = twitterGlobals.CONSUMER_KEY;
    	CONSUMER_SECRET = twitterGlobals.CONSUMER_SECRET;
    	ACCESS_TOKEN = twitterGlobals.ACCESS_TOKEN;
    	ACCESS_TOKEN_SECRET = twitterGlobals.ACCESS_TOKEN_SECRET;
    	DEPTH_LEVEL = DEPTH_LEVEL_;
    }
   
    //Establece la profundidad
    public void setDEPTH_LEVEL(int DEPTH_LEVEL_){
    	DEPTH_LEVEL = DEPTH_LEVEL_;
    }
    
    //Obtiene la profundidad
    public int getDEPTH_LEVEL(){
    	return DEPTH_LEVEL;
    }
    
    //Establece la authenticacion de la aplicacion para la extraccion de la informacion   
    public void Authenticate(){
    	ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(CONSUMER_KEY)
            .setOAuthConsumerSecret(CONSUMER_SECRET)
            .setOAuthAccessToken(ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        twitter_ = new TwitterFactory(cb.build()).getInstance();
    }
    
    //obtiene el objeto twitter
    public Twitter getTwitter(){
    	if (twitter_ == null) Authenticate();
    	return twitter_;
    }
    
    public void ObtenerUsuario(String id_Usuario){
    	try {
    		twitter4j.User user_ = twitter_.showUser(id_Usuario);
    		user = new User_twitter(user_, twitter_);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void ObtenerInformation() throws TwitterException{
    	user.AlmacenarUser();
    	//user.ObtenerTweets();
    	user.ObtenerFollowers();
    }
    
}

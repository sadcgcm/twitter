/**
 * 
 */
package com.bg.parser;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * @author KRISTIAN
 * to define the class uses for crawling the twitter
 *
 */

public class twitter {
	public static int DEFAULT_DEPTH_LEVEL = 0;
	private int DEPTH_LEVEL;
	private String CONSUMER_KEY = "97mJoEnOQaMqamB8D7bj8w";
	private String CONSUMER_SECRET = "JD3IxmcChd8eoh9IcfC3DH3bwWQAsmAD8YB7TzB0";
    private String ACCESS_TOKEN = "1276498230-fLCjXU9fShnPHSWZvfx6EwCpOtNT0zycvuqeQtI";
    private String ACCESS_TOKEN_SECRET = "Mt7mW83Tj19fpcMimsslW0BGnsdcztQItCuTtHALc";
    private Twitter twitter_;
    
    //constructor
    public twitter(){
    	//twitter_ = null;
    }
    
    //Obtiene la profundidad por defecto
    public int getDEFAULT_DEPTH_LEVEL(){
    	return DEFAULT_DEPTH_LEVEL;
    }
    
    //Establece la profundidad
    public void setDEPTH_LEVEL(int DEPTH_LEVEL_){
    	DEPTH_LEVEL = DEPTH_LEVEL_;
    }
    
    //Obtiene la profundidad
    public int getDEPTH_LEVEL(){
    	return DEPTH_LEVEL;
    }
    
    //Establece la consumer key 
    public void setCONSUMER_KEY(String CONSUMER_KEY_){
    	CONSUMER_KEY = CONSUMER_KEY_;
    }
    
    //Establece la consumer secret
    public void setCONSUMER_SECRET(String CONSUMER_SECRET_){
    	CONSUMER_SECRET = CONSUMER_SECRET_;
    }
    
    //Establece el access token    
    public void setACCESS_TOKEN(String ACCESS_TOKEN_){
    	ACCESS_TOKEN = ACCESS_TOKEN_;
    }
    
    //Establece el access token secret
    public void setACCESS_TOKEN_SECRET(String ACCESS_TOKEN_SECRET_){
    	ACCESS_TOKEN_SECRET = ACCESS_TOKEN_SECRET_;
    }
    
    //Establece la authenticacion de la aplicacion para la extraccion de la informacion   
    public void Auth(){
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
    	if (twitter_ == null) Auth();
    	return twitter_;
    }
    
    public void Varios(){
    	//Recuperar listado de ultimos tweets escritos
        //El paging sirve para decir el número máx de tweets que quieres recuperar
        Paging pagina = new Paging(2);
        pagina.setCount(200);
         
        //Recupera como máx tweets escritos por tí
        //ResponseList<Status> listado = twitter.getUserTimeline(pagina);
        ResponseList<Status> listado;
		try {
			listado = twitter_.getUserTimeline("naturanet",pagina);

        System.out.println(listado.size());
        //for (int i = 0; i < listado.size(); i++)
            
        	//System.out.println(listado.get(i).getUser());
        
        //Recupera como máx los ultimos tweets de tus novedades
        /*listado = twitter.getHomeTimeline(pagina);
        for (int i = 0; i < listado.size(); i++)
            System.out.println(listado.get(i).getText());
         */
        //Actualizar tu estado
        //Status tweetEscrito = twitter.updateStatus("¡Probando!");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("-------------------------------");
        try {
	        Query query = new Query("@naturanet");
	        QueryResult result;
	        int i = 0;
	        do {
	            
	        	result = twitter_.search(query);
	        	List<Status> tweets = result.getTweets();
	            //System.out.println(tweets.size());
	            for (Status tweet : tweets) {
	                //System.out.println(tweet.getUser());
	            	//System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + " - " + tweet.getSource());
	            	//System.out.println(++i);
	            	//System.out.println(tweet.getId());
	            }
	        } while ((query = result.nextQuery()) != null);
	        System.exit(0);
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
    }
    	
    
}

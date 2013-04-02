package com.bg.parser.twitter;

import java.util.ArrayList;
import java.util.List;

import com.bg.bd.mysql.mysql;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class User_twitter {
	private twitter4j.User user;
	private String id_user;
	private String name;
	private String location;
	private String description;
	private int followers_count;
	private String create_at;
	private String json;
	private int friends_count;
	private int listed_count;
	private String screen_name;
	private int statuses_count;
	private int level;
	private List<String> Followers_ids;
	private List<User_twitter> Followers;
	private List<Tweet_twitter> Tweets;
	Twitter twitter_;
	private String orig_user;
	private String dad_user;
   
    public User_twitter(twitter4j.User user_, Twitter twitter__){
    	user = user_;
    	id_user = Long.toString( user.getId() );
    	name = user.getName();
    	location = user.getLocation();
    	description = user.getDescription();
    	followers_count = user.getFollowersCount();
    	create_at = user.getCreatedAt().toString();
    	json = user.toString();
    	friends_count = user.getFriendsCount();
    	listed_count = user.getListedCount();
    	screen_name = user.getScreenName();
    	statuses_count = user.getStatusesCount();
    	level = twitterGlobals.DEFAULT_DEPTH_LEVEL;
    	Followers_ids = new ArrayList<String>();
    	Followers = new ArrayList<User_twitter>();
    	Tweets = new ArrayList<Tweet_twitter>();
    	twitter_ = twitter__;
    	orig_user = id_user;
    	dad_user = id_user;
    }

    public User_twitter(twitter4j.User user_, Twitter twitter__, int DEPTH_LEVEL, String orig_user_, String dad_user_){
    	user = user_;
    	id_user = Long.toString( user.getId() );
    	name = user.getName();
    	location = user.getLocation();
    	description = user.getDescription();
    	followers_count = user.getFollowersCount();
    	create_at = user.getCreatedAt().toString();
    	json = user.toString();
    	friends_count = user.getFriendsCount();
    	listed_count = user.getListedCount();
    	screen_name = user.getScreenName();
    	statuses_count = user.getStatusesCount();
    	level = DEPTH_LEVEL;
    	Followers_ids = new ArrayList<String>();
    	if (level <= twitterGlobals.MAX_DEPTH_LEVEL){
    		Followers = new ArrayList<User_twitter>();
    		Tweets = new ArrayList<Tweet_twitter>();
    	}
    	twitter_ = twitter__;
    	orig_user = orig_user_;
    	dad_user = dad_user_;
    	
    }
    
    public void AlmacenarUser(){
    	mysql m = new mysql();
    	m.InsertarUser(id_user, name, location, description, followers_count, create_at, json, friends_count, listed_count, screen_name, statuses_count);
    }
    
    public void AlmacenarFollowers(){
    	mysql m = new mysql();
    	m.InsertarUser(id_user, name, location, description, followers_count, create_at, json, friends_count, listed_count, screen_name, statuses_count);
    	m.InsertarRelation(dad_user, id_user, level + 1, orig_user);
    }
    
    public twitter4j.User ObtenerUsuario(String id_Usuario){
    	try {
    		twitter4j.User user = twitter_.showUser(id_Usuario);		
		} catch (TwitterException e) {
			System.out.println("Error al obtener tweets(ObtenerUsuario(id_Usuario)). -> " + e.toString());
			e.printStackTrace();
		}
    	return user;
    }
    
    //Only 3210
    public void ObtenerFollowers() throws TwitterException{
    	long cursor = -1;
    	int j = 1;
        IDs ids_followers = twitter_.getFollowersIDs(screen_name,cursor);
        do{
    		for(long id : ids_followers.getIDs() ){
    			System.out.println(Long.toString(id));
    			System.out.println(j++);
    			
    			
    			//System.out.println(twitter_.showUser(id));
    			//System.out.println((level+1) + " - " + orig_user + " - " + id_user);
    			//Followers.add( new User_twitter( twitter_.showUser(id), twitter_, (level+1), orig_user, id_user ) );
    		}
			/*try{
  			  Thread.sleep(4000);  
  			}catch (InterruptedException ie){
  			  System.out.println(ie.getMessage());
  			}*/
    	}while(ids_followers.hasNext());
    	
        
        /*
        long lCursor = -1;
        IDs friendsIDs = twitter.getFollowersIDs("naturanet", lCursor);
        System.out.println(twitter.showUser("naturanet").getName());
        System.out.println("==========================");
        do
        {
          for (long i : friendsIDs.getIDs())
           {
               System.out.println("follower ID #" + i);
               System.out.println(twitter.showUser(i).getName());
           }
        }while(friendsIDs.hasNext());
        */
        
    }
        
    private void ObtenerTweets(String id_Usuario){
    	String user_screen_name = "";
    	try {
			 user_screen_name = twitter_.showUser(id_Usuario).getScreenName();
		} catch (TwitterException e1) {
			System.out.println("Error al obtener tweets(ObtenerTweets(id_Usuario) parte1). -> " + e1.toString());
			e1.printStackTrace();
		}	
        for (int i = 1; i< 25; i++){ //25
	        Paging pagina = new Paging(i,200);  //200
	        ResponseList<Status> list_tweets;
			try {
				list_tweets = twitter_.getUserTimeline(user_screen_name,pagina);
				if (list_tweets.size() > 0){
					for (int j = 0; j < list_tweets.size(); j++){
						Tweets.add( new Tweet_twitter( list_tweets.get(j), id_user ) );
					}
				}
			} catch (TwitterException e) {
				System.out.println("Error al obtener tweets(ObtenerTweets(id_Usuario) parte2). -> " + e.toString());
				e.printStackTrace();
			}
        }
    }
    
    public void ObtenerTweets(){
        for (int i = 1; i< 25; i++){ //25
	        Paging pagina = new Paging(i,200);  //200
	        ResponseList<Status> list_tweets;
			try {
				list_tweets = twitter_.getUserTimeline(screen_name, pagina);
				if (list_tweets.size() > 0){
					for (int j = 0; j < list_tweets.size(); j++){
						System.out.println(list_tweets.get(j).toString());
						Tweets.add( new Tweet_twitter( list_tweets.get(j), id_user ) );
					}
				}
			} catch (TwitterException e) {
				System.out.println("Error al obtener tweets(ObtenerTweets). -> " + e.toString());
				e.printStackTrace();
			}
        }
    }
}

package com.bg.parser.twitter;
import com.bg.bd.mysql.mysql;

import twitter4j.Status;

public class Tweet_twitter {
	private Status tweet;
	private String id_tweet;
	private String text;
	private String to_;
	private String source;
	private String create_at;
	private String json;
	private String place;
	private long retweet_count; 
	private Boolean truncate;
	private String user;
	private String user_owner;
	
	public Tweet_twitter(Status tweet_, String user_owner_){
		tweet = tweet_;
		id_tweet = Long.toString( tweet.getId() );
		text = tweet.getText();
		to_ = tweet.getUser().getScreenName();
		source = tweet.getSource();
		create_at = tweet.getCreatedAt().toString();
		json = tweet.toString();
		place = "null";//tweet.getPlace().toString();
		retweet_count = tweet.getRetweetCount(); 
		truncate = tweet.isTruncated();
		user = tweet.getUser().toString();
		user_owner = user_owner_;
		AlmacenarTweets();
	}
	
	public void AlmacenarTweets(){
		mysql m = new mysql();
		System.out.println (m.InsertarTweets(id_tweet, text, to_, source, create_at, json, place, retweet_count, truncate, user));
		System.out.println (m.InsertarUserTweet(user_owner, id_tweet) );
	}
	
}

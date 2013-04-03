package com.bg.parser.json;

import java.util.ArrayList;
import java.util.List;

import com.bg.bd.mysql.mysql;

/**
 * 
 * @author KRISTIAN
 * this class is used for creating json
 */

public class User {
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
	private List<Tweet> tweets;
	private List<User> Followwers;

	/**
	 * Constructor
	 * @param id_user
	 * @param name
	 * @param location
	 * @param description
	 * @param followers_count
	 * @param create_at
	 * @param json
	 * @param friends_count
	 * @param listed_count
	 * @param screen_name
	 * @param statuses_count
	 */
	public User(String id_user, String name, String location, String description, int followers_count, String create_at, String json, int friends_count, int listed_count, String screen_name, int statuses_count){
		this.id_user = id_user;
		this.name = name;
		this.location = location;
		this.description = description;
		this.followers_count = followers_count;
		this.create_at = create_at;
		this.json = json;
		this.friends_count = friends_count;
		this.listed_count = listed_count;
		this.screen_name = screen_name;
		this.statuses_count = statuses_count;
		LoadTweets(id_user);
		LoadFollowers(id_user);
	}
	/**
	 * @return the id_user
	 */
	public String getId_user() {
		return id_user;
	}
	/**
	 * @param id_user the id_user to set
	 */
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the followers_count
	 */
	public int getFollowers_count() {
		return followers_count;
	}
	/**
	 * @param followers_count the followers_count to set
	 */
	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}
	/**
	 * @return the create_at
	 */
	public String getCreate_at() {
		return create_at;
	}
	/**
	 * @param create_at the create_at to set
	 */
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}
	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * @return the friends_count
	 */
	public int getFriends_count() {
		return friends_count;
	}
	/**
	 * @param friends_count the friends_count to set
	 */
	public void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}
	/**
	 * @return the listed_count
	 */
	public int getListed_count() {
		return listed_count;
	}
	/**
	 * @param listed_count the listed_count to set
	 */
	public void setListed_count(int listed_count) {
		this.listed_count = listed_count;
	}
	/**
	 * @return the screen_name
	 */
	public String getScreen_name() {
		return screen_name;
	}
	/**
	 * @param screen_name the screen_name to set
	 */
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	/**
	 * @return the statuses_count
	 */
	public int getStatuses_count() {
		return statuses_count;
	}
	/**
	 * @param statuses_count the statuses_count to set
	 */
	public void setStatuses_count(int statuses_count) {
		this.statuses_count = statuses_count;
	}
	
	private void LoadTweets(String id_user){
		mysql m = new mysql();
		//tweets = m.getAllTweets(id_user);
		tweets = m.getAllTweets(id_user, JsonGlobals.MAX_TWEETS);
	}
	
	private void LoadFollowers(String id_user){
		
	}
	
	@Override
	public String toString() {
		return new StringBuffer(" id_user=").append(this.id_user).append(",name=").append(this.name).append(", location=").append(this.location)
				.append(", description=").append(this.description).append(", followers_count=").append(this.followers_count)
				.append(", create_at=").append(this.create_at).append(", json=").append(this.json).append(", friends_count=").append(this.friends_count)
				.append(", listed_count=").append(this.listed_count).append(", screen_name=").append(this.screen_name)
				.append(", statuses_count=").append(this.statuses_count).append(", Tweets=").append(this.tweets).toString();
	}
	
	public void to_convert(){
		Json j = new Json();
		j.toJson(this);
	}
}

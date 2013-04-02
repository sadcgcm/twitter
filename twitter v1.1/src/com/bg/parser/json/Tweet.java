package com.bg.parser.json;


/**
 * 
 * @author KRISTIAN
 * this class is used for creating json
 *
 */
public class Tweet {
	private String id_tweet;
	private String text;
	private String to;
	private String source;
	private String create_at;
	private String json;
	private String place;
	private int retweet_count;
	private boolean truncate;
	
	/**
	 * Constructor del objeto Tweet
	 * @param id_tweet
	 * @param text
	 * @param to
	 * @param source
	 * @param json
	 * @param place
	 * @param retweet_count
	 * @param truncate
	 */
	public Tweet(String id_tweet, String text, String to, String source, String json, String place, int retweet_count, boolean truncate){
		this.id_tweet = id_tweet;
		this.text = text;
		this.to = to;
		this.source = source;
		this.json = json;
		this.place = place;
		this.retweet_count = retweet_count;
		this.truncate = truncate;
	}
	
	/**
	 * @return the id_tweet
	 */
	public String getId_tweet() {
		return id_tweet;
	}

	/**
	 * @param id_tweet the id_tweet to set
	 */
	public void setId_tweet(String id_tweet) {
		this.id_tweet = id_tweet;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
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
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the retweet_count
	 */
	public int getRetweet_count() {
		return retweet_count;
	}

	/**
	 * @param retweet_count the retweet_count to set
	 */
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}

	/**
	 * @return the truncate
	 */
	public boolean isTruncate() {
		return truncate;
	}

	/**
	 * @param truncate the truncate to set
	 */
	public void setTruncate(boolean truncate) {
		this.truncate = truncate;
	}
	
	@Override
	public String toString() {
		return new StringBuffer(" Id_tweet=").append(this.id_tweet).append(",Text=").append(this.text).append(", to=").append(this.to)
				.append(", source=").append(this.source).append(", create_at=").append(this.create_at).append(", json=").append(this.json)
				.append(", place=").append(this.place).append(", retweet_count=").append(this.retweet_count).append(", truncate=").append(this.truncate).toString();
	}

}

package com.bg.bd.mysql;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bg.parser.json.Tweet;


/**
 * 
 * @author KRISTIAN
 * define the mysql conection to store tweets
 */

public class mysql {
	private String DATABASE;
	private String HOST;
	private String USER;
	private String PASSWORD;
	private String PORT;
	private String URL;
	private Connection CON;
	private PreparedStatement PSTMT;

	//Estableciendo los parametros iniciales del mysql
	public mysql (){
		DATABASE = mysqlGlobals.DATABASE;
		HOST = mysqlGlobals.HOST;
		USER = mysqlGlobals.USER;
		PASSWORD = mysqlGlobals.PASSWORD;
		PORT = mysqlGlobals.PORT;
		URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE; 
	}

	//Testing the connection with the database
	public void Test(){
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Success!");
		}catch(Exception e){
			System.out.println("Error(Test) -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//funcion para verificar si los tweets estan almacenados
	public boolean BuscarTweets(String id_tweet){
		boolean is_found = true;
		int rpta = 1;
		try {
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_id_tweets(?,?) } ");
			CS.setString(1,id_tweet);
			CS.registerOutParameter(2,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(2);
			if (rpta == 0){ 
				is_found = false;
			}
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos(BuscarTweets). -> " + e.toString());
		} finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_found;
	}

	//funcion de insertar en la tabla tweets
	public boolean InsertarTweets(String id_tweet, String text, String to_, String source, String create_at, String json, String place, long retweet_count, Boolean truncate, String user){
		boolean is_insert = false;
		int rpta = -1;
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_id_tweets(?,?) } ");
			CS.setString(1,id_tweet);
			CS.registerOutParameter(2,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(2);
			if (rpta == 0){
				CON.setAutoCommit(true);
				PSTMT = CON.prepareStatement(" INSERT INTO tweets(id_tweet, text, to_, source, create_at, json, place, retweet_count, truncate, user) VALUES(?,?,?,?,?,?,?,?,?,?) ");
				PSTMT.setString(1, id_tweet);
				PSTMT.setString(2, text);
				PSTMT.setString(3, to_);
				PSTMT.setString(4, source);
				PSTMT.setString(5, create_at);
				PSTMT.setString(6, json);
				PSTMT.setString(7, place);
				PSTMT.setLong(8, retweet_count);
				PSTMT.setBoolean(9, truncate);
				PSTMT.setString(10, user);
				PSTMT.executeUpdate();
				is_insert = true;
			}	
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(InsertarTweets). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_insert;
	}

	//insertar tweets y users
	public boolean InsertarUser(String id_user, String name, String location, String description, int followers_count, String create_at, String json, int friends_count, int listed_count, String screen_name, int statuses_count){
		int rpta = -1;
		boolean is_insert = false;
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_id_user(?,?) } ");
			CS.setString(1,id_user);
			CS.registerOutParameter(2,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(2);
			if (rpta == 0){
				CON.setAutoCommit(true);
				PSTMT = CON.prepareStatement(" INSERT INTO users(id_user, name, location, description, followers_count, create_at, json, friends_count, listed_count, screen_name, statuses_count) VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
				PSTMT.setString(1, id_user);
				PSTMT.setString(2, name);
				PSTMT.setString(3, location);
				PSTMT.setString(4,description);
				PSTMT.setInt(5, followers_count);
				PSTMT.setString(6, create_at);
				PSTMT.setString(7, json);
				PSTMT.setInt(8, followers_count);
				PSTMT.setInt(9, listed_count);
				PSTMT.setString(10, screen_name);
				PSTMT.setInt(11, statuses_count);
				PSTMT.executeUpdate();
				is_insert = true;
			}
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(InsertarUser). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_insert;
	}

	//insertar tweets y users
	public boolean InsertarUserTweet(String id_user, String id_tweet){
		int rpta = -1;
		boolean is_insert = false;
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_user_tweet(?,?,?) } ");
			CS.setString(1,id_user);
			CS.setString(2,	id_tweet);
			CS.registerOutParameter(3,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(3);
			if (rpta == 0){
				CON.setAutoCommit(true);
				PSTMT = CON.prepareStatement(" INSERT INTO tweet_user(id_user, id_tweet) VALUES(?,?) ");
				PSTMT.setString(1, id_user);
				PSTMT.setString(2, id_tweet);
				PSTMT.executeUpdate();	
				is_insert = true;
			}
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(InsertarUserTweet). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_insert;
	}
	
	//insertar relation
	public boolean InsertarRelation(String id_user_padre, String id_user, int level_, String id_user_origen){
		boolean is_insert = false;
		int rpta = 0;
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_relation(?,?,?,?,?) } ");
			CS.setString(1,id_user_padre);
			CS.setString(2,	id_user);
			CS.setInt(3, level_);
			CS.setString(4,	id_user_origen);
			CS.registerOutParameter(5,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(5);
			if (rpta == 0){
				CON.setAutoCommit(true);
				PSTMT = CON.prepareStatement(" INSERT INTO relation(id_user_padre, id_user, level_, id_user_origen) VALUES(?,?,?,?) ");
				PSTMT.setString(1, id_user_padre);
				PSTMT.setString(2, id_user);
				PSTMT.setInt(3, level_);	
				PSTMT.setString(4, id_user_origen);
				PSTMT.executeUpdate();
				is_insert = true;
			}
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(InsertarRelation). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_insert;
	}

	//Actualizacion de algun campo
	public boolean ActualizarTweetCampo(String id_tweet, String campo_name, String campo){
		boolean is_updated = false;
		int rpta = -1;
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_id_tweets(?,?) } ");
			CS.setString(1,id_tweet);
			CS.registerOutParameter(2,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(2);
			if (rpta == 1){
				CON.setAutoCommit(true);
				PSTMT = CON.prepareStatement("UPDATE tweets SET " + campo_name + " = ? WHERE id_tweet = ? ");
				PSTMT.setString(1, campo);
				PSTMT.setString(2, id_tweet);
				is_updated = true;
			}
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(Actualizar). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_updated;
	}
	
	public boolean ActualizarTweetAll(String id_tweet, String text, String to_, String source, String create_at, String json, String place, int retweet_count, Boolean truncate, String user){
		boolean is_updated = false;
		int rpta = -1;
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_id_tweets(?,?) } ");
			CS.setString(1,id_tweet);
			CS.registerOutParameter(2,java.sql.Types.INTEGER);
			CS.execute();
			rpta = CS.getInt(2);
			if (rpta == 1){
				CON.setAutoCommit(true);
				PSTMT = CON.prepareStatement("UPDATE tweets SET text = ?, to_ = ?, source =?, create_at = ?, json = ?, place = ?, retweet_count = ?, truncate = ?, user = user WHERE id_tweet = ? ");
				PSTMT.setString(1, text);
				PSTMT.setString(2, to_);
				PSTMT.setString(3, source);
				PSTMT.setString(4, create_at);
				PSTMT.setString(5, json);
				PSTMT.setString(6, place);
				PSTMT.setInt(7, retweet_count);
				PSTMT.setBoolean(8, truncate);
				PSTMT.setString(9, user);
				PSTMT.setString(10, id_tweet);
				is_updated = true;
			}
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(Actualizar). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_updated;
	}
	
	
	public List<Tweet> getAllTweets(String id_user){
		List<Tweet> List_tweets = new ArrayList<Tweet>();
		try {
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "select t.id_tweet, t.text, t.to_, t.source, t.create_at, t.json, t.place, t.retweet_count, t.truncate from tweets t, tweet_user tu where t.id_tweet = tu.id_tweet and tu.id_user=" + id_user;
			Statement  STMT = CON.createStatement();
			ResultSet RS = STMT.executeQuery(query);
			while(RS.next()){
				String id_tweet = RS.getString(1);
				String text = RS.getString(2);
				String to = RS.getString(3);
				String source = RS.getString(4);
				String json = RS.getString(5);
				String place = RS.getString(6);
				int retweet_count = RS.getInt(7);
				boolean truncate = RS.getBoolean(8);
				RS.getBoolean(9);
				List_tweets.add( new Tweet(id_tweet, text, to, source, json, place, retweet_count, truncate) );	
			}	
	        RS.close();
	        STMT.close();
		} catch (SQLException e) {
			e.printStackTrace();
	    } finally {
	      try {
	        CON.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
		return List_tweets;
	} 
	
	public List<Tweet> getAllTweets(String id_user, int count_tweets){
		List<Tweet> List_tweets = new ArrayList<Tweet>();
		int i = 1;
		try {
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "select t.id_tweet, t.text, t.to_, t.source, t.create_at, t.json, t.place, t.retweet_count, t.truncate from tweets t, tweet_user tu where t.id_tweet = tu.id_tweet and tu.id_user=" + id_user;
			Statement  STMT = CON.createStatement();
			ResultSet RS = STMT.executeQuery(query);
			while(RS.next() && (i<= count_tweets) ){
				String id_tweet = RS.getString(1);
				String text = RS.getString(2);
				String to = RS.getString(3);
				String source = RS.getString(4);
				String json = RS.getString(5);
				String place = RS.getString(6);
				int retweet_count = RS.getInt(7);
				boolean truncate = RS.getBoolean(8);
				RS.getBoolean(9);
				List_tweets.add( new Tweet(id_tweet, text, to, source, json, place, retweet_count, truncate) );
				i++;
			}	
	        RS.close();
	        STMT.close();
		} catch (SQLException e) {
			e.printStackTrace();
	    } finally {
	      try {
	        CON.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
		return List_tweets;
	} 

}

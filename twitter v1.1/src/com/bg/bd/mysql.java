package com.bg.bd;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
	private String URL;
	private Connection CON;
	private PreparedStatement PSTMT;
	
	//Estableciendo los parametros iniciales del mysql
	public mysql (String DATABASE_, String HOST_, String USER_, String PASSWORD_){
		DATABASE = DATABASE_;
		HOST = HOST_;
		USER = USER_;
		PASSWORD = PASSWORD_;
		URL = "jdbc:mysql://" + HOST + ":3306/" + DATABASE; 
	}
	
	
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
	public boolean Buscar(String id_tweet){
		int rpta = 1;
		try {
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			CallableStatement CS = CON.prepareCall(" { call get_id_tweets(?,?) } ");
			CS.setString(1,id_tweet);
			CS.registerOutParameter(2,java.sql.Types.INTEGER);
			//CS.execute();
			rpta = CS.getInt(2);
			System.out.println(rpta);			
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos(Buscar). -> " + e.toString());
		}finally{
			try {
				CON.close();
				if (rpta == 0) return false;
				else return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
		
	}
	
	//funcion de insertar en la tabla tweets
	public void Insertar(String id_tweet, String text, String from, String to){
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			if (!Buscar(id_tweet) ){
				PSTMT = CON.prepareStatement("INSERT INTO tweets(id_tweet, text, from, to) VALUES(?,?,?,?)");
				PSTMT.setString(1, id_tweet);
				PSTMT.setString(2, text);
				PSTMT.setString(3, from);
				PSTMT.setString(4, to);
				PSTMT.executeUpdate();
			}	
		}catch(Exception e){
			System.out.println("Error al conectar con la base de datos(Insertar). -> " + e.toString());
		}finally{
			try {
				CON.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

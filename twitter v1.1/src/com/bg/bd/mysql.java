package com.bg.bd;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;
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
	
	public mysql (String DATABASE_, String HOST_, String USER_, String PASSWORD_){
		DATABASE = DATABASE_;
		HOST = HOST_;
		USER = USER_;
		PASSWORD = PASSWORD_;
		URL = "jdbc:mysql://" + HOST + ":3306/" + DATABASE; 
	}
	
	public void Insertar(){
		try{
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			PSTMT = CON.prepareStatement("INSERT INTO tweets VALUES(?,?,?,?)");
			PSTMT.setString(1, author);
			PSTMT.executeUpdate();
		}catch{
			
		}
	}
	
}

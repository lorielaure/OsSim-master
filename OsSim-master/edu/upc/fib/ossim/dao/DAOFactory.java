/**
 * 
 */
package edu.upc.fib.ossim.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

/**
 * @author Laure
 *
 */
public class DAOFactory {
	
	private static Connection connect = null;
	private final static String HOST = "localhost";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private final static String DATABASE = "ossim";
	private final static String CONNECTION_STRING = "jdbc:mysql://%s/%s?user=%s&password=%s";
	
	
	public static Dao getDAO(String clazz){
		if(connect == null)
			initConnection();
	
		if(clazz.compareTo(EtudiantDAO.class.getName())==0)
			return new EtudiantDAO(connect);
		if(clazz.compareTo(ProfesseurDAO.class.getName())==0)
			return new ProfesseurDAO(connect);
		return null;
	}
	private static void initConnection(){
		
		try{
			Class.forName(Driver.class.getName());
			String url = String.format(CONNECTION_STRING, HOST, DATABASE, USERNAME, PASSWORD);
			System.out.println(url);
			connect = DriverManager.getConnection(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}

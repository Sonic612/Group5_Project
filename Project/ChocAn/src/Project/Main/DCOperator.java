package Project.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the Data Center Operator Class. This controls the DC Operator functions.
 * 
 * @author sfyock
 */
public class DCOperator {
	private String dbUser;
	private String dbPass;
	private String connString;
	private Connection connection;
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	/**
	 * @param user
	 * @param password
	 */
	DCOperator(String user, String password){
		init(user, password);
	}
	
	private void init(String user, String password) {
		this.dbUser = user;
    	this.dbPass = password;
    	this.connString = TARGET_SERVER + ";" 
                + "database="+ TARGET_DB + ";"
                + "user=" + dbUser + ";"
                + "password={" + dbPass + "};"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=60;";
  

    	try {
            connection = DriverManager.getConnection(connString);
    	}
    	catch(SQLException e){
    		System.out.println(e.getErrorCode()+ " " + e.getMessage());
    	}
		
	}
	/**
	 * @param a
	 * @param b
	 * @param c
	 */
	public void createConn(String a, String b, String c){
		
	}
	/**
	 * @param mem
	 */
	private void addMember(Member mem){
		
	}
	
	/**
	 * @param mem
	 */
	//private void delMember(memID mem){
		
	//}
	
	/**
	 * @param mem
	 */
	private void updateMember(Member mem){
		
	}
	/**
	 * @param prov
	 */
	private void addProvider(Provider prov){
		
	}
	/**
	 * @param prov
	 */
	//private void delProvider(provID prov){
		
	//}
	/**
	 * @param prov
	 */
	private void updateProvider(Provider prov){
		
	}
	/**
	 * 
	 */
	private void termConn(){
		
	}
}
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportViewer {
		final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
		final String TARGET_DB = "ChocAn";
		//Declaring JDBC object
		String connString;
		String dbUser;
		String dbPass;
		String connectionError;
		
		Connection connection;
	    ResultSet resultSet;

	   
	    //SQL queries
	    final String SELECT_STMT = "SELECT Content" + 
	    				"\nFROM dbo.tbl_ProvReport \nWHERE Prov_ID = ? AND record_date = ?;";
 	
	  	// Prepared Statements
	    private PreparedStatement Stmt1;
   
		// Constructor
	    public ReportViewer(String user, String password){
	    	init(user,password);
	    }
	    
	    private void init(String user, String password){
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
	            Stmt1 = connection.prepareStatement(SELECT_STMT);
	            System.out.println("Successfully entered Report Viewer!");
	    	} catch (SQLException e) {
				System.out.println(e.getErrorCode()+ " " + e.getMessage());
				setConnectionError("Error!");
			}
	    }
	    
	    private void setConnectionError(String error){
			connectionError = error;
		}
		
		public String getConnectionError(){
			return connectionError;
		}
	    
	    public void GenReport(int id,String date){
	    	try {
				Stmt1.setInt(1, id);
				Stmt1.setDate(2, java.sql.Date.valueOf(date));
		    	resultSet = Stmt1.executeQuery();
		    	connection.commit();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode()+ " " + e.getMessage());
			}
	    	
	    }
	    
	    public String viewReport(){
	  	    String repStream = "";
	    	String processStr = "";
	    	
	    	try {
				while(resultSet.next()){
					repStream += resultSet.getString("Content");
				}
				resultSet.close();
			} catch (SQLException e) {
				System.out.println( e.getErrorCode()+ " " + e.getMessage());
			}
	    	
	    	String[] report = repStream.split("\\,");
	    	
	    	for(int i=0;i<report.length;i++){
	    		processStr += report[i] + "\n";
	    		if(i%5==0) System.out.print("\n");
	    	}
			return processStr;
	    }
	    
	    public void termConnection() {
			try {
				Stmt1.close();
				connection.close();
				
			} catch (SQLException e) {
				System.out.println(e.getErrorCode()+ " " + e.getMessage());
			}
		}
}
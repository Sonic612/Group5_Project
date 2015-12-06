package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.*;

/**
 * @author lle
 *
 */

public class MngReportProcess implements ReportProcess{
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	private Connection connection;
	private String connString;
	private String dbUser;
	private String dbPass;
	public Connection dbTarget;
	private String reportString;
	
	final String WRITE_STMT = "INSERT INTO tbl_MngReport(record_date,Content) VALUES(";
	String repString = "";
	/*ResultSet resultProvSet;
    ResultSet resultEnctrSet;
    ResultSet resultFCount, resultFSum;*/
	public MngReportProcess(String user, String password){
		init(user, password);
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
    	}
    	catch(SQLException e){
    		System.out.println(e.getErrorCode()+ " " + e.getMessage());
    	}
    }
	
	@Override
	public void computeReport(int id, String date) {
		//Checking date format
		Date strDate = new Date();
		if(date == null){
			throw new RuntimeException("The date entered for report generation is invalid or null.");
		}		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);	
		try {
			 strDate = sdf.parse(date);		
		} 
		catch (ParseException e) {	
			e.printStackTrace();
		}
		
		//SQL queries
		//provider to be paid that week, the number of consultations each had, and his or her total fee for that week, the total number of providers who provided services, the total number of consultations, and the overall fee total
		
	}

	@Override
	public void computeReport(String startDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String printReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void termConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
	}

	@Override
	public void saveReport() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Statement insertStmt;
		try {
			insertStmt = connection.createStatement();
			insertStmt.execute(WRITE_STMT + dateFormat.format(date) + "," + repString + ");");
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}	
		
	}

}

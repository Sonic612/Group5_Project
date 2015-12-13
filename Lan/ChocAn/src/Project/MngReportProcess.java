package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author lle
 *
 */

public class MngReportProcess implements ReportProcess{
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	private String connString;
	private String dbUser;
	private String dbPass;
	
	private Connection connection;
	private ResultSet resultProvSet;
	private ResultSet resultEnctrSet;
	private ResultSet resultFeeSet;
	private ResultSet resultProvSum;
	private ResultSet resultEnctrSum;
	private ResultSet resultFeeSum;
	
	String repString = "";
	
	final String WRITE_STMT = "INSERT INTO tbl_MngReport(Mng_ID,record_date,Content) VALUES(?,?,?);";
	
	final String QRY_PROVIDER = "SELECT Prov_Name, Prov_ID,"
				+ "\nWHERE Prov_ID = ?;";
	
	final String QRY_ENCOUNTER = "SELECT COUNT(DISTINCT Prov_ID) AS NumOfEncounters"
  				+ "\nFROM dbo.tbl_Encounters"
  				+ "\nWHERE record_date BETWEEN ? AND ?;";
	
	final String QRY_FEE = "SELECT SUM(dbo.tbl_Services.SERV_fee) AS TotalDues"
				+ "\nFROM dbo.tbl_Encounters "
				+ "\nJOIN dbo.tbl_Services ON dbo.tbl_Encounters.serv_code = dbo.tbl_Services.serv_code"
				+ "\nWHERE Prov_ID = ? AND record_date BETWEEN ? AND ?;";
	
	
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
	public void computeReport(int id, String startDate, String endDate) {
		//SQL queries
		//provider to be paid that week, the number of consultations each had, and his or her total fee for that week, 
		//the total number of providers who provided services, the total number of consultations, and the overall fee total
		
	}

	@Override
	public void computeReport(String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveReport(int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveReport() {
		// TODO Auto-generated method stub
		
	}

}

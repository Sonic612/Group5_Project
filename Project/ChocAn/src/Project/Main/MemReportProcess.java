package Project.Main;

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

//import com.microsoft.sqlserver.jdbc.*;

/**
 * @author lle
 *
 */

public class MemReportProcess implements ReportProcess{
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	private Connection connection;
	private String connString;
	private String dbUser;
	private String dbPass;
	public Connection dbTarget;
	private String reportString;
	
	final String WRITE_STMT = "INSERT INTO tbl_MemReport(record_date,Content) VALUES(";
	private ResultSet resultMemSet;
    	private ResultSet resultEnctrSet;
	private String repString = "";
	
	public MemReportProcess(String user, String password){
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
		//Member name, Member number, Member street address, Member city, Member state, Member ZIP code
		//Date of service, Provider name, Service name
		String memHeader = "SELECET Mem_Name, Mem_ID, Mem_Address, "
				+ "Mem_City, Mem_State, Zip "
				+ "\nFROM tbl_Member "
				+ "\nWHERE Mem_ID = ;";
	
		String EnctrHeader = "SELECET tbl_Encounters.Enctr_date, "
				+ " tbl_Provider.Prov_Name, tbl_Services.serv_name"
				+ "\nFROM tbl_Encounters "
				+ "\nJOIN tbl_Provider \n  ON tbl_Encounters.Prov_ID = tbl_Provider.Prov_ID"
				+ "\nJOIN tbl_Services \n ON tbl_Encounters.serv_code = tbl_Services.serv_code"
				+ "\nWHERE Mem_ID = ? AND record_date BETWEEN ? AND ?;";
		
		try {
			Statement Stmt1 = connection.createStatement();
			this.resultMemSet = Stmt1.executeQuery(memHeader + id);
			
			PreparedStatement Stmt2 = connection.prepareStatement(EnctrHeader);
			Stmt2.setInt(1, id);
			Stmt2.setDate(1, (java.sql.Date) strDate);
			Stmt2.setDate(1, (java.sql.Date) new Date());
			this.resultEnctrSet = Stmt2.executeQuery();
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}	
	}

	@Override
	public void computeReport(String startDate) {
		throw new UnsupportedOperationException("Alternate form of same method valid, member ID is required");
	}

	@Override
	public String printReport() {
		String memStr = ""; 
		String enctrStr = "";
		
		//Result Sets parsed into a single report string
		try {
			while(resultMemSet!=null){
				String name = resultMemSet.getString("Prov_Name");
				int number = resultMemSet.getInt("Prov_ID");
				String address = resultMemSet.getString("Prov_Address");
				String city = resultMemSet.getString("Prov_City");
				String state = resultMemSet.getString("Prov_State");
				String zip = resultMemSet.getString("Zip");
				
				memStr = "Member Name : " + name + "/nMember Number : " + number + "/nMember Street Address : "
						+ address + "/nMember City : " + city + "/nMember State  :" + state + "Member ZIP : " + zip;	
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
		
		try {
			while(resultEnctrSet!=null){
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				String enctrDateStr = resultEnctrSet.getDate("Enctr_Date").toString();
				String recDateStr = sdf.format(resultEnctrSet.getTimestamp("record_date")).toString();
				String provNameStr = resultEnctrSet.getString("Prov_Name");
				String servNameStr = resultEnctrSet.getString("Mem_ID");
				
				enctrStr = "\n-------Service Details--------\nDate of Service : " + enctrDateStr + "/nRecord Date and Time : " +
						recDateStr + "/nMember Name : " + memNameStr + "/nMember Number: " + memNumStr + "/nService Code  :" + servCodeStr 
						+ "Provider ZIP : " + fee;				
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
		
		return repString = memStr + enctrStr;
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

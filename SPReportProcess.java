/**
 * 
 */
package project;

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
 * @author sgill
 *
 */
public class SPReportProcess implements ReportProcess{
	final String TARGET_SERVER = "jdbc:sqlserver://sonic612.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	String connString;
	String dbUser;
	String dbPass;
	
	Connection connection;
    final String WRITE_STMT = "INSERT INTO tbl_ProvReport(record_date,Content) VALUES(";
    ResultSet resultProvSet;
    ResultSet resultEnctrSet;
    ResultSet resultFCount, resultFSum;
    
    String repString = "";
        
    public SPReportProcess(String user, String password){
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
		String provHeader = "SELECET Prov_Name, Prov ID, Prov_Address, "
					+ "Prov_City, Prov_State, Zip "
					+ "\nFROM tbl_Provider "
					+ "\nWHERE Prov_ID = ;";
		
		String EnctrHeader = "SELECET tbl_Encounters.Enctr_date, tbl_Encounters.record_date,"
				+ " tbl_Member.Mem_Name, tbl_Encounters.Mem_ID, tbl_Encounters.serv_code, tbl_Services.SERV_fee"
				+ "\nFROM tbl_Encounters "
				+ "\nJOIN tbl_Member \n  ON tbl_Encounters.Mem_ID = tbl_Member.Mem_ID"
				+ "\nJOIN tbl_Services \n ON tbl_Encounters.serv_code = tbl_Services.serv_code"
				+ "\nWHERE Prov_ID = ? AND record_date BETWEEN ? AND ?;";
		
		String countQuery = "SELECET COUNT(UID)"
				+ "\nFROM tbl_Encounters"
				+ "\nWHERE Prov_ID= ? AND record_date BETWEEN ? AND ?;";
		
		String sumQuery = "SELECET SUM(SERV_fee)"
				+ "\nFROM tbl_Encounters"
				+ "\nWHERE Prov_ID = ? AND record_date BETWEEN ? AND ?;";
		
		try {
			Statement Stmt1 = connection.createStatement();
			this.resultProvSet = Stmt1.executeQuery(provHeader + id);
			
			PreparedStatement Stmt2 = connection.prepareStatement(EnctrHeader);
			Stmt2.setInt(1, id);
			Stmt2.setDate(1, (java.sql.Date) strDate);
			Stmt2.setDate(1, (java.sql.Date) new Date());
			this.resultEnctrSet = Stmt2.executeQuery();
			
			PreparedStatement Stmt3 = connection.prepareStatement(countQuery);
			Stmt3.setInt(1, id);
			Stmt3.setDate(1, (java.sql.Date) strDate);
			Stmt3.setDate(1, (java.sql.Date) new Date());
			this.resultFCount = Stmt3.executeQuery();
			
			PreparedStatement Stmt4 = connection.prepareStatement(sumQuery);
			Stmt4.setInt(1, id);
			Stmt4.setDate(1, (java.sql.Date) strDate);
			Stmt4.setDate(1, (java.sql.Date) new Date());
			this.resultFSum = Stmt4.executeQuery();
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}	
	}
	
	@Override
	public String printReport(){
		
		String providerStr = ""; 
		String enctrStr = ""; 
		String finalStr = "";
		
		
		//Result Sets parsed into a single report string
		try {
			while(resultProvSet!=null){
				String name = resultProvSet.getString("Prov_Name");
				int number = resultProvSet.getInt("Prov_ID");
				String address = resultProvSet.getString("Prov_Address");
				String city = resultProvSet.getString("Prov_City");
				String state = resultProvSet.getString("Prov_State");
				String zip = resultProvSet.getString("Zip");
				
				providerStr = "Provider Name : " + name + "/nProvider Number : " + number + "/nProvider Street Address : "
						+ address + "/nProvider City : " + city + "/nProvider State  :" + state + "Provider ZIP : " + zip;	
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
		
		try {
			while(resultEnctrSet!=null){
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				String enctrDateStr = resultEnctrSet.getDate("Enctr_Date").toString();
				String recDateStr = sdf.format(resultEnctrSet.getTimestamp("record_date")).toString();
				String memNameStr = resultEnctrSet.getString("Mem_Name");
				int memNumStr = resultEnctrSet.getInt("Mem_ID");
				int servCodeStr = resultEnctrSet.getInt("serv_code");
				float fee = resultEnctrSet.getFloat("SERV_fee");
				
				enctrStr = "\n-------Service Details--------\nDate of Service : " + enctrDateStr + "/nRecord Date and Time : " +
						recDateStr + "/nMember Name : " + memNameStr + "/nMember Number: " + memNumStr + "/nService Code  :" + servCodeStr 
						+ "Provider ZIP : " + fee;				
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
		
		int enctrCount = -1;
		int enctrSum = -1;
		try {
			while(resultFCount!=null){
				enctrCount = resultFCount.getInt("COUNT(UID)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(resultFSum!=null){
				enctrSum = resultFSum.getInt("SUM(SERV_fee)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finalStr = "\nTotal number of consultations with members : " + Integer.toString(enctrCount) + "/nTotal fee for week : " +
				Integer.toString(enctrSum);
		
		return repString = providerStr + enctrStr + finalStr;
	}

	@Override
	public void computeReport(String startDate) {
		throw new UnsupportedOperationException("Alternate form of same method valid, provider ID is required");
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

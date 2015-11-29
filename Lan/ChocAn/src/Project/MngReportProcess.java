package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MngReportProcess implements ReportProcess{
	private Connection dbConn;
	private String dbHost;
	private String dbUser;
	private String dbPass;
	public Connection dbTarget;
	private String reportString;
	
	public MngReportProcess(String dbHost, String dbUser, String dbPass){
		super();
		this.dbHost = dbHost;
		this.dbUser = dbUser;
		this.dbPass = dbPass;
	}
	
	private String printReport(int id, String date){
		
	}
	
	private void printReport(String date){
		
	}
	
	private void termConn(){
		
	}
	
	 private void saveReport(){							
		 
	 }

}

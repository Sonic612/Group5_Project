package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

	public class MngReportProcess implements ReportProcess{
		final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
		final String TARGET_DB = "ChocAn";
		
		private String connString;
		private String dbUser;
		private String dbPass;
		
		private Connection connection;
		private ResultSet resultProvSet;
		private ResultSet resultNumProv;
		private ResultSet resultNumEnctrs;
		private ResultSet resultTotalFee;

	 	// Prepared Statements
	    private PreparedStatement Stmt1;
		private PreparedStatement Stmt2;
		private PreparedStatement Stmt3;
		private PreparedStatement Stmt4;
		private PreparedStatement Stmt5;
		private PreparedStatement Stmt6;

		//
		
		String repString = "";
		
		final String WRITE_STMT = "INSERT INTO tbl_EFTReport(record_date,Content) VALUES(?,?);";
		
		final String QRY_PROVIDER = "SELECT dbo.tbl_Encounters.Prov_ID, dbo.tbl_Provider.Prov_Name, dbo.tbl_Service.SERV_FEE,"
  				+ "\nJOIN dbo.tbl_Services ON dbo.tbl_Encounters.serv_code = dbo.tbl_Services.serv_code"
  				+ "\nWHERE record_date BETWEEN ? AND ?;";
		
		final String QRY_NUMOFPROV = "SELECT COUNT(DISTINCT Prov_ID) AS NumOfProviders"
	  				+ "\nFROM dbo.tbl_Encounters"
	  				+ "\nWHERE record_date BETWEEN ? AND ?;";
		
		final String QRY_NUMOFPROVENCTRS = "SELECT COUNT(UID) AS NumOfConsultations"
  				+ "\nFROM dbo.tbl_Encounters"
  				+ "\nWHERE Prov_ID=? AND record_date BETWEEN ? AND ?;";
		
		final String QRY_PROVTOTALFEE = "SELECT SUM(dbo.tbl_Services.SERV_fee) AS ProviderDues"
				+ "\nFROM dbo.tbl_Encounters "
				+ "\nJOIN dbo.tbl_Services ON dbo.tbl_Encounters.serv_code = dbo.tbl_Services.serv_code"
				+ "\nWHERE Prov_ID=? AND record_date BETWEEN ? AND ?;";
		
		final String QRY_TOTALFEE = "SELECT SUM(dbo.tbl_Services.SERV_fee) AS TotalDues"
				+ "\nFROM dbo.tbl_Encounters "
				+ "\nJOIN dbo.tbl_Services ON dbo.tbl_Encounters.serv_code = dbo.tbl_Services.serv_code"
				+ "\nWHERE record_date BETWEEN ? AND ?;";
		
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
	            Stmt1 = connection.prepareStatement(QRY_PROVIDER);
	            Stmt2 = connection.prepareStatement(QRY_NUMOFPROVENCTRS);
	            Stmt3= connection.prepareStatement(QRY_PROVTOTALFEE);
	            Stmt4 = connection.prepareStatement(QRY_NUMOFPROV);
	            Stmt5 = connection.prepareStatement(QRY_TOTALFEE);
	            Stmt6 =  connection.prepareStatement(WRITE_STMT);

	    	}
	    	catch(SQLException e){
	    		System.out.println(e.getErrorCode()+ " " + e.getMessage());
	    	}
	    }


		@Override
		public void computeReport(int id, String startDate, String endDate) {
			throw new UnsupportedOperationException("Alternate form of same method valid, No ID is required");

		}

		@Override
		public void computeReport(String startDate, String endDate) {
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
		public void saveReport(int ID) {
			throw new UnsupportedOperationException("Alternate form of same method valid, No ID is required");			
		}

		@Override
		public void saveReport() {
			// TODO Auto-generated method stub
			
		}

	}

}
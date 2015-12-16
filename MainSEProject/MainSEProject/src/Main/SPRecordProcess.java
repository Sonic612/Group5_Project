package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * @author jmilinsk
 *
 */
public class SPRecordProcess implements RecordProcess{
	
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	//Declaring JDBC object
	private String connString;
	private String dbUser;
	private String dbPass;
	String connectionError;
	
	private Connection connection;
    private PreparedStatement Stmt1;

	//SQL queries
    final String WRITE_STMT = "INSERT INTO dbo.tbl_Encounters(Prov_ID,Mem_ID,serv_code,record_date,Enctr_date) VALUES(?,?,?,?,?);";
    
	/**
	 * @param user
	 * @param password
	 */
	public SPRecordProcess(String user, String password){
		init(user, password);
	}

	/**
	 * @param user
	 * @param password
    */
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
			Stmt1 = connection.prepareStatement(WRITE_STMT);
			System.out.println("Successfully entered SPRecord!");
		}
		catch(SQLException e){
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

	/**
	 * @param provID
	 * @param memID
	 * @param serv_code
	 * @param date
	 * @return
	 */
	public String addRecord(int provID, int memID, int servcode, String date){
		Calendar calendar = Calendar.getInstance();

		try {
			Stmt1.setInt(1, provID);
			Stmt1.setInt(2, memID);
			Stmt1.setInt(3, servcode);
			Stmt1.setTimestamp(4, new java.sql.Timestamp(calendar.getTime().getTime()));
			Stmt1.setDate(5, java.sql.Date.valueOf(date));
			Stmt1.executeQuery();
		} catch (SQLException e) {
			if(e.getMessage().equals("The statement did not return a result set.")){
				return "No Result Set!";
			}
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
		return "Encounter record was added Successfuly!";
	}

	@Override
	public void termConnection() {
		try {
			Stmt1.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
	}

	
	@Override
	public String deleteRecord(int id) {
		throw new UnsupportedOperationException("Not Needed!");
	}

}

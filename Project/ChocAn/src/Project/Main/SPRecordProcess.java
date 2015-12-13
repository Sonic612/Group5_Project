package Project.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jmilinsk
 *
 */
public class SPRecordProcess implements RecordProcess{
	
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	String connString;
	String dbUser;
	String dbPass;
	
	Connection connection;
	public Date TIMESTMP_FRMT;
	private String timestamp;
	
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
	}
	catch(SQLException e){
		System.out.println(e.getErrorCode()+ " " + e.getMessage());
	}
}


private void createTimeStmp(){
	
}

/**
 * @param provider
 * @param prov_ID
 * @param mem_ID
 * @param serv_code
 * @param memberName
 * @return
 */
private String writeRecord(String provider, int prov_ID, int mem_ID, int serv_code, String memberName){
	Statement stmt = null;
    String query = "INSERT INTO tbl_Encounters" +
	"Values (provider, prov_ID, mem_ID, serv_code, memberName)";
    try {
        stmt = connection.createStatement();
        stmt.executeQuery(query);
        termConnection();
        return "Service has been written";
    } catch (SQLException e) {
		System.out.println(e.getErrorCode()+ " " + e.getMessage());
		return "Service has not been written";
	}
}


public void termConnection() {
	try {
		connection.close();
	} catch (SQLException e) {
		System.out.println(e.getErrorCode()+ " " + e.getMessage());
	}
}

}





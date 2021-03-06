package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jmilinsk
 *
 */
public class ServiceRecordProcess implements RecordProcess{
	
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	
	private String connString;
	private String dbUser;
	private String dbPass;
	String connectionError;
	
	private Connection connection;
	
	final String WRITE_STMT = "INSERT INTO dbo.tbl_Services(serv_code,serv_name,SERV_fee) VALUES(?,?,?);";
	final String UPDATE_STMT = "UPDATE dbo.tbl_Services "
	+ "\nSET serv_code = ?,serv_name = ?,SERV_fee = ?"
	+ "\nWHERE serv_code = ?;";
	final String DEL_STMT = "DELETE FROM dbo.tbl_Services WHERE serv_code = ?;";
	final String SEL_STMT = "SELECT 1 FROM dbo.tbl_Services WHERE serv_code = ?;";
	final String DELENCTR_STMT = "DELETE FROM dbo.tbl_Encounters WHERE serv_code = ?;";

	
	//Prepared Statements
	private PreparedStatement Stmt1;
	private PreparedStatement Stmt2;
	private PreparedStatement Stmt3;
	private PreparedStatement Stmt4;
	private PreparedStatement Stmt5;
	private ResultSet resultSet;

	/**
	 * @param user
	 * @param password
	 */
	public ServiceRecordProcess(String user, String password){
		init(user,password);
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
            Stmt2 = connection.prepareStatement(UPDATE_STMT);
            Stmt3 = connection.prepareStatement(DEL_STMT);
            Stmt4 = connection.prepareStatement(DELENCTR_STMT);
            Stmt5 = connection.prepareStatement(SEL_STMT);
            System.out.println("Successfully entered Service Record!");

		} catch(SQLException e){
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
	 * @param serv
	 * @return
	 */
	public String addRecord(Service serv){
		try {
			Stmt1.setInt(1, serv.getServCode());
			Stmt1.setString(2, serv.getServName());
			Stmt1.setDouble(3, serv.getServFee());
			Stmt1.execute();
		} catch (SQLException e) {
			if(e.getMessage().equals("The statement did not return a result set.")){
				return "No Result Set!";
			}
			return e.getErrorCode()+ " " + e.getMessage();
		}
		return "New Service Successfuly Added!";
	}

	/**
	 * @param serv
	 * @return
	 */
	public String UpdateRecord(Service serv){
		try{
			Stmt5.setInt(1, serv.getServCode());
			resultSet = Stmt5.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Service Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		try {
			if(resultSet.next()){
				Stmt2.setInt(1, serv.getServCode());
				Stmt2.setString(2, serv.getServName());
				Stmt2.setDouble(3, serv.getServFee());
				Stmt2.setInt(4, serv.getServCode());
				Stmt2.execute();
				return "Service Successfuly Updated!";
			}
			else return "Service Record not Found!";
		} catch (SQLException e) {
			if(e.getMessage().equals("The statement did not return a result set.")){
				return "No Result Set!";
			}
			return e.getErrorCode()+ " " + e.getMessage();
		}	
	}

	/**
	 * @param servCode
	 * @return
	 */
	@Override
	public String deleteRecord(int servCode){
		try{
			Stmt5.setInt(1, servCode);
			resultSet = Stmt5.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Service Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		try {
			if(resultSet.next()){
				Stmt3.setInt(1, servCode);
				Stmt4.setInt(1, servCode);
				Stmt4.execute();
				Stmt3.execute();
				return "Service Successfuly Deleted!";
			}
			else return "No Service Record Found!";
		} catch (SQLException e) {
			if(e.getMessage().equals("The statement did not return a result set.")){
				return "No Result Set!";
			}
			return e.getErrorCode()+ " " + e.getMessage();
		}	
		

	}

	@Override
	public void termConnection() {
		try {
			Stmt1.close();
			Stmt2.close();
			Stmt3.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ " " + e.getMessage());
		}
	}
}
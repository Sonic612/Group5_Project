package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the Data Center Operator Class. This controls the DC Operator
 * functions.
 * 
 * @author sfyock
 */
public class DCOperator {
	final String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	final String TARGET_DB = "ChocAn";
	// Declaring JDBC object
	String connString;
	String dbUser;
	String dbPass;
	String connectionError;
	Connection connection;

	final String ADD_MEM_STMT = "INSERT INTO dbo.tbl_Member(Mem_ID,Mem_Name,Mem_Address,Mem_City,Mem_State,Zip,isActive,isSuspended) VALUES(?,?,?,?,?,?,?,?);";
	final String DEL_MEM_STMT = "DELETE FROM dbo.tbl_Member WHERE Mem_ID = ?;";
	final String SEL_MEM_STMT = "SELECT 1 FROM dbo.tbl_Member WHERE Mem_ID = ?;";
	final String DEL_MEMENCTR_STMT = "DELETE FROM dbo.tbl_Encounters WHERE Mem_ID = ?;";
	final String UPDATE_MEM_STMT = "UPDATE dbo.tbl_Member "
			+ "\nSET Mem_ID = ?,Mem_Name = ?,Mem_Address = ?,Mem_City = ?,Mem_State = ?,Zip = ?, isActive = ?, isSuspended = ?"
			+ "\nWHERE Mem_ID = ?;";

	final String ADD_PROV_STMT = "INSERT INTO dbo.tbl_Provider(Prov_ID,Prov_Name,Prov_Address,Prov_City,Prov_State,Zip) VALUES(?,?,?,?,?,?);";
	final String DEL_PROV_STMT = "DELETE FROM dbo.tbl_Provider WHERE Prov_ID = ?;";
	final String DEL_PROVENCTR_STMT = "DELETE FROM dbo.tbl_Encounters WHERE Prov_ID = ?;";
	final String SEL_PROV_STMT = "SELECT 1 FROM dbo.tbl_Provider WHERE Prov_ID = ?;";
	final String UPDATE_PROV_STMT = "UPDATE dbo.tbl_Provider "
			+ "\nSET Prov_ID = ?,Prov_Name = ?,Prov_Address = ?,Prov_City = ?,Prov_State = ?,Zip = ?"
			+ "\nWHERE Prov_ID = ?;";

	// Prepared Statements
	private PreparedStatement Stmt1;
	private PreparedStatement Stmt2;
	private PreparedStatement Stmt3;
	private PreparedStatement Stmt4;
	private PreparedStatement Stmt5;
	private PreparedStatement Stmt6;
	private PreparedStatement Stmt7;
	private PreparedStatement Stmt8;
	private PreparedStatement Stmt9;
	private PreparedStatement Stmt10;

	ResultSet resultSet;

	public DCOperator(String user, String password) {
		init(user, password);
	}

	private void init(String user, String password) {
		this.dbUser = user;
		this.dbPass = password;
		this.connString = TARGET_SERVER + ";" + "database=" + TARGET_DB + ";" + "user=" + dbUser + ";" + "password={"
				+ dbPass + "};" + "encrypt=true;" + "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=60;";

		try {
			connection = DriverManager.getConnection(connString);
			Stmt1 = connection.prepareStatement(ADD_MEM_STMT);
			Stmt2 = connection.prepareStatement(DEL_MEM_STMT);
			Stmt3 = connection.prepareStatement(UPDATE_MEM_STMT);
			Stmt4 = connection.prepareStatement(ADD_PROV_STMT);
			Stmt5 = connection.prepareStatement(DEL_PROV_STMT);
			Stmt6 = connection.prepareStatement(UPDATE_PROV_STMT);
			Stmt7 = connection.prepareStatement(DEL_MEMENCTR_STMT);
			Stmt8 = connection.prepareStatement(DEL_PROVENCTR_STMT);
			Stmt9 = connection.prepareStatement(SEL_MEM_STMT);
			Stmt10 = connection.prepareStatement(SEL_PROV_STMT);

			System.out.println("Successfully entered DCOperator!");
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " " + e.getMessage());
			setConnectionError("Error!");
		}

	}

	private void setConnectionError(String error) {
		connectionError = error;
	}

	public String getConnectionError() {
		return connectionError;
	}

	public String addMember(Member mem) {
		int active;
		int suspended;
		active = (mem.isActive()) ? 1 : 0;
		suspended = (mem.isSusp()) ? 1 : 0;

		try {
			Stmt1.setInt(1, mem.getMemID());
			Stmt1.setString(2, mem.getMemFName() + ", " + mem.getMemLName());
			Stmt1.setString(3, mem.getMemStAddr());
			Stmt1.setString(4, mem.getMemCity());
			Stmt1.setString(5, mem.getMemSt());
			Stmt1.setInt(6, mem.getMemZip());
			Stmt1.setInt(7, active);
			Stmt1.setInt(8, suspended);
			Stmt1.execute();
			return "The new member has been successfuly added!";

		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Member Already Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
	}

	public String delMember(int memID) {
		try {
			Stmt9.setInt(1, memID);
			resultSet = Stmt9.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Member Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		
		try {
			if (resultSet.next()) {
				Stmt2.setInt(1, memID);
				Stmt7.setInt(1, memID);
				Stmt7.execute();
				Stmt2.execute();
				return "Member Record Successfuly Deleted!";
			}
			else return "No Such Member Record Found!";
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Member Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		
	}

	public String updateMember(Member mem) {
		int active;
		int suspended;
		active = (mem.isActive()) ? 1 : 0;
		suspended = (mem.isSusp()) ? 1 : 0;

		try {
			Stmt9.setInt(1, mem.getMemID());
			resultSet = Stmt9.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Member Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		try {
			if (resultSet.next()) {

				Stmt3.setInt(1, mem.getMemID());
				Stmt3.setString(2, mem.getMemFName() + mem.getMemLName());
				Stmt3.setString(3, mem.getMemStAddr());
				Stmt3.setString(4, mem.getMemCity());
				Stmt3.setString(5, mem.getMemSt());
				Stmt3.setInt(6, mem.getMemZip());
				Stmt3.setInt(7, active);
				Stmt3.setInt(8, suspended);
				Stmt3.setInt(9, mem.getMemID());
				Stmt3.execute();
				return "Member has been successfuly updated!";
			} else
				return "Member record does not exist!";
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Member Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}

	}

	/**
	 * @param prov
	 */
	public String addProvider(Provider prov) {
		try {
			Stmt4.setInt(1, prov.getProvID());
			Stmt4.setString(2, prov.getProvName());
			Stmt4.setString(3, prov.getProvStAddr());
			Stmt4.setString(4, prov.getProvCity());
			Stmt4.setString(5, prov.getProvSt());
			Stmt4.setInt(6, prov.getProvZip());
			Stmt4.execute();
			return "New Provider Record Successfuly Added!";
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Provider already exists!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}

	}

	/**
	 * @param prov
	 */
	public String delProvider(int provID) {
		try {
			Stmt10.setInt(1, provID);
			resultSet = Stmt10.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Provider Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		
		try {
			if (resultSet.next()) {
				Stmt5.setInt(1, provID);
				Stmt8.setInt(1, provID);
				Stmt8.execute();
				Stmt5.execute();
				return "Provider Record Successfuly Deleted!";
			} 
			else return "No Provider Record Found!";	
		}catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Provider Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}

	}

	/**
	 * @param prov
	 */
	public String updateProvider(Provider prov) {
		try {
			Stmt10.setInt(1, prov.getProvID());
			resultSet = Stmt10.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Provider Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
		try {
			if (resultSet.next()) {
				Stmt6.setInt(1, prov.getProvID());
				Stmt6.setString(2, prov.getProvName());
				Stmt6.setString(3, prov.getProvStAddr());
				Stmt6.setString(4, prov.getProvCity());
				Stmt6.setString(5, prov.getProvSt());
				Stmt6.setInt(6, prov.getProvZip());
				Stmt6.setInt(7, prov.getProvID());
				Stmt6.execute();
				return "Provider Record Successfuly Updated!";
			} else
				return "No Provider Record Found!";
		} catch (SQLException e) {
			if (e.getMessage().equals("The statement did not return a result set.")) {
				return "Provider Does Not Exist!";
			}
			return e.getErrorCode() + " " + e.getMessage();
		}
	}

	/**
	 * 
	 */
	public void termConn() {
		try {
			Stmt1.close();
			Stmt2.close();
			Stmt3.close();
			Stmt4.close();
			Stmt5.close();
			Stmt6.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " " + e.getMessage());
		}
	}
}
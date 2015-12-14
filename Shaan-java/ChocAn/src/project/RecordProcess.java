package project;

import java.sql.SQLException;


/**
 * This is the interface for the SPRecordProcess.
 * 
 * @author sfyock
 */
public interface RecordProcess{
	//Constant Variables
	/**
	 * A constant string variable to store the name of the Data Table to which data extracted will be written to.
	*/
	String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	String TARGET_DB = "ChocAn";;

	/**
	 * @param id, int variable
	 * @return String, indicates whether record was deleted successfully or not.
	 * @throws SQLException 
	 */
	String deleteRecord(int id);
	
	/**
	 * Terminates the connection and close all prepared statements to the database.
	 */
	void termConnection();	
}
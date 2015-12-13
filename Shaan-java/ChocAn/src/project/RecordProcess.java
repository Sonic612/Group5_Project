package project;

import java.sql.Connection;

/**
 * This is the interface for the SPRecordProcess.
 * 
 * @author sfyock
 */
public interface RecordProcess {
	//Constants
	String TARGET_SERVER = "jdbc:sqlserver://sonic613.database.windows.net:1433;";
	String TARGET_DB = "ChocAn";;
	
	String connString =  null;
	String dbUser =  null;
	String dbPass = null;
	
	Connection dbConn = null;

		
	String deleteRecord(int id);

	void termConnection();
	
}

package project;

	public interface ReportProcess{
		//Constant Variables
		/**
		 * A constant string variable to store the name of the Data Table to which data extracted will be written to.
		 */
		String TARGET_SERVER = "jdbc:sqlserver://sonic612.database.windows.net:1433;";
		String TARGET_DB = "ChocAn";
		
		//Methods	
		
		/**
		 * @param id, int variable
		 * @param startDate, Date variable that signifies the start date of the week for which the report is to be computed.
		 * @return String, report information in the required format contained in a single string variable.
		 */
		void computeReport(int id,String startDate);
		
		/**
		 * @param startDateDate variable that signifies the start date of the week for which the report is to be computed.
		 * @return String, report information in the required format contained in a single string variable.
		 */
		void computeReport(String startDate);
		
		/**
		 * Terminates the connection to the database.
		 */
		
		String printReport();
		void termConnection();
		
		/**
		 * Write the report string from printReport() into the listed TARGET_DBTBL.
		 */

		void saveReport();
		
}


package project;

import java.sql.SQLException;


public class Test {

	public static void main(String[] args) throws SQLException {
		SPReportProcess ver1 = new SPReportProcess("group5","!Tech!612");
		/*
		 * //Checking date format
		Date strDate = new Date();
		if(date == null){
			throw new RuntimeException("The date entered for report generation is invalid or null.");
		}		
		SimpleDateFormat sdf = new SimpleDateFormat("");
		sdf.setLenient(false);	
		try {
			 strDate = sdf.parse(date);		
		} 
		catch (ParseException e) {	
			e.printStackTrace();
		}
		*/
		ver1.computeReport(10,"2015-11-11","2015-11-27");
		System.out.print(ver1.printReport());
		ver1.saveReport(10);
		ver1.termConnection();
		
		ReportViewer rv = new ReportViewer("group5","!Tech!612");
		rv.GenReport(10, "2015-11-29");
		System.out.print("Generating report .....\n" + rv.viewReport());
		rv.termConnection();

	}

}
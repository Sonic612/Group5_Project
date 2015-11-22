package project;

public class ChocAn {

	public static void main(String[] args) {

		SPReportProcess ver1 = new SPReportProcess("group5","!Tech!612");
		ver1.computeReport(10, "2015-11-11");
		ver1.printReport();
		ver1.saveReport();
		ver1.termConnection();
	}

}

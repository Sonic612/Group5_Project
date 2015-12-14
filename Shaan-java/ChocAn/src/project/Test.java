package project;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DCOperator dc1 = new DCOperator("group5","!Tech!612");
		ServiceRecordProcess servDir = new ServiceRecordProcess("group5","!Tech!612");
		Member mem1 = new Member(1,"Shaan","Gill","1137 Gertrude St","Waukesha","WI",53186,true,false,"no comments");
		Member mem2 = new Member(2,"Shaan2","Gill2","1138 Gertrude St","Waukesha","WI",53186,true,false,"no comments");
		Member mem3 = new Member(3,"Shaan3","Gill3","1139 Gertrude St","Waukesha","WI",53186,true,false,"no comments");
		Member mem4 = new Member(4,"Shaan4","Gill4","1140 Gertrude St","Waukesha","WI",53186,true,false,"no comments");
		
		Provider prov1 = new Provider(1,"Gills","1141 Gertrude St","Waukesha","WI",53186);
		Provider prov2 = new Provider(2,"Gills2","1142 Gertrude St","Waukesha","WI",53186);
		
		//DCOperaotr
		dc1.addMember(mem1);
		dc1.addMember(mem2);
		dc1.addMember(mem3);
		dc1.addMember(mem4);

		dc1.addProvider(prov1);
		dc1.addProvider(prov2);
		//
		Service serv1 = new Service(1,"serv1",100.5);
		Service serv2 = new Service(2,"serv2",135.0);
		//ServiceRecordProcess
		servDir.addRecord(serv1);
		servDir.addRecord(serv2);

		//SPRecordProcess
		SPRecordProcess sp = new SPRecordProcess("group5","!Tech!612");
		sp.addRecord(1, 1, 1, "12-13-2015");
		sp.addRecord(2, 2, 1, "12-10-2015");
		sp.addRecord(3, 2, 1, "12-11-2015");
		sp.addRecord(4, 1, 2, "12-06-2015");
		sp.addRecord(1, 2, 2, "12-07-2015");
		sp.addRecord(2, 1, 2, "12-09-2015");
		
		//memReport
		MemReportProcess memRep = new MemReportProcess("group5","!Tech!612");
		memRep.computeReport(1,"12-07-2015", "12-13-2015");
		System.out.print("---Member Report---\n" + memRep.printReport());

		//SPReport
		SPReportProcess spRep = new SPReportProcess("group5","!Tech!612");
		spRep.computeReport(1,"12-07-2015", "12-13-2015");
		System.out.print("\n\n---Provider Report---\n" + spRep.printReport());
		
		//EFTReport
		SPReportProcess mangRep = new SPReportProcess("group5","!Tech!612");
		mangRep.computeReport("12-07-2015", "12-13-2015");
		System.out.print("\n\n---Manager Report---\n" + mangRep.printReport());
		

	}

}

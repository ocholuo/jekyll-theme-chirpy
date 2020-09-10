



import java.util.*;



public class Tester{


    HashMap hm = new HashMap<>();

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", "date", "example request", 200, 500, 0);
        System.out.println(le);

        LogEntry le2 = new LogEntry("1.2.100.4", "date", "example request 2", 300, 400, 0);
        System.out.println(le2);
    }


    public void testLogAnalyzer() {
        LogAnalyzer pr = new LogAnalyzer();
        pr.readFile("data/weblog1_log");
        pr.printAll();
        
        // System.out.println("---------------test countUniqueIPs()---------------");
        // int ips = pr.countUniqueIPs();

        // System.out.println("---------------test uniqueIPVisitsOnDay()---------------");
        // pr.uniqueIPVisitsOnDay("24/Mar");

        // System.out.println("---------------test printAllHigherThanNum()---------------");
        // pr.printAllHigherThanNum(400);

        // System.out.println("---------------test countUniqueIPsInRange()---------------");
        // pr.countUniqueIPsInRange(300,399);

        System.out.println("---------------test counVisitesPerIO()---------------");
        HashMap<String,Integer> counts = pr.countVisitsPerIP();

        // System.out.println("---------------test mostNumberVisitsByIP()---------------");
        // int maxTimes = pr.mostNumberVisitsByIP(counts);

        // System.out.println("---------------test iPsMostVisits()---------------");
        // ArrayList<String> maxIP = pr.iPsMostVisits();

        System.out.println("---------------test iPsForDays()---------------");
        HashMap<String, ArrayList<String>> dayToIP = pr.iPsForDays();


        System.out.println("---------------test dayWithMostIPVisits()---------------");
        String mostIPday = pr.dayWithMostIPVisits(dayToIP);

        System.out.println("---------------test iPsWithMostVisitsOnDay()---------------");
        ArrayList<String> maxIP = pr.iPsWithMostVisitsOnDay(dayToIP, "17/Mar");
    }


}

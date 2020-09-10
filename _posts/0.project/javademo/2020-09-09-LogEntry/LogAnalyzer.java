

import java.util.*;
import edu.duke.*;

public class LogAnalyzer{

    private ArrayList<LogEntry> records;
     

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }


    public LogEntry getthelog(String line) {

        int ip2index = line.indexOf(" ");
        String ipAddress = line.substring(0, ip2index);

        int time1index = line.indexOf("[");
        int time2index = line.indexOf("/20");
        String accessTime = line.substring(time1index+1, time2index);
        
        int request1index = line.indexOf('"');
        int request2index = line.lastIndexOf('"');
        String request = line.substring(request1index, request2index+1);

        
        int statusCode1index = line.lastIndexOf('"')+2;
        int statusCode2index = line.indexOf(" ", statusCode1index);
        int statusCode = Integer.parseInt(line.substring(statusCode1index, statusCode2index));

        
        int bytes1index = statusCode2index+1;
        int bytesReturned = Integer.parseInt(line.substring(bytes1index));

        int lo = 0;

        LogEntry log = new LogEntry(ipAddress, accessTime, request, statusCode, bytesReturned, lo);

        return log;
    }

    // the readFile method to create a FileResource and to iterate over all the lines in the file. For each line, create a LogEntry and store it in the records ArrayList.
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            // LogEntry log = WebLogParser.parseEntry(line);
            LogEntry log = getthelog(line);
            records.add(log);
            // System.out.println(line);
            // System.out.println(log);
        }
    }
  
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


    // This method should examine all the web log entries in records and print those LogEntrys that have a status code greater than num
    public void printAllHigherThanNum(int num) {
        ArrayList<Integer> numlist = new ArrayList<Integer>();
        for (LogEntry log : records) {
            // System.out.println(log.getStatusCode());
            int currcode = log.getStatusCode();
            if( num < currcode){
                // System.out.println(log);
                if(!numlist.contains(currcode)){
                    numlist.add(currcode);
                    System.out.println(currcode);
                }
            }
        }
        System.out.println(numlist.size());
    }


    // -----------------------------------------------------------------------
    // from record ArrayList<LogEntry>, get a HashMap<ip, visit times> visitCounts
    // -----------------------------------------------------------------------
    // This method returns a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in records, meaning the number of times this IP address visited the website.
    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> visitCounts = new HashMap<String,Integer>();
        for(LogEntry log : records){
            String ip = log.getIpAddress();
            if( !visitCounts.containsKey(ip)){
                visitCounts.put(ip, 1);
            }
            else{
                visitCounts.put(ip, visitCounts.get(ip)+1 );
            }
        }
        System.out.println(visitCounts);
        System.out.println(visitCounts.size());
        return visitCounts;
    }
      
    // -----------------------------------------------------------------------
    // from a HashMap<ip, visit times>, return the max visit time
    // -----------------------------------------------------------------------
    // This method returns a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in records, meaning the number of times this IP address visited the website.
    public int mostNumberVisitsByIP(HashMap<String, Integer> source) {
        int maxTimes = 0;
        String maxIP = null;
        for(String ip : source.keySet()){
            int currTimes = source.get(ip);
            if(maxTimes < currTimes){
                maxTimes = currTimes;
                maxIP = ip;
            }
        }
        System.out.println(maxTimes);
        return maxTimes;
    }

    
    // -----------------------------------------------------------------------
    // from a HashMap<ip, visit times>, return the Unique IPs total.
    // -----------------------------------------------------------------------
    // This method should return an integer representing the number of unique IP addresses. It should also assume that the instance variable records already has its ArrayList of Strings read in from a file, and should access records in computing this value.
    public int countUniqueIPs() {
        HashMap<String,Integer> visitCounts = countVisitsPerIP();
        int countofUniqueIPs = visitCounts.size();
        System.out.println("The log has " + countofUniqueIPs + " Unique IPs.");
        return countofUniqueIPs;
    }


    // -----------------------------------------------------------------------
    // from HashMap<ip, visit times> visitCounts, return the Unique IPs for onr day
    // -----------------------------------------------------------------------
    // This method accesses the web logs in records and returns an ArrayList of Strings of unique IP addresses that had access on the given day. 
    // 30/Sep 
    public void uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueip = new ArrayList<String>();
        for(LogEntry log : records){
            String accessTime = log.getAccessTime();
            String ip = log.getIpAddress();
            if(accessTime.equals(someday)) {
                if( !uniqueip.contains(ip) ){
                    uniqueip.add(ip);
                    System.out.println(ip);
                }
            }
        }
        System.out.println(uniqueip);
        System.out.println(uniqueip.size());
    }
     
    
    // -----------------------------------------------------------------------
    // from HashMap<ip, visit times> visitCounts, return the Unique IPs in range
    // -----------------------------------------------------------------------
    // This method returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive
    public void countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueipInRange = new ArrayList<String>();
        for (LogEntry log : records) {
            int currcode = log.getStatusCode();
            if(low <= currcode && currcode < high){
                String ip = log.getIpAddress();
                if(!uniqueipInRange.contains(ip)){
                    uniqueipInRange.add(ip);
                    System.out.println(ip + " " + log.getStatusCode());
                }
            }
        }
        System.out.println(uniqueipInRange.size());
    }


    // -----------------------------------------------------------------------
    // from HashMap<ip, visit times> visitCounts, return Most Visits IPs ArrayList<String>
    // -----------------------------------------------------------------------
    // from <ip, visitTimes> get the most visit ip
    // This method returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website.
    public ArrayList<String> iPsMostVisits() {
        ArrayList<String> maxIP = new ArrayList<String>();
        HashMap<String,Integer> visitCounts = countVisitsPerIP();
        int maxTimes = mostNumberVisitsByIP(visitCounts);
        for(String ip : visitCounts.keySet()){
            if( maxTimes == visitCounts.get(ip) ){
                maxIP.add(ip);
            }
        }
        System.out.println(maxIP);
        return maxIP;
    }


    // -----------------------------------------------------------------------
    // from HashMap<ip, visittimes> visitCounts, return day ip record HashMap<day, <ip1 ip1 ip2>> dayToIP
    // -----------------------------------------------------------------------
    // This method returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses).
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dayToIP = new HashMap<String, ArrayList<String>>();
        for (LogEntry log : records){
            String currDay = log.getAccessTime();
            String currIP = log.getIpAddress();
            if( !dayToIP.containsKey(currDay) ){
                ArrayList<String> ipforDay = new ArrayList<String>();
                ipforDay.add(currIP);
                dayToIP.put(currDay, ipforDay);
            }
            else{
                ArrayList<String> ipforDay = dayToIP.get(currDay);
                ipforDay.add(currIP);
                dayToIP.put(currDay, ipforDay);
            }
        }
        for(String day : dayToIP.keySet()){
            System.out.println(day);
            System.out.println(dayToIP.get(day));
        }
        return dayToIP;
    }


    // -----------------------------------------------------------------------
    // from HashMap<day, <ip1 ip1 ip2>> dayToIP, return the most visits IP day
    // -----------------------------------------------------------------------
    // This method returns the day that has the most IP address visits. If there is a tie, then return any such day.
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayToIP) {
        int size = 0;
        String mostIPday = null;
        for(String day : dayToIP.keySet()){
            int ipsNum = dayToIP.get(day).size();
            if(size < ipsNum){
                mostIPday = day;
                size = ipsNum;
            }
        }
        System.out.println("the day that has the most IP address visits: " + mostIPday);
        return mostIPday;
    }

    

    // -----------------------------------------------------------------------
    // from HashMap<day, <ip1 ip1 ip2>> dayToIP, return the most visits IP of one day
    // -----------------------------------------------------------------------
    // This method returns the day that has the most IP address visits. If there is a tie, then return any such day.
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayToIP, String day) {
        int maxTime = 0;
        ArrayList<String> maxIP = new ArrayList<String>();
        for(String date : dayToIP.keySet()){
            if(date.equals(day)){
                HashMap<String,Integer> FreqsIP = new HashMap<String,Integer>();
                ArrayList<String> ipList = dayToIP.get(date);
                for(String ip : ipList){
                    if( ! FreqsIP.containsKey(ip) ){
                        FreqsIP.put(ip, 1);

                    }
                    else{
                        FreqsIP.put(ip, FreqsIP.get(ip)+1 );
                    }
                }
                maxTime = mostNumberVisitsByIP(FreqsIP);
                for(String ip : FreqsIP.keySet()){
                    if(FreqsIP.get(ip) == maxTime){
                        maxIP.add(ip);
                    }
                }
            }
        }
        System.out.println(maxTime);
        System.out.println(maxIP);
        return maxIP;
    }


}






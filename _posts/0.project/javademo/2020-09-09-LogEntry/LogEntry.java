


import  java.util.*;

import sun.security.x509.IPAddressName;


public class LogEntry {

     private String ipAddress;
     private String accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;
     private int locatioin;
     

      public LogEntry(String ip, String time, String req, int status, int bytes, int lo) {
            ipAddress = ip;
            accessTime = time;
            request = req;
            statusCode = status;
            bytesReturned = bytes;
            locatioin = 0;
      }
   
      
      public String getIpAddress() {
            return ipAddress;
      }
      public String getAccessTime() {
            return accessTime;
      }   
      public String getRequest() {
            return request;
      }
      public int getStatusCode() {
            return statusCode;
      }
      public int getBytesReturned() {
            return bytesReturned;
      }
      
      // defact,
      // LogEntry le = new LogEntry()
      // system.out.println(le)
      public String toString() {
            return ipAddress + " " + accessTime + " " + request + " " + statusCode + " " + bytesReturned;
      }

}

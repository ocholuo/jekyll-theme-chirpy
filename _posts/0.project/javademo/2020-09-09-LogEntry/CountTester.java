




import java.util.*;


public class CountTester {
    LogAnalyer la = new LogAnalyzer();
    la.readFile('data/short-test_log');
    HashMap<String,Integer> counts = la.counVisitesPerIO();
    
}

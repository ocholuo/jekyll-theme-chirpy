

// 亚麻的每个订单第一段为认证号，后面接纯字母代表prime order； 后面接纯数字代表non-prime order。要求给prime order 按照字典顺序排在前面，non-prime order按照其原始顺序加到队尾。
// Example:
// Input:
// {
//     ["a1 9 2 3 1"],
//     ["g1 act car"],
//     ["zo4 4 7"],
//     ["ab1 off key dog"],
//     ["a8 act zoo"]
// }

// Output: 
// {
//     ["g1 act car"],
//     ["a8 act zoo"],
//     ["ab1 off key dog"],
//     ["a1 9 2 3 1"],
//     ["zo4 4 7"]
// }


import java.util.List;
import java.util.*;
// import java.util.Stack;

public class solution200927{


    // solution
    public String[] reorderLogFiles(String[] logs) {

        // compare(log1, log2)
        // (text, text):
        // <0; = log2, log1
        // >0; = log1, log2
        // (text, num): nochange -1 <0
        // (num, text): change 1 > 0

        Comparator<String> myComp = new Comparator<String>() {

            @Override
            public int compare(String log1, String log2) {
                // split each log into two parts: <identifier, content>
                String[] split1 = log1.split(" ", 2); // before index 2, split by " "
                String[] split2 = log2.split(" ", 2);

                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

                // case 1). both logs are letter-logs
                if (!isDigit1 && !isDigit2) {
                    // first compare the content
                    int alphcompare = split1[1].compareTo(split2[1]);
                    if (alphcompare != 0){
                        return alphcompare; 
                        // > 0 split2, split1
                        // < 0 split1, split2
                    }
                    // logs of same content, compare the identifiers
                    // 2nd part same, compare first part
                    return split1[0].compareTo(split2[0]);
                }

                // case 2). one of logs is digit-log
                if (!isDigit1 && isDigit2)
                    // the letter-log comes before digit-logs
                    return -1;
                else if (isDigit1 && !isDigit2)
                    return 1;
                else
                    // case 3). both logs are digit-log
                    return 0;
            }
        };

        Arrays.sort(logs, myComp);
        return logs;
    }
    











    // my
    String alph = "abcdefghijklmnopqrstuvwxyz";

    public boolean checkprime(String order){
        int startindex = order.indexOf(" ");
        String startwrd = order.substring(startindex+1, startindex+2);
        if(alph.indexOf(startwrd)==-1){
            System.out.println("not prime");
            return false;
        }
        else{
            System.out.println("prime");
            return true;
        }
    }


    public List<String> compare(List<String> result){
        int n = result.size()-1;
        for(int i = 0; i < n-1 ; ++i) {
            for (int j = i + 1; j < n; ++j) {
            String currOrder = result.get(i);
            if(result.get(i).compareTo(result.get(j)) > 0){
                String temp = result.get(i);
                result.set(i) = result.get(j);
                result.set(i+1) = result.get(i);
            }
        }
        return List<String> result;
    }

    List<String> prioritizedOrders(int numOrders, List<String> orderList) {
        List<String> result = new ArrayList<String>();
        for(int i=0; i<numOrders; i++) {
            String currstr = orderList.get(i);
            if(Boolean.TRUE.equals(checkprime(currstr))){
                result.add(currstr);
            }
        }

        result = compare(result);
        // for(String ans : result){
        //     System.out.println(ans);
        // }
        return result;
    }
    
    
    public static void main(String[] args) {
        List<String> orderList = Arrays.asList("a abc", "a 12", "a echo");
        int numOrders = orderList.size();
        
        solution200927 pr = new solution200927();

        System.out.println("hello");

        List<String> result = pr.prioritizedOrders(numOrders, orderList);
        // System.out.println("result is: " + result);
        
    }







}
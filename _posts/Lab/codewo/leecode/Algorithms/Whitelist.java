

public class Whitelist {

    public static String randomString(int L, String alpha){
        char[] a = new char[L];
        for(int i=0; i< L; i++) {
            int t = StdRandom.uniform(alpha.length());
            a[i] = alpha.charAt(t);
        }
        return new String(a);
    }

    public static int search(String key, String[] a) {

        // 1. Sequential search
        for (int i=0; i<a.length; i++){
            if(a[i].compareTo(key) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String words = in.readAllStrings();
        while(!StdIn.isEmpty()){
            String key = StdIn.readString();
            if search(key, words != -1) {
                StdOut.println(key);
            }
        }
    }

}


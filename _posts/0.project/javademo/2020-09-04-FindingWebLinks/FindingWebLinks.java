




public class FindingWebLinks {
    
    // Part 4: Finding Web Links
    public void findURL(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String linkstring : ur.lines()) {
            String s = linkstring.toLowerCase();
            int pos = s.indexOf("youtube.com");
            if(pos != -1){
                // System.out.println(s);
                int beginIndex = s.lastIndexOf("\"", pos);
                if(beginIndex != -1){
                    int endIndex = s.indexOf("\"", beginIndex+1);
                    // System.out.println(beginIndex);
                    // System.out.println(endIndex);
                    String resultlink = linkstring.substring(beginIndex+1, endIndex);
                    System.out.println(resultlink);
                }
            }
        }
    }

    public static void main(String[] args) {
        FindingWebLinks pr = new FindingWebLinks();
        pr.findURL();
    }


}

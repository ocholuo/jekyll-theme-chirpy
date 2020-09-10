


import edu.duke.*;
import java.util.*;

public class CodonCount {

    private HashMap<String,Integer> codonMap;

    public CodonCount(){
        codonMap = new HashMap<String, Integer>();
        
    }

    public void buildCodonMap(int start, String dna) {
        int uniquecodon = 0;
        for(int i = start; i+3 <= dna.length(); i+=3){
            String codon = dna.substring(i, i+3);
            // System.out.println(codon);
            if(!codonMap.containsKey(codon)){
                // System.out.println("new codon");
                codonMap.put(codon, 1);
            }
            else{
                // System.out.println("codon existed");
                codonMap.put(codon, codonMap.get(codon)+1 );
            }
        }
        System.out.println("Reading frame starting with " + start + " results in " + codonMap.size() + " unique codons.");
    }

    // This method returns a String, the codon in a reading frame that has the largest count. If there are several such codons, return any one of them. This method assumes the HashMap of codons to counts has already been built.
    public String getMostCommonCodon(){
        int maxcount = 0;
        String maxcodon = null;
        for(String codon : codonMap.keySet()){
            if(maxcount < codonMap.get(codon)){
                maxcount = codonMap.get(codon);
                maxcodon = codon;
            }
        }
        System.out.println("and most common codon is " + maxcodon + " with count " + maxcount);
        // for(String codon : codonMap.keySet()){
        //     System.out.println(codon + codonMap.get(codon));
        // }
        return maxcodon;
    }


    // This method prints all the codons in the HashMap along with their counts if their count is between start and end, inclusive.
    public void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for(String codon : codonMap.keySet()){
            if(start <= codonMap.get(codon) && codonMap.get(codon) <= end){
                System.out.println(codon + "  " + codonMap.get(codon));
            }
        }
    }

    public static void main(String[] args) {
    
        CodonCount pr = new CodonCount();
        FileResource fr = new FileResource();
        String dna = fr.asString();
        System.out.println("---------------test buildCodonMap()---------------");
        // String dna  = "CGTTCAAGTTCAA";
        // pr.buildCodonMap(0, dna);
        pr.buildCodonMap(1, dna);
        // pr.buildCodonMap(2, dna);

        System.out.println("---------------test getMostCommonCodon()---------------");
        pr.getMostCommonCodon();

        System.out.println("---------------test printCodonCounts()---------------");
        pr.printCodonCounts(5, 7);

    }
    
}

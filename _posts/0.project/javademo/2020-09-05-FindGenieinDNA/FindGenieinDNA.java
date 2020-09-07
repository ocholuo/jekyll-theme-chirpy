


// Part 1
// This assignment is to write the code from the lesson to use a StorageResource to store the genes you find instead of printing them out. This will help you see if you really understood how to put the code together, and might identify a part that you did not fully understand. If you get stuck, then you can go back and watch the coding videos that go with this lesson again.
// Specifically, you should do the following:
// 1. new Java project named StringsThirdAssignments. You can put all the classes for this programming exercise in this project.
// 2. new Java Class named Part1. Copy and paste the code from your Part1 class in your StringsSecondAssignments project into this class.
// 3. Make a copy of the printAllGenes method called getAllGenes. Instead of printing the genes found, this method should make and return a StorageResource containing the genes found. Remember to import the edu.duke libraries otherwise you will get an error message cannot find the class StorageResource.
// 4. Make sure you test your getAllGenes method.


// Part 2
// Write the method cgRatio that has one String parameter dna, and returns the ratio of C's and G's in dna as a fraction of the entire strand of DNA. For example if the String were "ATGCCATAG," then cgRatio would return 4/9 or .4444444.
// Hint: 9/2 uses integer division because you are dividing an integer by an integer and thus Java thinks you want the result to be an integer. If you want the result to be a decimal number, then make sure you convert one of the integers to a decimal number by changing it to a float. For example, (float) 9/2 is interpreted by Java as 9.0/2 and if one of the numbers is a decimal, then Java assumes you want the result to be a decimal number. Thus (float) 9/2 is 4.5.
// Write method countCTG that has one String parameter dna, and returns the number of times the codon CTG appears in dna.



// Part 3
// Write the void method processGenes that has one parameter sr, which is a StorageResource of strings. This method processes all the strings in sr to find out information about them. Specifically, it should:
// print all the Strings in sr that are longer than 9 characters
// print the number of Strings in sr that are longer than 9 characters
// print the Strings in sr whose C-G-ratio is higher than 0.35
// print the number of strings in sr whose C-G-ratio is higher than 0.35
// print the length of the longest gene in sr




import java.lang.Object;
import edu.duke.*;


// Nucleotide =+ Condon
// ATG start Condon
// TAA stop Condon


public class FindGenieinDNA {
    


    // Part 3: Problem Solving with Strings
    // Write the method named twoOccurrences that has two String parameters named stringa and stringb. This method returns true if stringa appears at least twice in stringb, otherwise it returns false. For example, the call twoOccurrences(“by”, “A story by Abby Long”) returns true as there are two occurrences of “by”, the call twoOccurrences(“a”, “banana”) returns true as there are three occurrences of “a” so “a” occurs at least twice, and the call twoOccurrences(“atg”, “ctgtatgta”) returns false as there is only one occurence of “atg”.

    // public boolean twoOccurrences(String smalldna, String bigdna){
    //     String gene1 = findGeneSimple(smalldna);
    //     if(bigdna.indexOf(gene1) != -1){
    //         int fristIndex = bigdna.indexOf(gene1);
    //         if(bigdna.indexOf(gene1, fristIndex+3) != -1){
    //             return true;
    //         }
    //     }
    //     return false;
    // } 

    // Write the method named lastPart that has two String parameters named stringa and stringb. This method finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa. If stringa does not occur in stringb, then return stringb. For example, the call lastPart(“an”, “banana”) returns the string “ana”, the part of the string after the first “an”. The call lastPart(“zoo”, “forest”) returns the string “forest” since “zoo” does not appear in that word.

    // public String lastPart(String stringa, String stringb) {
    //     if(stringb.indexOf(stringa) != -1){
    //         int beginIndex = stringb.indexOf(stringa) + stringa.length();
    //         String result = stringb.substring(beginIndex, stringb.length());
    //         return result;
    //     }
    //     return "";
    // }



    // Part 1: Finding a Gene - Using the Simplified Algorithm
    // public String findGeneSimple(String dna){
    //     String result = "";
    //     int startIndex = dna.indexOf("ATG");
    //     if(startIndex == -1){
    //         return "cannot find ATG";
    //     }
    //     int currIndex = dna.indexOf("TAA", startIndex+3);
    //     while(currIndex != -1){
    //         if((currIndex - startIndex) % 3 == 0){
    //             result = dna.substring(startIndex, currIndex+3);
    //             return result;
    //         }
    //         else {
    //             currIndex = dna.indexOf("TAA", currIndex+1);
    //         }
    //     }
    //     return "cannot find TAA";
    // }


    // public void testSimpleGene() {
    //     String dna1 = "ATGxxxTAAxxxyyyxxx";
    //     String dna2 = "ATGxxTAAxxxyyyxxx";
    //     String gene1 = findGeneSimple(dna1);
    //     String gene2 = findGeneSimple(dna2);
    //     System.out.println("DNA stand:" + dna1);
    //     System.out.println("Gene is:" + gene1);
    //     System.out.println("DNA stand:" + dna2);
    //     System.out.println("Gene is:" + gene2);
    // }


    // week1: better code
    // find single gene start from index "where" in String dna
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = taaIndex;
        if(taaIndex == -1 || tgaIndex != -1 && tgaIndex < taaIndex){
            minIndex = tgaIndex;
        }
        if(minIndex == -1 || tagIndex != -1 && tagIndex < minIndex){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }

    // find single gene start from index "startIndex" in String dna that stop by Gene "String stopCondon TAA/TAG/TGA"
    public int findStopCodon(String dna, int startIndex, String stopCondon){
        int currIndex = dna.indexOf(stopCondon, startIndex+3);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCondon, currIndex+1);
            }
        }
        return -1;
    }


    // public void testFindStop() {
    //     String dna = "xxxyyyxxxATGxxxTAAxx";
    //     int stopCondon = findStopCodon(dna, 0, "TAA");
    //     System.out.println("DNA stand:" + dna);
    //     System.out.println("stopCondon position is:" + stopCondon);
    // }



    // find multiple Genes
    // public int findMultipleGene(String dna){
    //     int count = 0;
    //     int startIndex = 0;
    //     while(true){
    //         String currGene = findGene(dna, startIndex);
    //         if(currGene.isEmpty()){
    //             break;
    //         }
    //         System.out.println(currGene);
    //         count++;
    //         startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
    //     }
    //     return count;
    // }
    //


    // week 2: store all gene in list
    public StorageResource getAllGene(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()){
                break;
            }
            geneList.add(currGene);
            // System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return geneList;
    }

    public int countCG(String dna) {
        int count = 0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C'){
                count++;
            }
            if(dna.charAt(i) == 'G'){
                count++;
            }
        }
        return count;
    }

    public float cgRatio(String dna) {
        int count = dna.length();
        float ctcount = countCG(dna);
        // System.out.println("C/G count is: " + ctcount);
        return ctcount/count;
    }



    public void processGenes(StorageResource sr) {

        // System.out.println("all the Strings in sr that are longer than 60 characters: ");
        int ninecharcount = 0;
        for(String gene : sr.data()){
            if(gene.length() > 60){
                // System.out.println(gene);
                ninecharcount++;
            }
        }
        System.out.println("the number of Strings in sr that are longer than 60 characters: " + ninecharcount);

        System.out.println("");
        // System.out.println("the Strings in sr whose C-G-ratio is higher than 0.35: ");
        int highCGcount = 0;
        for(String gene : sr.data()){
            if(cgRatio(gene) > 0.35) {
                // System.out.println(gene);
                highCGcount++;
            }
        }
        System.out.println("the number of strings in sr whose C-G-ratio is higher than 0.35: " + highCGcount);

        System.out.println("");
        String longestGene = "";
        for(String gene : sr.data()){
            if(gene.length() > longestGene.length()){
                longestGene = gene;
            }
        }
        System.out.println("the length of the longest gene in sr: " + longestGene);
    }


    public static void main(String[] args) {
        FindGenieinDNA pr = new FindGenieinDNA();

        // System.out.println("------------------test testSimpleGene()------------------");
        // pr.testSimpleGene();

        // System.out.println("------------------test twoOccurrences()------------------");
        // String dna1 = "ATGxxxTAAxxxyyyxxx";
        // String dna2 = "ATGxxTAAxxxyyyxxx";
        // if(pr.twoOccurrences(dna1, dna2)){
        //     System.out.println("there are two occurrences of dna gene of small dna in big dna.");
        // }

        // System.out.println("------------------test lastPart()------------------");
        // String stringa = "an";
        // String stringb = "banana";
        // String result = pr.lastPart(stringa, stringb);
        // System.out.println("The part of the string after '" + stringa + "' in '" + stringb + "': " + result);

        // System.out.println("------------------test testFindStop()------------------");
        // pr.testFindStop();

        // System.out.println("------------------test findGene()------------------");
        // String result = pr.findGene("xxxyyyxxxATGxxxTAAxx");
        // System.out.println(result);

        // System.out.println("------------------test findMultipleGene()------------------");
        // //                              ATCv  TAAv  ATGv  v  v  TGAv
        // int num1 = pr.findMultipleGene("ATCoooTAAoooATGoooooooooTGAooo");
        // //                              ATGv  v  v  v  TAAv  v  v  ATGTAA
        // int num2 = pr.findMultipleGene("ATGooooooooooooTAAoooooooooATGTAA");
        // System.out.println("num1 has: " + num1 + "Gene");
        // System.out.println("num2 has: " + num2 + "Gene");


        System.out.println("------------------test getAllGene()------------------");
        // start Condon: ATG 
        // stop Condon: TAA/TAG/TGA"
        //                                    "ATGooo   ooo   ooo   ooo   ooo   ooo   ooo   ooo   ooo   ooo   ooo   ooo   CCAATAA"
        StorageResource genes = pr.getAllGene("ATGCCTATTGGATCCAAAGAGAGGCCAACATTTTTTGAAATTTTTAAGACACGCTGCAACAAAGCAGATTTAGGACCAATAA");
        System.out.println("This is the list of genes: ");
        for(String g : genes.data()){
            System.out.println(g);
            double ctcount = pr.cgRatio(g);
            System.out.println("the ratio of C’s and G’s in dna is: " + ctcount);
        }

        System.out.println("------------------input dna from file, tostring, find all gene, then process it:------------------");
        FileResource fr = new FileResource("dna-brca1line.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
        System.out.println("");

        StorageResource sr = pr.getAllGene(dna);
        pr.processGenes(sr);
        for(String g : sr.data()){
            System.out.println(g);
            
        }
    }

}

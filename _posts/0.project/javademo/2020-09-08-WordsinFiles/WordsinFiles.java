

import edu.duke.*;
import java.io.File;
import java.util.*;

public class WordsinFiles {

    private HashMap<String, ArrayList<String>> wrdcount;

    public WordsinFiles(){
        wrdcount = new HashMap<String, ArrayList<String>>();
    }
    

    // This method should add all the words from f into the map. 
    // If a word is not in the map, then you must create a new ArrayList of type String with this word, and have the word map to this ArrayList. If a word is already in the map, then add the current filename to its ArrayList, unless the filename is already in the ArrayList. You can use the File method getName to get the filename of a file.
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        // ArrayList<String> filename = new ArrayList<String>();
        for(String wrd: fr.words()){
            if( !wrdcount.containsKey(wrd) ){
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(filename);
                wrdcount.put(wrd, fileList);
            }
            else{
                if(!wrdcount.get(wrd).contains(filename)){
                    wrdcount.get(wrd).add(filename);
                }
            }
        }
        // for(String wrd : wrdcount.keySet()){
        //     System.out.print(wrd + ": ");
        //     System.out.println(wrdcount.get(wrd));
        // }

        System.out.println("word size: " + wrdcount.size());
    }


    // This method first clears the map, and then uses a DirectoryResource to select a group of files. For each file, it puts all of its words into the map by calling the method addWordsFromFile. The remaining methods to write all assume that the HashMap has been built.
    public void buildWordFileMap(){
        wrdcount.clear();
        DirectoryResource dr = new DirectoryResource();      
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }


    // This method returns the maximum number of files any word appears in, considering all words from a group of files. In the example above, there are four files considered. No word appears in all four files. Two words appear in three of the files, so maxNumber on those four files would return 3. This method assumes that the HashMap has already been constructed.
    public int maxNumber(){
        int maxnum = 0;
        String mostcommwrd = null;
        for(String word : wrdcount.keySet()){
            if(maxnum < wrdcount.get(word).size()){
                maxnum = wrdcount.get(word).size();
                mostcommwrd = word;
            }
        }
        System.out.println("most common word is " + mostcommwrd);
        System.out.println("appear in " + maxnum + " files.");
        // for(String codon : codonMap.keySet()){
        //     System.out.println(codon + codonMap.get(codon));
        // }
        return maxnum;
    }



    // This method returns an ArrayList of words that appear in exactly number files. 
    // wordsInNumFiles(3) would return an ArrayList with the words “cats” and “and”
    // wordsInNumFiles(2) would return an ArrayList with the words “love”, “are”, and “dogs”, all the words that appear in exactly two files.
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordlist = new ArrayList<String>();
        int count = 0;
        for(String word : wrdcount.keySet()){
            if(number == wrdcount.get(word).size()){
                wordlist.add(word);
                count++;
            }
        }
        System.out.println(count + " words that appear in exactly " + number + " files.");
        // for(String word : wordlist){
        //     System.out.print(word + ", ");
        //     System.out.println();
        // }
        return wordlist;
    }


    // This method prints the names of the files this word appears in, one filename per line. 
    // printFilesIn(“cats”) would print the three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a separate line.
    public void printFilesIn(String word){
        if(wrdcount.containsKey(word)){
            System.out.println(wrdcount.get(word));
        }
        wrdcount.clear();
    }


    public static void main(String[] args) {

        WordsinFiles pr = new WordsinFiles();

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){

            System.out.println();
            System.out.println("---------------File " + f.getName() + " .TXT ---------------");
            System.out.println();

            // System.out.println("---------------test buildWordFileMap()---------------");
            // pr.buildWordFileMap();

            System.out.println("---------------test addWordsFromFile()---------------");
            pr.addWordsFromFile(f);

            // System.out.println("---------------test maxNumber()---------------");
            // int max = pr.maxNumber();

            // System.out.println("---------------test wordsInNumFiles()---------------");
            // ArrayList<String> wordlist = pr.wordsInNumFiles(4);


            System.out.println("---------------test wrdnotexit()---------------");
            pr.printFilesIn("sad");

        }

        // System.out.println("---------------test buildWordFileMap()---------------");
        // pr.buildWordFileMap();


    }


}

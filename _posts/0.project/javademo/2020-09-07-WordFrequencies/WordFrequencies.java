

import edu.duke.*;
import java.util.ArrayList;


public class WordFrequencies{

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    private ArrayList<String> charac;
    private ArrayList<Integer> characFreqs;


    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
        charac = new ArrayList<>();
        characFreqs = new ArrayList<>();
        
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        for(String s : fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + " is: " + myFreqs.get(i));
        }
        int index = findIndexOfMax();
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }

    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }


    public void update(String name){
        int index = charac.indexOf(name);
        if(index == -1){
            charac.add(name);
            characFreqs.add(1);
        }
        else{
            int currFreqs = characFreqs.get(index);
            characFreqs.set(index, currFreqs+1);
        }
    }


    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            if(line.indexOf("    ") != 0){
                if(line.indexOf(".") != -1){
                    String name = line.substring(0, line.indexOf("."));
                    System.out.println(name);
                    update(name);
                }
            }
        }
        for(int i = 0; i < charac.size(); i++){
            System.out.println(charac.get(i) + ": " + characFreqs.get(i));
        }
    }
    

    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < charac.size(); i++){
            if(num1 <= characFreqs.get(i) && characFreqs.get(i) <= num2){
                String name = charac.get(i);
                System.out.println(name);
            }
        }
    }

    public static void main(String[] args) {
        WordFrequencies pr = new WordFrequencies();

        System.out.println("---------------test findAllCharacters()---------------");
        pr.findAllCharacters();

        System.out.println("---------------test charactersWithNumParts()---------------");
        pr.charactersWithNumParts(10, 15);

        System.out.println("---------------test findUnique()---------------");
        pr.tester();

    }

}


// Programming Exercise: Implementing the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher

// Programming Exercise: Breaking the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/727CD/programming-exercise-breaking-the-caesar-cipher


import edu.duke.*;
import sun.net.www.content.text.plain;

import java.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;


public class CaesarCipherAlgorithm {

    // This class has two fields. instance variables
    // A field is a special kind of variable which lives inside of an object instead of inside of a method.
    // private String alph;
    // private String shiftedAlphU;
    // private String shiftedAlphL;


    // constructor
    // code that gets run to initialize an object when it is created using new.
    // public CaesarCipherAlgorithm(int key){
    //     alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //     shiftedAlphU = (alph.substring(key) + alph.substring(0,key)).toUpperCase();
    //     shiftedAlphL = alph.substring(key) + alph.substring(0,key).toLowerCase();
    // }

    // public String caesarcipher(String plaintext, int key){
    public String caesarcipher(String plaintext, int key){
        String alphU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphL = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphU = alphU.substring(key) + alphU.substring(0,key);
        String shiftedAlphL = alphL.substring(key) + alphL.substring(0,key);
        System.out.println(shiftedAlphU);

        StringBuilder ciphertext = new StringBuilder(plaintext);

        for(int i = 0; i < plaintext.length(); i++){
            char currChar = plaintext.toUpperCase().charAt(i);
            int alphindex = alphU.indexOf(currChar);

            if(alphindex != -1 && Character.isLowerCase(plaintext.charAt(i))){
                char encrChar = shiftedAlphL.charAt(alphindex);
                // ciphertext.append(encrChar);
                ciphertext.setCharAt(i, encrChar);
            }

            if(alphindex != -1 && Character.isUpperCase(plaintext.charAt(i))){
                char encrChar = shiftedAlphU.charAt(alphindex);
                // ciphertext.append(encrChar);
                ciphertext.setCharAt(i, encrChar);
            }
        }
        System.out.println("plaintext: ");
        System.out.println(plaintext);
        System.out.println("ciphertext: ");
        System.out.println(ciphertext.toString());
        return ciphertext.toString();
    }
    

    // This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise. You should write a tester method to see if this method works correctly.
    public boolean isVowel(char ch) {
        String vowel = "aeiouAEIOU";
        Character c0 = ch;

        for(int i = 0; i < vowel.length(); i++){
            char c1 = vowel.charAt(i);
            if(c0.equals(c1)){
                System.out.println(c0 + " is vowel.");
                return true;
            }
        }
        System.out.println(c0 + " is not vowel.");
        return false;
    }


    // This method should return a String that is the string phrase with all the vowels (uppercase or lowercase) replaced by ch.
    public String replaceVowels(String phrase, char ch){
        StringBuilder ans = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++){
            char currChar = phrase.charAt(i);
            if( isVowel(currChar) ){
                ans.setCharAt(i, ch);
            }
            else{
                ans.setCharAt(i, currChar);
            }
        }
        System.out.println(ans);
        return ans.toString();
    }


    // Write a method emphasize with two parameters, a String named phrase and a character named ch. This method should return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by
    // ‘*’ if it is in an odd number location in the string (e.g. the first character has an odd number location but an even index, it is at index 0), or
    // ‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but an odd index, it is at index 1).
    public String emphasize(String phrase, char ch){
        StringBuilder ans = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++){
            char currChar = phrase.charAt(i);
            char c0 = Character.toUpperCase(ch);
            char c1 = Character.toLowerCase(ch);
            if( c0 == currChar || c1 == currChar){
                if(i==0){
                    ans.setCharAt(i, '*');
                }
                else if(i==1){
                    ans.setCharAt(i, '+');
                }
                else if((i+1)%2 == 1){
                    ans.setCharAt(i, '*');
                }
                else {
                    ans.setCharAt(i, '+');
                }
            }
        }
        System.out.println(ans);
        return ans.toString();
    }



    // This method returns a String that has been encrypted using the following algorithm. Parameter key1 is used to encrypt every other character with the Caesar Cipher algorithm, starting with the first character, and key2 is used to encrypt every other character, starting with the second character. 
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if((i+1)%2==1){
                String cipherStr = caesarcipher(input.substring(i, i+1), key1);
                ans.append(cipherStr);
            }
            else{
                String cipherStr = caesarcipher(input.substring(i, i+1), key2);
                ans.append(cipherStr);
            }
        }
        System.out.println(ans);
        return ans.toString();
    }


    // count 26 characters
    public int[] count26char(String s) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            char currChar = Character.toLowerCase(s.charAt(i));
            int index = alph.indexOf( currChar);
            if( index != -1){
                count[index] +=1;
            }
        }
        for(int i = 0; i < count.length; i++){
            System.out.println(alph.charAt(i) + ": " + count[i]);
        }
        return count;
    }

    
    // find the max index
    public int maxIndex(int[] freqs){
        int maxWrdIndex = 0;
        for(int i = 0; i < freqs.length; i++){
            if(freqs[maxWrdIndex] < freqs[i]){
                maxWrdIndex = i;
            }
        }
        System.out.println(maxWrdIndex + " is maxWrdIndex is: " + freqs[maxWrdIndex]);
        return maxWrdIndex;
    }


    // This method should read in the words from resource and count the number of words of each length for all the words in resource, storing these counts in the array counts.
    // counts[k] should contain the number of words of length k.
    // public void countWordLengths(FileResource resource, int[] counts){
    //     String alph = "abcdefghijklmnopqrstuvwxyz0123456789";
    //     for(String wrds : resource.words()){
    //         StringBuilder newwrd = new StringBuilder();
    //         for(int i = 0; i < wrds.length(); i++){
    //             char currChar = wrds.charAt(i);
    //             char currCharlower = Character.toLowerCase(currChar);
    //             if(alph.indexOf(currCharlower) != -1){
    //                 newwrd.append(currChar);
    //             }
    //         }
    //         int wrdlength = newwrd.length();
    //         counts[wrdlength]++;
    //         System.out.println(newwrd);
    //         System.out.println(wrdlength + " : " + counts[wrdlength]);
    //     }
    //     // for(int i = 0; i < counts.length; i++){
    //     //     System.out.println(i + " : " + counts[i]);
    //     // } 
    // }


    // This method should call countLetters to get an array of the letter frequencies in String s and then use maxIndex to calculate the index of the largest letter frequency, which is the location of the encrypted letter ‘e’, which leads to the key, which is returned.
    public int getkey(String ciphertext){
        int[] freqs = count26char(ciphertext);
        int maxWrdIndex = maxIndex(freqs);
        // e is most frequent letter which should be index 4
        int cipherkey = maxWrdIndex - 4;
        if(maxWrdIndex < 4){
            cipherkey = 4 - maxWrdIndex;
        }
        return cipherkey;
    }


    // index:
    //    k4
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    //  4                     23 = 26 - key = 26 - (old 4 - new *4)
    // DEFGHIJKLMNOPQRSTUVWXYZABC
    //    
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ

    //   k 4
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    //   4                     24 = 26 - key = 26 - (old 4 - new *4)
    // CDEFGHIJKLMNOPQRSTUVWXYZAB
    //    
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ

    //     4       <-k
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    //            11 = 25 - (14)key = (new *4 - old 4)
    //                4
    // PQRSTUVWXYZABCDEFGHIJKLMNO 
    //    
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ

    //  decrypt
    public String caesarBreaker(String ciphertext){
        // int[] freqs = count26char(ciphertext);
        // int maxWrdIndex = maxIndex(freqs);
        // e is most frequent letter which should be index 4
        // int cipherkey = maxWrdIndex - 4;
        // if(maxWrdIndex < 4){
        //     cipherkey = 4 - maxWrdIndex;
        // }
        int cipherkey = getkey(ciphertext);
        // int cipherkey = 2;
        System.out.println(cipherkey);
        return caesarcipher(ciphertext, 26 - cipherkey);
    }




    // This method should return a new String that is every other character from message starting with the start position.
    public String halfOfString(String message, int start) {
        StringBuilder ans = new StringBuilder();
        for(int i = start; i < message.length(); i+=2){
            char currChar = message.charAt(i);
            ans.append(currChar);
        }
        System.out.println(ans);
        return ans.toString();
    }
    
    // This method attempts to determine the two keys used to encrypt the message, prints the two keys, and then returns the decrypted String with those two keys. More specifically, this method should:

    public void decryptTwoKeys(String encrypted){
        String string0 = halfOfString(encrypted, 0);
        String string1 = halfOfString(encrypted, 1);
        StringBuilder ans = new StringBuilder();

        // String ans0 = "Rnlk idt ettewn";
        // String ans1 = "u iewl oba h id";

        String ans0 = caesarBreaker(string0);
        String ans1 = caesarBreaker(string1);

        System.out.println(ans0.length());
        System.out.println(ans1.length());
        System.out.println(ans0);
        System.out.println(ans1);

        for(int i = 0; i < ans0.length(); i++){
            char currChar0 = ans0.charAt(i);
            ans.append(currChar0);
            if(i < ans1.length()){
                char currChar1 = ans1.charAt(i);
                ans.append(currChar1);
            }
        }
        System.out.println(ans);
        // return ans.toString();
    }
    
    public static void main(String[] args) {
        CaesarCipherAlgorithm pr = new CaesarCipherAlgorithm();
        // FileResource fr = new FileResource("data/mysteryTwoKeysPractice.txt");
        // String plaintext = fr.asString();

        System.out.println("---------------test caesarcipher()---------------");
        int key = 14;
        System.out.println(key);
        // String plaintext = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! eeeeeeeeeeeeeeeeeee";
        String plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZreeeeeeeeeeeeeeeeeeeeeee";
        String ciphertext = pr.caesarcipher(plaintext, key);
        // int[] freqs = pr.count26char(plaintext);
        // int maxWrdIndex = pr.maxIndex(freqs);   // 
        // System.out.println(maxWrdIndex);
        // ciphertext = pr.caesarcipher("First Legion", 17);


        System.out.println("---------------test caesarBreaker()---------------");
        plaintext = pr.caesarBreaker(ciphertext);


        // System.out.println("---------------test isVowel()---------------");
        // boolean ans = pr.isVowel('a');
        // ans = pr.isVowel('k');

        // System.out.println("---------------test replaceVowels()---------------");
        // String ans = pr.replaceVowels("Hello World", '*');

        // System.out.println("---------------test emphasize()---------------");
        // String ans = pr.emphasize("dna ctgaaactga", 'a');

        // System.out.println("---------------test encryptTwoKeys()---------------");
        // String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        // int key1 = 8;
        // int key2 = 21;
        // System.out.println("plaintext :");
        // System.out.println(input);
        // String ciphertext = pr.encryptTwoKeys(input, key1, key2);


        // System.out.println("---------------test decryptTwoKeys()---------------");
        // String ciphertext = fr.asString();
        // // String ciphertext = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        // pr.decryptTwoKeys(ciphertext);



        // System.out.println("---------------test count26char()---------------");
        // int[] count = pr.count26char("ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz A");


        // System.out.println("---------------test maxIndex()---------------");
        // int maxindex = pr.maxIndex(count);

        // System.out.println("---------------test countWordLengths()---------------");
        // int[] counts = new int[50];
        // FileResource resource = new FileResource("data/smallHamlet.txt");
        // pr.countWordLengths(resource, counts);
        // int maxindex = pr.maxIndex(counts);
        // System.out.println("the most common word length in the file is: " + counts[maxindex]);


        // System.out.println("---------------test halfOfString()---------------");
        // String message = "Qbkm Zgis";
        // int start = 1;
        // pr.halfOfString(message, start);
    }
}

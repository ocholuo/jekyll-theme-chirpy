
// Programming Exercise: Implementing the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher

// Programming Exercise: Breaking the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/727CD/programming-exercise-breaking-the-caesar-cipher


import edu.duke.*;
import java.*;
import java.lang.reflect.Array;


public class CipherAlgorithm {
    
    public String caesarcipher(String plaintext, int key){
        String alphU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphL = "abcdefghijklmnopqrstuvwxyz";
        String encrU = alphU.substring(key) + alphU.substring(0,key);
        String encrL = alphL.substring(key) + alphL.substring(0,key);
        System.out.println(encrU);

        StringBuilder ciphertext = new StringBuilder(plaintext);

        for(int i = 0; i < plaintext.length(); i++){
            char currChar = plaintext.toLowerCase().charAt(i);
            int alphindex = alphL.indexOf(currChar);

            if(alphindex != -1 && Character.isLowerCase(plaintext.charAt(i))){
                char encrChar = encrL.charAt(alphindex);
                // ciphertext.append(encrChar);
                ciphertext.setCharAt(i, encrChar);
            }

            if(alphindex != -1 && Character.isUpperCase(plaintext.charAt(i))){
                char encrChar = encrU.charAt(alphindex);
                // ciphertext.append(encrChar);
                ciphertext.setCharAt(i, encrChar);
            }
        }
        System.out.println("ciphertext: ");
        System.out.println(ciphertext.toString());
        System.out.println("plaintext: ");
        System.out.println(plaintext);
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
    public void countWordLengths(FileResource resource, int[] counts){
        String alph = "abcdefghijklmnopqrstuvwxyz0123456789";
        for(String wrds : resource.words()){
            StringBuilder newwrd = new StringBuilder();
            for(int i = 0; i < wrds.length(); i++){
                char currChar = wrds.charAt(i);
                char currCharlower = Character.toLowerCase(currChar);
                if(alph.indexOf(currCharlower) != -1){
                    newwrd.append(currChar);
                }
            }
            int wrdlength = newwrd.length();
            counts[wrdlength]++;
            System.out.println(newwrd);
            System.out.println(wrdlength + " : " + counts[wrdlength]);
        }
        for(int i = 0; i < counts.length; i++){
            System.out.println(i + " : " + counts[i]);
        } 
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

    //     4       <- k
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    //            11 = 26 - key = (new *4 - old 4)
    //                4
    // PQRSTUVWXYZABCDEFGHIJKLMNO 
    //    
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ


    //  decrypt
    public String caesarBreaker(String ciphertext){
        int[] freqs = count26char(ciphertext);
        int maxWrdIndex = maxIndex(freqs);   // 
        // System.out.println(maxWrdIndex);
        // e is most frequent letter which should be index 4
        
        int newcipherkey = maxWrdIndex - 4;
        System.out.println(cipherkey);
        if(maxWrdIndex < 4){
            int key = 4 - maxWrdIndex;
            // newcipherkey = 26 - (4 - key);
            // return caesarcipher(ciphertext, cipherkey);
        }
        return caesarcipher(ciphertext, 26 - newcipherkey);
        // return caesarcipher(ciphertext, 11);
    }


    
    public static void main(String[] args) {
        CipherAlgorithm pr = new CipherAlgorithm();
        // int key = 17;
        // FileResource fr = new FileResource("data/smallHamlet.txt");
        // String plaintext = fr.asString();

        System.out.println("---------------test caesarcipher()---------------");
        int key = 2;
        System.out.println(key);
        // String plaintext = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZreeeeeeeeeeeeeeeeeeeeeee";
        String ciphertext = pr.caesarcipher(plaintext, key);
        int[] freqs = pr.count26char(plaintext);
        int maxWrdIndex = pr.maxIndex(freqs);   // 
        System.out.println(maxWrdIndex);
        // ciphertext = pr.caesarcipher("First Legion", 17);


        // System.out.println("---------------test isVowel()---------------");
        // boolean ans = pr.isVowel('a');
        // ans = pr.isVowel('k');

        // System.out.println("---------------test replaceVowels()---------------");
        // String ans = pr.replaceVowels("Hello World", '*');

        // System.out.println("---------------test emphasize()---------------");
        // String ans = pr.emphasize("dna ctgaaactga", 'a');

        // System.out.println("---------------test encryptTwoKeys()---------------");
        // String ans = pr.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);

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


        // System.out.println("---------------test caesarBreaker()---------------");
        // plaintext = pr.caesarBreaker(ciphertext);

        // String alphU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // String encrU = alphU.substring(17) + alphU.substring(0,17);
        // System.out.println(encrU);

    }
}

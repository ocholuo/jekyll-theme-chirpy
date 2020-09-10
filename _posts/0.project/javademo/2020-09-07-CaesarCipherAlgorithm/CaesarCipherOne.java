
// Programming Exercise: Implementing the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher

// Programming Exercise: Breaking the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/727CD/programming-exercise-breaking-the-caesar-cipher


import edu.duke.*;
import sun.net.www.content.text.plain;

import java.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;


public class CaesarCipherOne {

    // This class has two fields. instance variables
    // A field is a special kind of variable which lives inside of an object instead of inside of a method.
    private String alphU;
    private String shiftedAlphU;
    private String alphL;
    private String shiftedAlphL;

    private int mainKey;


    // constructor
    // code that gets run to initialize an object when it is created using new.
    public CaesarCipherOne(int key){
        alphU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphL = "abcdefghijklmnopqrstuvwxyz";

        shiftedAlphU = alphU.substring(key) + alphU.substring(0,key);
        shiftedAlphL = alphL.substring(key) + alphL.substring(0,key);

        System.out.println(alphU);
        System.out.println(shiftedAlphU);
        mainKey = key;
    }

    public String caesarcipher(String plaintext){
        StringBuilder ciphertext = new StringBuilder(plaintext);
        for(int i = 0; i < plaintext.length(); i++){
            char currChar = plaintext.toUpperCase().charAt(i);
            int alphindex = alphU.indexOf(currChar);
            if(alphindex != -1 && Character.isLowerCase(plaintext.charAt(i))){
                char encrChar = shiftedAlphL.charAt(alphindex);
                ciphertext.setCharAt(i, encrChar);
            }
            if(alphindex != -1 && Character.isUpperCase(plaintext.charAt(i))){
                char encrChar = shiftedAlphU.charAt(alphindex);
                ciphertext.setCharAt(i, encrChar);
            }
        }
        System.out.println("plaintext: ");
        System.out.println(plaintext);
        System.out.println("ciphertext: ");
        System.out.println(ciphertext.toString());
        return ciphertext.toString();
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

    public int getkey(String ciphertext){
        int[] freqs = count26char(ciphertext);
        int maxWrdIndex = maxIndex(freqs);
        // e is most frequent letter which should be index 4
        int cipherkey = maxWrdIndex - 4;
        if(maxWrdIndex < 4){
            cipherkey = 26 -(4 - maxWrdIndex);
        }
        return cipherkey;
    }


    //  decrypt
    public String caesarBreaker(String ciphertext){
        mainKey = 26 - getkey(ciphertext);
        System.out.println("mainKey is: " + mainKey);
        CaesarCipherOne pr = new CaesarCipherOne(mainKey);
        String plaintext = pr.caesarcipher(ciphertext);
        return plaintext;
    }
    

    
    public void test() {
        System.out.println("---------------test caesarcipher()---------------");
        // String plaintext = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! eeeeeeeeeeeeeeeeeee";
        String plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZreeeeeeeeeeeeeeeeeeeeeee";
        String ciphertext = caesarcipher(plaintext);

        System.out.println("---------------test caesarBreaker()---------------");
        plaintext = caesarBreaker(ciphertext);

    }
}

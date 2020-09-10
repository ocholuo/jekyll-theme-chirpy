
// Programming Exercise: Implementing the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher

// Programming Exercise: Breaking the Caesar Cipher
// https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/727CD/programming-exercise-breaking-the-caesar-cipher


import edu.duke.*;
import sun.net.www.content.text.plain;

import java.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;


public class CaesarCipherTwo {

    // This class has two fields. instance variables
    // A field is a special kind of variable which lives inside of an object instead of inside of a method.
    private String alph;
    private String shiftedAlph1;
    private String shiftedAlph2;
    private int keyfor1;
    private int keyfor2;


    // constructor
    // code that gets run to initialize an object when it is created using new.
    public CaesarCipherTwo(int key1, int key2){
        alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlph1 = alph.substring(key1) + alph.substring(0,key1);
        shiftedAlph2 = alph.substring(key2) + alph.substring(0,key2);
        keyfor1 = key1;
        keyfor2 = key2;
    }

    public String caesarcipher(String plaintext){
        StringBuilder ans = new StringBuilder(plaintext);

        String alphaLower = alph.toLowerCase();
        String shiftLower1 = shiftedAlph1.toLowerCase();
        String shiftLower2 = shiftedAlph2.toLowerCase();

        System.out.println(keyfor1);
        System.out.println(keyfor2);
        System.out.println(alph);
        System.out.println(alphaLower);
        System.out.println(shiftedAlph1);
        System.out.println(shiftedAlph2);

        for(int i = 0; i < plaintext.length(); i+=2){
            char c = plaintext.charAt(i);
            int charindex = alph.indexOf( Character.toUpperCase(c) );
            if(alph.indexOf( Character.toUpperCase(c)) != -1){
                if(Character.isLowerCase(c)){
                    char encrChar = shiftLower1.charAt(charindex);
                    ans.setCharAt(i, encrChar);
                }
                else{
                    char encrChar = shiftedAlph1.charAt(charindex);
                    ans.setCharAt(i, encrChar);
                }
            }
        }

        for(int i = 1; i < plaintext.length(); i+=2){
            char c = plaintext.charAt(i);
            int charindex = alph.indexOf( Character.toUpperCase(c) );
            if(alph.indexOf( Character.toUpperCase(c)) != -1){
                if(Character.isLowerCase(c)){
                    char encrChar = shiftLower2.charAt(charindex);
                    ans.setCharAt(i, encrChar);
                }
                else{
                    char encrChar = shiftedAlph2.charAt(charindex);
                    ans.setCharAt(i, encrChar);
                }
            }
        }
        System.out.println("plaintext: ");
        System.out.println(plaintext);
        System.out.println("ans: ");
        System.out.println(ans.toString());
        return ans.toString();
    }



    // break
    public String halfOfString(String message, int start) {
        StringBuilder ans = new StringBuilder();
        for(int i = start; i < message.length(); i+=2){
            char currChar = message.charAt(i);
            ans.append(currChar);
        }
        System.out.println(ans);
        return ans.toString();
    }

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
            cipherkey = 4 - maxWrdIndex;
        }
        return cipherkey;
    }


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

    public String caesarBreaker(String ciphertext){
        int cipherkey = getkey(ciphertext);
        System.out.println(cipherkey);
        return caesarcipher(ciphertext, 26 - cipherkey);
    }

    public void breakCaesarCipher(String input){
        // CaesarCipherTwo pr = new CaesarCipherTwo(26 - keyfor1, 26 - keyfor2);

        String string0 = halfOfString(input, 0);
        String string1 = halfOfString(input, 1);

        StringBuilder ans = new StringBuilder();

        String ans0 = caesarBreaker(string0);
        String ans1 = caesarBreaker(string1);

        System.out.println(ans0.length());
        System.out.println(ans1.length());
        System.out.println(ans0);
        System.out.println(ans1);

        for(int i = 0; i < ans0.length(); i++){
            char c0 = ans0.charAt(i);
            ans.append(c0);
            if(i < ans1.length()){
                char c1 = ans1.charAt(i);
                ans.append(c1);
            }
        }
        System.out.println(ans);
        // return ans.toString();
    }
    

    public void test() {

        CaesarCipherTwo pr = new CaesarCipherTwo(17, 3);
        System.out.println("---------------test caesarcipher()---------------");
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        // String input = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        System.out.println("plaintext :");
        System.out.println(input);
        String ans = pr.caesarcipher(input);


        System.out.println("---------------test breakCaesarCipher()---------------");
        String ctext = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        pr.breakCaesarCipher(ctext);
    }
}



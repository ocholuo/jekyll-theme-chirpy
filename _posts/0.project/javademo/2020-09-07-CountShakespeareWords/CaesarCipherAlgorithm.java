

import edu.duke.*;
public class CountShakespeareWords{


	// bring commond wrds to string array
    public String[] getCommon(){
		FileResource resource = new FileResource("data/common.txt");
		String[] common = new String[20];
		int index = 0;
		for(String s : resource.words()){
			common[index] = s;
			index += 1;
		}
		return common;
	}
	

	// check if word exist in string array command
	public int indexOf(String[] list, String word) {
	    for (int k=0; k<list.length; k++) {
	        if (list[k].equals(word)) {
	               return k;
	           }
	       }
	    return -1;
	}
	

	//  count the words in command
	public void countWords(FileResource resource, String[] common, int[] counts){
		for(String word : resource.words()){
			word = word.toLowerCase();
			int index = indexOf(common,word);
			if (index != -1) {
				counts[index] += 1;
			}
		}
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
            // System.out.println(newwrd);
            // System.out.println(wrdlength + " : " + counts[wrdlength]);
        }
        for(int i = 0; i < counts.length; i++){
            System.out.println(i + " : " + counts[i]);
        } 
    }

	
	void countShakespeare(){
		// String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
	    String[] plays = {"romeo.txt"};
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for(int k=0; k < plays.length; k++){
			FileResource resource = new FileResource("data/" + plays[k]);
			countWords(resource,common,counts);
			System.out.println("done with " + plays[k]);
		}
		
		for(int k=0; k < common.length; k++){
			System.out.println(common[k] + "\t" + counts[k]);
		}
	}


	public static void main(String[] args) {
		
		CountShakespeareWords pr = new CountShakespeareWords();
		FileResource resource = new FileResource("data/lotsOfWords.txt");
		int[] counts = new int[50];
		System.out.println("---------------test emphasize()---------------");
		pr.countWordLengths(resource,counts);

	}


}

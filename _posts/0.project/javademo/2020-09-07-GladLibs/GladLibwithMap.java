import edu.duke.*;
import java.util.*;

public class GladLibwithMap {
	private HashMap<String, ArrayList<String>> myMap;
	// private ArrayList<String> adjectiveList;
	// private ArrayList<String> nounList;
	// private ArrayList<String> colorList;
	// private ArrayList<String> countryList;
	// private ArrayList<String> nameList;
	// private ArrayList<String> animalList;
	// private ArrayList<String> timeList;
	// private ArrayList<String> verbList;
	// private ArrayList<String> fruitList;
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	

	// defect, initializeFromSource dataSourceDirectory "data" Directory
	public GladLibwithMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	

	public GladLibwithMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	

	// HashMap<String, ArrayList<String>> myMap;
	// add word to arraylist 
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	

	// add arraylist according to the source path 2
	private void initializeFromSource(String source) {   // dataSourceDirectory / dataSourceURL
		String[] labels = {"adjective", "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
		for(String filename : labels){
			ArrayList<String> list = readIt( source + "/" + filename + ".txt");	
			myMap.put(filename, list);
			for(String wrd : list){
				System.out.println(wrd);
			}
		}
		// myLabelSource file
		// for(String file : myLabelSource.keySet){
		// 	ArrayList<String> list = readIt(myLabelSource.get(file));	
		// 	myMap.put(file, list);
		// }

		// adjectiveList= readIt(source+"/adjective.txt");	
		// nounList = readIt(source+"/noun.txt");
		// colorList = readIt(source+"/color.txt");
		// countryList = readIt(source+"/country.txt");
		// nameList = readIt(source+"/name.txt");		
		// animalList = readIt(source+"/animal.txt");
		// timeList = readIt(source+"/timeframe.txt");	
	}
	
	// get random item from Arraylist
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	

	// add word to arraylist according to the source path 1
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return "" + myRandom.nextInt(50) + 5;
		}
		return randomFrom(myMap.get(label));
		// if (label.equals("country")) {
		// 	return randomFrom(countryList);
		// }
		// if (label.equals("color")){
		// 	return randomFrom(colorList);
		// }
		// if (label.equals("noun")){
		// 	return randomFrom(nounList);
		// }
		// return "**UNKNOWN**";
	}
	



	// add word to arraylist according to the source path 1
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;  // no lable
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		return prefix+sub+suffix;
	}
	

	// 
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	


	// add word to arraylist according to the source path 1
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	

	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}


	// This method returns the total number of words in all the ArrayLists in the HashMap. After printing the GladLib, call this method and print out the total number of words that were possible to pick from.
	public int totalWordsInMap(){
		int total = 0;
		for(String filename : myMap.keySet()){
			int sum = myMap.get(filename).size();      // ArrayList<String>
			// System.out.println(myMap.get(filename));
			// System.out.println(num);
			total += sum;
			System.out.println("ArrayList " + filename + " has " + sum + " words.");
		}
		System.out.println(total);
		return total;
	}

	public static void main(String[] args) {
		GladLibwithMap pr = new GladLibwithMap();
		pr.makeStory();
		int total = pr.totalWordsInMap();
	}

}

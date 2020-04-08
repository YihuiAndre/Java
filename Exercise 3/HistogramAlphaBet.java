import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.canvas.GraphicsContext;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class HistogramAlphaBet {
	//store the total number of alphabet inside the text file
	private int totalAlphabet = 0;
	//create a reference of Map which store the alphabet and its total occurrence
	private Map<Character,Integer> frequent;
	
	//non-default constructor 
	public HistogramAlphaBet(String fileName) {
		//create the HashMap object 
		frequent = new HashMap<Character,Integer>();
		//Using Scanner object to open the file. Then, the try block will automatically close the file after execution
		try(Scanner file = new Scanner(new File(fileName))){
			//read all line from the file
			while(file.hasNext()) {
				//replace all the non-alphabet character to empty string and convert all the alphabet to lower case
				String line = file.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();
				//skip the line if the line is empty line
				if(line.length() != 0) {
					//sum all the number of alphabets for each line
					totalAlphabet += line.length();
					for(int i = 0; i < line.length(); i++) {
						//call increment method to increase values of the key
						increment(frequent, line.charAt(i));
					}
				}
			}
		//catch a exception if the file is not exist and print the exception to the console
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//sort the HashMap by the values in decreasing order
		frequent = sort(frequent);
	}
	//sort the HashMap by value in decreasing order
	private static<K> Map<K,Integer> sort(Map<K, Integer> map){
		//create a LinkedHashMap object
		LinkedHashMap<K, Integer> sortMap = new LinkedHashMap<K, Integer>();
		//create a TreeSet object that sort the insert entries by its value
		Set<Map.Entry<K, Integer>> sortSet = new TreeSet<>(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		//iterate every entries in the HashMap and add into the TreeSet. 
		for(Map.Entry<K,Integer> ele : map.entrySet()) {
			sortSet.add(ele);
		}
		//iterate every entries in TreeSet and put it into LinkedHashMap in order
		for(Map.Entry<K,Integer> ele : sortSet) {
			sortMap.put(ele.getKey(), ele.getValue());
		}
		return sortMap;
	}
	
	
	//increase the value by one with the corresponding key
	public static<K> void increment(Map<K, Integer> map, K key) {
		//if the value of the key is null, initialize it to zero.
		map.putIfAbsent(key,0);
		//replace the old value with new value
		map.put(key, map.get(key) + 1);
	}
	
	//return the HashMap
	public  Map<Character, Integer> getMap(){
		return frequent;
	}
	
	//return the total alphabet
	public int getTotalAlphabet() {
		return totalAlphabet;
	}
	
	//method that will override by subclass
	public void draw(GraphicsContext gc) {
	}
}

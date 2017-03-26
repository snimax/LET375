import java.io.*;
import java.util.*;

public class WordLists {
	private Reader in = null;
	private static TreeMap<String, Number> wordMap = new TreeMap<>();

	public WordLists(String inputFileName) throws IOException{
	    // ... define!

		in = new FileReader(inputFileName);
		String a;
		do {
			a = getWord();

			if(a != null) {
				//System.out.println(a);
				if(wordMap.containsKey(a)){
					wordMap.put(a, wordMap.get(a).intValue() + 1); //
				}
				else{
					wordMap.put(a, 1);
				}
			}
		}while(a !=null);
	}
	
	private boolean isPunctuationChar(char c) {
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}
	
	private String getWord() throws IOException {
		int state = 0;
		int i;
		String word = "";
		while ( true ) {
			i = in.read();
			char c = (char)i;
			switch ( state ) {
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) ) {
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) ) 
					word += Character.toLowerCase( c );
				else {
					word = "";
					state = 0;
				}
			}
		}
	}
	
	private String reverse(String s) {
	    // define!
		char[] tmp = s.toCharArray();
		String reverse = "";
		for(char c:tmp){
			reverse = c + reverse;
		}
        return reverse;
	}
	
	private void computeWordFrequencies() throws IOException{
		Writer writer = new FileWriter("alfaSorted.txt");

		for(Map.Entry<String, Number> entry: wordMap.entrySet()){
			//Map.Entry me = (Map.Entry)i.next();
			writer.write(entry.getKey() + "    " + entry.getValue() + "\r\n");
		}
		writer.close();

	}

	

	private void computeFrequencyMap() throws IOException{
          // define!
		Comparator<Number> descendingComp = (a, b) -> b.intValue() - a.intValue();
		TreeMap<Number, TreeSet<String>> freqMap = new TreeMap<>(descendingComp);
		Object[] keys = wordMap.keySet().toArray();
		Object[] values = wordMap.values().toArray();
		// need to "switch places" of values and keys
		for (int i = 0; i < values.length; i++) {
			TreeSet<String> tmp = new TreeSet<>();

			if(freqMap.containsKey(values[i])){
				tmp = freqMap.get(values[i]);
				tmp.add(keys[i].toString());
				freqMap.put((int)values[i], tmp);
			}
			else{
				tmp.add(keys[i].toString());
				freqMap.put((int)values[i], tmp);
			}
		}

		Writer writer = new FileWriter("frequencySorted.txt");

		for (Map.Entry<Number, TreeSet<String>> entry:freqMap.entrySet()) {
			writer.write(entry.getKey().toString() + ":\r\n");
			for (String s: entry.getValue()) {
				writer.write("\t" + s + "\r\n");
			}
		}

		writer.close();

	}
	

	private void computeBackwardsOrder() throws IOException{
		Comparator<String> reverseComp = (a, b) -> reverse(a).compareTo(reverse(b));
		TreeSet<String> reverse = new TreeSet<String>(reverseComp);

		for(Map.Entry<String, Number> entry: wordMap.entrySet()){
			reverse.add(entry.getKey());
		}

		Writer writer = new FileWriter("backwardsSorted.txt");

		int maxLength = 20;

		for (String s:reverse) {
			String spaces = "";
			for(int j = maxLength - s.length(); j > 0; j--){
				spaces += " ";
			}
			writer.write( spaces + s + "\r\n");
		}

		writer.close();
	}

	public static void main(String[] args) throws IOException {
		WordLists wl = new WordLists(args[0]);  // args[0] contains the input file name
		wl.computeWordFrequencies();
		wl.computeFrequencyMap();
		wl.computeBackwardsOrder();

		System.out.println("Finished!");
	}

}























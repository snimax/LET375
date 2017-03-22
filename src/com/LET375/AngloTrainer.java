package com.LET375;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Arrays;

public class AngloTrainer {
	// ...

    private TreeSet<String> wordTree;
    private String letters;

    public AngloTrainer(String dictionaryFile) throws IOException {
	    loadDictionary(dictionaryFile);
	    //dumpDict();

	    Random rand = new Random();
	    rand.setSeed(System.currentTimeMillis());
	    int length = rand.nextInt() % 5 + 7;// ensures atleast 2 letters, and modulus returns negative numbers apparently

	    System.out.println(length);
        letters = randomLetters(length);
        letters = sort(letters); // might aswell save the sorted letters
        System.out.println(letters);
    }

	// use this to verify loadDictionary
	private void dumpDict() {
	    // Print out the dictionary at the screen.
        for (String word:wordTree) {
            System.out.println(word);
        }
    }

	private void loadDictionary( String fileName ) {
	    // Read the dictionary into a suitable container.
	    // The file is a simple text file. One word per line.
        try {
            List<String> temp = Files.readAllLines(Paths.get(fileName));
            wordTree = new TreeSet<>(temp);
        }
        catch (IOException e){
            e.printStackTrace();
        }
	}

	private String randomLetters( int length ) {
	    // this makes vovels a little more likely
	    String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";
	    StringBuffer buf = new StringBuffer(length);

	    Random randomGenerator = new Random();
	    randomGenerator.setSeed(System.currentTimeMillis()); //setting seed of current time for better randomization

	    for ( int i = 0; i < length; i++ )
		    buf.append( letters.charAt(randomGenerator.nextInt(letters.length())));
	
	    return buf.toString();
	}
	
	
	/* Def. includes	
	 * Let #(x,s) = the number of occurrences of the character x in the string s.
	 * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
	 * 
	 * A necessary precondition for includes is that both strings are sorted
	 * in ascending order.
	 */
	private boolean includes( String a, String b ) {
		if ( b == null || b.length() == 0 )
			return true;
		else if ( a == null || a.length() == 0 )
			return false;
		
		//precondition: a.length() > 0 && b.length() > 0
		int i = 0, j = 0;
		while ( j < b.length() ) {
			if (i >= a.length() || b.charAt(j) < a.charAt(i))
				return false;
			else if (b.charAt(j) == a.charAt(i)) {
				i++; j++;
			} else if (b.charAt(j) > a.charAt(i))
				i++;
		}
		//postcondition: j == b.length()
		return true;
	}
	
     // This is just for demonstration purposes.
	private void testIncludes() { 
		//                                            expected value
		System.out.println(includes("abc",""));		//t
		System.out.println(includes("","abc"));		//f
		System.out.println(includes("abc","abc"));	//t
		System.out.println(includes("abc","bcd"));	//f
		System.out.println(includes("abc","a"));	//t
		System.out.println(includes("abc","b"));	//t
		System.out.println(includes("abc","c"));	//t
		System.out.println(includes("abc","ab"));	//t
		System.out.println(includes("abc","bc"));	//t
		System.out.println(includes("abc","ac"));	//t
		System.out.println(includes("abc","abcd"));	//f
		System.out.println(includes("abc","abd"));	//f
		System.out.println(includes("abc","d"));	//f
		System.out.println(includes("",""));		//t
		System.out.println(includes("abc","ca"));	//f
		System.out.println(includes("abc","bac"));	//f
		System.out.println(includes("abc","abbc"));	//f
		System.out.println(includes("abbc","abc"));	//t
		System.out.println(includes(null,null));    //t
		System.out.println(includes(null,""));	    //t
		System.out.println(includes(null,"abc"));	//f
		System.out.println(includes("",null));		//t
		System.out.println(includes("abc",null));   //t
	}

    public static void main(String[] args) {
        // ... define!
        Scanner in = new Scanner(System.in);
        while(true) {
            String input = in.next();
            System.out.println(input);
            if(input.equals("break")){
                System.out.println("breaking");
                break;
            }
        }
    }

    private String sort(String s){
	    char temp[] = new char[s.length()];

	    for(int i = 0; i < s.length(); i++){
	        temp[i] = s.charAt(i);
	        //System.out.println(temp[i]);
        }

        Arrays.sort(temp);
        return new String(temp);
    }
}













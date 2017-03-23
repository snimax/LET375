package com.LET375;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{

        System.out.println("Type in 'anglotrainer' or 'wordlists' to start the corresponding program.");

        Scanner in = new Scanner(System.in);
        String input = in.next();
        while(true) {
            if(input.equals("anglotrainer")) {
                AngloTrainer test = new AngloTrainer("wordsEn.txt");
                break;
            }
            else if(input.equals("wordlists")){
                WordLists wl = new WordLists("provtext.txt");
                wl.main(args);
                break;
            }
        }
    }
}

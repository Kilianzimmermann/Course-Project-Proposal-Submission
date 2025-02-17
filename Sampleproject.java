/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/* sample code for our project
 * Things that still need work: Check for repeated inputed letters, make sure that when the user has already inputed a letter, he can't input it again
 * Make sure the user is able to see the letters he has already inputed on the screen, also make sure if the user inputs a repeated letter, not to take a life away
 */  
package com.mycompany.sampleproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
/**
 *
 * @author kzimm
 */
public class Sampleproject {

    public static void main(String[] args) {
        String word = getRandomWordFromWeb();
        if (word == null || word.isEmpty()) {
            System.out.println("Error fetching word.");
            return;
        }

        StringBuilder hiddenWord = new StringBuilder("_".repeat(word.length()));
        int attempts = 6;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word.");
        System.out.println("The word is: " + hiddenWord);
        
        while (attempts > 0 && hiddenWord.toString().contains("_")) {
            System.out.println("Attempts left: " + attempts);
            System.out.print("Enter a letter: ");
            String guess = scanner.nextLine().toLowerCase();

            // Validate user input
            if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    hiddenWord.setCharAt(i, guess.charAt(0));
                    found = true;
                }
            }

            if (!found) {
                attempts--;
                System.out.println("Incorrect guess.");
            } else {
                System.out.println("Good guess!");
            }

            System.out.println("The word is: " + hiddenWord);
        }

        if (hiddenWord.toString().equals(word)) {
            System.out.println("Congratulations, you guessed the word!");
        } else {
            System.out.println("Game over! The word was: " + word);
        }

        scanner.close();
    }

    public static String getRandomWordFromWeb() {
        String word = "";
        try {
            URL url = new URL("https://randomwordgenerator.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("random word")) {
                    // Extract the word here, for now we assume the word is directly available
                    word = "hangman"; // Placeholder word, for demonstration.
                    break;
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }
}

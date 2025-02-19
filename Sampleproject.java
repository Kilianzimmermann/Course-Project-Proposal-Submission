package com.mycompany.sampleproject;
// added the support for the user guessing repeated letters, and made the length of the word that the user has to guess clearer, adding spaces between every
// underscore, in order for the user to tell how many more letters he needs to guess
// WHAT IS STILL NEEDED:
// All of the javafx part of the app (drawing the hangman stage, a display that shows the wrong letters and drawing the hangman body parts after each wrong guess,
// displaying the correct letters in the correct spaces and showing the remaining amount of lives/attempts the user has left)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Sampleproject {

    public static void main(String[] args) {
        String word = getRandomWordFromWeb();
        if (word == null || word.isEmpty()) {
            System.out.println("Error fetching word.");
            return;
        }

        // Create the hidden word with underscores
        StringBuilder hiddenWord = new StringBuilder("_".repeat(word.length()));
        int attempts = 6;
        Scanner scanner = new Scanner(System.in);
        
        // Set to store guessed letters
        Set<Character> guessedLetters = new HashSet<>();
        
        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word.");
        
        // Print the hidden word with spaces between underscores
        System.out.println("The word is: " + addSpacesToHiddenWord(hiddenWord.toString()));
        
        while (attempts > 0 && hiddenWord.toString().contains("_")) {
            System.out.println("Attempts left: " + attempts);
            System.out.print("Enter a letter: ");
            String guess = scanner.nextLine().toUpperCase();

            // Validate user input
            if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            // Check if the letter has already been guessed
            if (hasAlreadyGuessed(guessedLetters, guess.charAt(0))) {
                System.out.println("You've already guessed that letter. Try a different one.");
                continue;
            }

            // Add the guessed letter to the set
            guessedLetters.add(guess.charAt(0));

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

            // Print the hidden word with spaces between underscores
            System.out.println("The word is: " + addSpacesToHiddenWord(hiddenWord.toString()));
        }

        if (hiddenWord.toString().equals(word)) {
            System.out.println("Congratulations, you guessed the word!");
        } else {
            System.out.println("Game over! The word was: " + word);
        }

        scanner.close();
    }

    // Helper method to add spaces between each character in the hidden word
    private static String addSpacesToHiddenWord(String word) {
        StringBuilder spacedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            spacedWord.append(word.charAt(i)).append(" ");
        }
        return spacedWord.toString().trim();
    }

    // Method to check if a letter has already been guessed
    private static boolean hasAlreadyGuessed(Set<Character> guessedLetters, char letter) {
        return guessedLetters.contains(letter);
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
                    word = "HANGMAN"; // Placeholder word, for demonstration.
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

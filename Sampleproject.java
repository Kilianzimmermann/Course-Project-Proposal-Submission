package com.mycompany.sampleproject;
// what has changed
// changed the getWordfromWeb into a wordlist, which usses an array to store multiple different words that are used for the hidden Word.
// this is in order to make the compiling of the project easier
// the guessing and word choosing still works as expected
// the life system and win and lose features still work as expected

// what is still needed
// implementing this code into a javafx app that correctly uses the code to display a hangman game.
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Sampleproject {

    public static void main(String[] args) {
        // Get a random word for the user to guess
        String word = getRandomWord();
        if (word == null || word.isEmpty()) {
            System.out.println("Error fetching word.");
            return;
        }

        // Create the hidden word with underscores
        StringBuilder hiddenWord = new StringBuilder("_".repeat(word.length()));
        int attempts = 6;  // Number of attempts (lives)
        Scanner scanner = new Scanner(System.in);

        // Set to store guessed letters
        Set<Character> guessedLetters = new HashSet<>();

        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word.");

        // Print the initial hidden word with spaces between underscores
        System.out.println("The word is: " + addSpacesToHiddenWord(hiddenWord.toString()));

        // Game loop: Keep asking for guesses until the user wins or loses
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
            // Loop through the word and check for all occurrences of the guessed letter
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    hiddenWord.setCharAt(i, guess.charAt(0));  // Update all occurrences of the guessed letter
                    found = true;
                }
            }

            if (!found) {
                attempts--;  // Decrease attempts if the letter was not found
                System.out.println("Incorrect guess.");
            } else {
                System.out.println("Good guess!");
            }

            // Print the updated hidden word with spaces between underscores
            System.out.println("The word is: " + addSpacesToHiddenWord(hiddenWord.toString()));
        }

        // Check if the player has guessed all letters correctly
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

    // Method to return a random word from a predefined list
    public static String getRandomWord() {
        // List of random words for the Hangman game
        String[] wordList = {
            "APPLE", "BANANA", "ORANGE", "GRAPE", "PINEAPPLE", "MANGO", "WATERMELON",
            "STRAWBERRY", "BLUEBERRY", "RASPBERRY", "CHERRY", "PEACH", "PLUM", "PEAR",
            "LEMON", "LIME", "KIWI", "PAPAYA", "COCONUT", "AVOCADO", "CARROT", "POTATO",
            "CUCUMBER", "TOMATO", "PUMPKIN", "BROCCOLI", "SPINACH", "LETTUCE", "ZUCCHINI",
            "EGGPLANT", "BELLPEPPER", "ASPARAGUS", "ARTICHOKE", "RADISH", "BEETROOT"
        };

        // Select a random word from the list
        Random rand = new Random();
        return wordList[rand.nextInt(wordList.length)];
    }
}

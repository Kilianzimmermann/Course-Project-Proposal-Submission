package com.mycompany.sampleproject;

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
            "APPLE", "BANANA", "ORANGE", "GRAPE", "MANGO", "PEAR", "PEACH", "PLUM", "CHERRY", "KIWI",
            "PINEAPPLE", "WATERMELON", "STRAWBERRY", "BLUEBERRY", "RASPBERRY", "BLACKBERRY", "TANGERINE", 
            "LEMON", "LIME", "AVOCADO", "COCONUT", "ALMOND", "CASHEW", "PEANUT", "WALNUT", "HAZELNUT", 
            "MACADAMIA", "PISTACHIO", "SHEA", "CARAMEL", "CHOCOLATE", "VANILLA", "CINNAMON", "MINT", "GINGER", 
            "SUGAR", "HONEY", "MAPLE", "FUDGE", "MOCHA", "LATTE", "ESPRESSO", "CAPPUCCINO", "AMERICANO", 
            "MACCHIATO", "COFFEE", "TEA", "HOTCOCOA", "CARAMEL", "MILK", "CREAM", "BUTTER", "CHEESE", "YOGURT", 
            "EGGS", "BREAD", "CEREAL", "OATS", "POPCORN", "CRACKERS", "NUTS", "SOUP", "SALAD", "SANDWICH", 
            "BURGER", "PIZZA", "PASTA", "SPAGHETTI", "NOODLES", "RICE", "CHICKEN", "TURKEY", "BEEF", "PORK", 
            "LAMB", "FISH", "TUNA", "SALMON", "COD", "TROUT", "SHRIMP", "CRAB", "LOBSTER", "MUSSELS", "OYSTERS", 
            "CAVIAR", "STEAK", "PORKCHOP", "FRIEDRICE", "POTATOES", "CARROTS", "PEAS", "BROCCOLI", "CABBAGE", 
            "CORN", "TOMATO", "LETTUCE", "CUCUMBER", "PEPPER", "ONION", "GARLIC", "GINGER", "MUSHROOM", 
            "SPINACH", "KALE", "BRUSSELS", "BEANS", "SQUASH", "ZUCCHINI", "ARTICHOKE", "LEEK", "FENNEL", 
            "PARSNIP", "TURNIP", "PUMPKIN", "YAM", "POTATO", "RADISH", "AVOCADO", "FETA", "PANEER", "MOZZARELLA", 
            "BRIE", "CHEDDAR", "PARMESAN", "SWISS", "CAMEMBERT", "GOUDA", "AMERICAN", "JACK", "PEPPERJACK", 
            "COLBY", "PROVOLONE", "COTTAGE", "RICOTTA", "CHEESECAKE", "PIE", "CAKE", "BROWNIE", "COOKIE", 
            "MUFFIN", "CUPCAKE", "DOUGHNUT", "TART", "PUDDING", "SORBET", "ICECREAM", "CUPCAKES", "CANDY", 
            "CARAMEL", "COTTONCANDY", "MARSHMALLOW", "GUMDROP", "JELLYBEAN", "MINTCHOCOLATE", "LOLLIPOP", 
            "FUDGE", "CHOCOLATE", "MILKCHOCOLATE", "DARKCHOCOLATE", "WHITECHOCOLATE", "TRUFFLES", "CARAMEL", 
            "CHOCOLATECAKE", "TIRAMISU", "PAVLOVA", "MOUSSE", "MACAROONS", "BISCUITS", "KNOCKKNOCK", "BILL",
            "COFFEE", "TEA", "LATTE", "CARAMEL", "COCONUT", "TOAST", "CUP", "GLASS", "MUG", "BOTTLE", 
            "TUMBLER", "CUPBOARD", "FLOOR", "WALL", "ROOF", "DOOR", "WINDOW", "LIGHT", "FLOODLIGHT", "LAMPS", 
            "CANDLES", "BULB", "ELECTRICITY", "WIRING", "BATTERY", "GENERATOR", "CIRCUIT", "WIRES", "POWER", 
            "ELECTRIC", "LAMP", "FLOOR", "CABINET", "SHELF", "DRAWER", "CHAIR", "SOFA", "BED", "MATTRESS", 
            "BLANKET", "CUSHION", "PILLOW", "CARPET", "RUG", "LAMP", "LANTERN", "PAINT", "PAPER", "PEN", 
            "PENCIL", "MARKER", "ERASER", "SHARPENER", "FOLDER", "BINDER", "NOTEBOOK", "STAPLER", "RULER", 
            "TAPE", "SCISSORS", "GLOVES", "MASK", "SHOES", "JACKET", "COAT", "HAT", "GLOVES", "SCARF", 
            "Mittens", "JEANS", "TROUSERS", "SKIRTS", "SHIRTS", "PANTS", "HOODIE", "SWEATER", "T-SHIRT", 
            "BUTTON", "BLOUSE", "DRESS", "SUIT", "TIE", "SHOES", "BOOTS", "SNEAKERS", "LOAFERS", "SLIPPERS"        };

        // Select a random word from the list
        Random rand = new Random();
        return wordList[rand.nextInt(wordList.length)];
    }
}




//This is the updated code, but I didn't wnat to delete previous just in case
// The main changes are new labels and creation of window panels to display the word and such


package com.mycompany.sample_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class App extends Application {
    private String word;
    private StringBuilder hiddenWord;
    private int attempts = 6;
    private Set<Character> guessedLetters = new HashSet<>();
    private Label wordLabel, attemptsLabel, hangmanLabel;
    private TextField inputField;
    private Button guessButton;

    @Override
    public void start(Stage primaryStage) {
        word = getRandomWord();
        hiddenWord = new StringBuilder("_".repeat(word.length()));

        wordLabel = new Label(addSpacesToHiddenWord(hiddenWord.toString()));
        attemptsLabel = new Label("Attempts left: " + attempts);
        hangmanLabel = new Label(getHangmanDrawing(6));
        inputField = new TextField();
        inputField.setMaxWidth(50);
        guessButton = new Button("Guess");
        guessButton.setOnAction(e -> handleGuess());

        VBox layout = new VBox(10, wordLabel, attemptsLabel, hangmanLabel, inputField, guessButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        String guess = inputField.getText().toUpperCase();
        inputField.clear();

        if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
            showAlert("Invalid Input", "Please enter a single letter.");
            return;
        }

        char guessedChar = guess.charAt(0);
        if (guessedLetters.contains(guessedChar)) {
            showAlert("Duplicate Guess", "You've already guessed that letter.");
            return;
        }

        guessedLetters.add(guessedChar);
        boolean found = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guessedChar) {
                hiddenWord.setCharAt(i, guessedChar);
                found = true;
            }
        }

        if (!found) {
            attempts--;
            hangmanLabel.setText(getHangmanDrawing(attempts));
        }

        wordLabel.setText(addSpacesToHiddenWord(hiddenWord.toString()));
        attemptsLabel.setText("Attempts left: " + attempts);

        if (!hiddenWord.toString().contains("_")) {
            showAlert("Congratulations!", "You guessed the word: " + word);
            System.exit(0);
        } else if (attempts == 0) {
            showAlert("Game Over", "The word was: " + word);
            System.exit(0);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static String addSpacesToHiddenWord(String word) {
        StringBuilder spacedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            spacedWord.append(word.charAt(i)).append(" ");
        }
        return spacedWord.toString().trim();
    }

    private static String getRandomWord() {
        String[] wordList = {  "APPLE", "BANANA", "ORANGE", "GRAPE", "MANGO", "PEAR", "PEACH", "PLUM", "CHERRY", "KIWI",
            "PINEAPPLE", "WATERMELON", "STRAWBERRY", "BLUEBERRY", "RASPBERRY", "BLACKBERRY", "TANGERINE", 
            "LEMON", "LIME", "AVOCADO", "COCONUT", "ALMOND", "CASHEW", "PEANUT", "WALNUT", "HAZELNUT", 
            "MACADAMIA", "PISTACHIO", "SHEA", "CARAMEL", "CHOCOLATE", "VANILLA", "CINNAMON", "MINT", "GINGER", 
            "SUGAR", "HONEY", "MAPLE", "FUDGE", "MOCHA", "LATTE", "ESPRESSO", "CAPPUCCINO", "AMERICANO", 
            "MACCHIATO", "COFFEE", "TEA", "HOTCOCOA", "CARAMEL", "MILK", "CREAM", "BUTTER", "CHEESE", "YOGURT", 
            "EGGS", "BREAD", "CEREAL", "OATS", "POPCORN", "CRACKERS", "NUTS", "SOUP", "SALAD", "SANDWICH", 
            "BURGER", "PIZZA", "PASTA", "SPAGHETTI", "NOODLES", "RICE", "CHICKEN", "TURKEY", "BEEF", "PORK", 
            "LAMB", "FISH", "TUNA", "SALMON", "COD", "TROUT", "SHRIMP", "CRAB", "LOBSTER", "MUSSELS", "OYSTERS", 
            "CAVIAR", "STEAK", "PORKCHOP", "FRIEDRICE", "POTATOES", "CARROTS", "PEAS", "BROCCOLI", "CABBAGE", 
            "CORN", "TOMATO", "LETTUCE", "CUCUMBER", "PEPPER", "ONION", "GARLIC", "GINGER", "MUSHROOM", 
            "SPINACH", "KALE", "BRUSSELS", "BEANS", "SQUASH", "ZUCCHINI", "ARTICHOKE", "LEEK", "FENNEL", 
            "PARSNIP", "TURNIP", "PUMPKIN", "YAM", "POTATO", "RADISH", "AVOCADO", "FETA", "PANEER", "MOZZARELLA", 
            "BRIE", "CHEDDAR", "PARMESAN", "SWISS", "CAMEMBERT", "GOUDA", "AMERICAN", "JACK", "PEPPERJACK", 
            "COLBY", "PROVOLONE", "COTTAGE", "RICOTTA", "CHEESECAKE", "PIE", "CAKE", "BROWNIE", "COOKIE", 
            "MUFFIN", "CUPCAKE", "DOUGHNUT", "TART", "PUDDING", "SORBET", "ICECREAM", "CUPCAKES", "CANDY", 
            "CARAMEL", "COTTONCANDY", "MARSHMALLOW", "GUMDROP", "JELLYBEAN", "MINTCHOCOLATE", "LOLLIPOP", 
            "FUDGE", "CHOCOLATE", "MILKCHOCOLATE", "DARKCHOCOLATE", "WHITECHOCOLATE", "TRUFFLES", "CARAMEL", 
            "CHOCOLATECAKE", "TIRAMISU", "PAVLOVA", "MOUSSE", "MACAROONS", "BISCUITS", "KNOCKKNOCK", "BILL",
            "COFFEE", "TEA", "LATTE", "CARAMEL", "COCONUT", "TOAST", "CUP", "GLASS", "MUG", "BOTTLE", 
            "TUMBLER", "CUPBOARD", "FLOOR", "WALL", "ROOF", "DOOR", "WINDOW", "LIGHT", "FLOODLIGHT", "LAMPS", 
            "CANDLES", "BULB", "ELECTRICITY", "WIRING", "BATTERY", "GENERATOR", "CIRCUIT", "WIRES", "POWER", 
            "ELECTRIC", "LAMP", "FLOOR", "CABINET", "SHELF", "DRAWER", "CHAIR", "SOFA", "BED", "MATTRESS", 
            "BLANKET", "CUSHION", "PILLOW", "CARPET", "RUG", "LAMP", "LANTERN", "PAINT", "PAPER", "PEN", 
            "PENCIL", "MARKER", "ERASER", "SHARPENER", "FOLDER", "BINDER", "NOTEBOOK", "STAPLER", "RULER", 
            "TAPE", "SCISSORS", "GLOVES", "MASK", "SHOES", "JACKET", "COAT", "HAT", "GLOVES", "SCARF", 
            "Mittens", "JEANS", "TROUSERS", "SKIRTS", "SHIRTS", "PANTS", "HOODIE", "SWEATER", "T-SHIRT", 
            "BUTTON", "BLOUSE", "DRESS", "SUIT", "TIE", "SHOES", "BOOTS", "SNEAKERS", "LOAFERS", "SLIPPERS"  };
        return wordList[new Random().nextInt(wordList.length)];
    }

   private String getHangmanDrawing(int attemptsLeft) {
    String[] hangmanStages = {
        
        
         " _______\n" +
        "|/      |\n" +
        "|\n" +
        "|\n" +
        "|\n" +
        "|\n" +
        "|\n" +
        "|________",
        
          " _______\n" +
        "|/      |\n" +
        "|    (X_X)\n" +
        "|\n" +
        "|\n" +
        "|\n" +
        "|\n" +
        "|________",

       " _______\n" +
        "|/      |\n" +
        "|    (X_X)\n" +
        "|      \\|\n" +
        "|       |\n" +
        "|\n" +
        "|\n" +
        "|________",

        " _______\n" +
        "|/      |\n" +
        "|    (X_X)\n" +
        "|      \\|/\n" +
        "|       |\n" +
        "|\n" +
        "|\n" +
        "|________",

      

       

       " _______\n" +
        "|/      |\n" +
        "|    (X_X)\n" +
        "|      \\|/\n" +
        "|       |\n" +
        "|     / \n" +
        "|\n" +
        "|________",

         " _______\n" +
        "|/      |\n" +
        "|    (X_X)\n" +
        "|      \\|/\n" +
        "|       |\n" +
        "|      / \\\n" +
        "|\n" +
        "|________",
         
           " _______\n" +
        "|/      |\n" +
        "|    (X_X)\n" +
        "|      \\|/\n" +
        "|       |\n" +
        "|      / \\\n" +
        "|\n" +
        "|________"
    };
    return hangmanStages[6 - attemptsLeft];
}

    public static void main(String[] args) {
        launch(args);
    }
}

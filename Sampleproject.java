package com.mycompany.sample_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.*;

public class App extends Application {
    private String word;
    private StringBuilder hiddenWord;
    private int attempts = 6;
    private Set<Character> guessedLetters = new HashSet<>();

    private Label wordLabel, attemptsLabel, guessedLettersLabel;
    private TextField inputField;
    private Button guessButton, quitButton;
    private Pane hangmanPane;

    private final String[] easyWords = {"CAT", "DOG", "CAR", "SUN", "HAT"};
    private final String[] mediumWords = {"BANANA", "GUITAR", "PYTHON", "FLOWER", "MARKET", "STICKS"};
    private final String[] hardWords = {"PNEUMONIA", "PSYCHOLOGY", "ASTRONOMY", "EXTRATERRESTRIAL", "SYNTHESIZER", "BLACKJACK"};

    private String selectedDifficulty = "EASY";

    @Override
    public void start(Stage primaryStage) {
        showDifficultyDialog(primaryStage);
    }

    private void showDifficultyDialog(Stage primaryStage) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("EASY", "MEDIUM", "HARD");
        dialog.setTitle("Select Difficulty");
        dialog.setHeaderText("Choose a difficulty level");
        dialog.setContentText("Difficulty:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            selectedDifficulty = result.get();
            initializeGame(primaryStage);
        } else {
            System.exit(0);
        }
    }

    private void initializeGame(Stage primaryStage) {
        word = getRandomWord(selectedDifficulty);
        hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.append("_");
        }

        wordLabel = new Label(addSpacesToHiddenWord(hiddenWord.toString()));
        attemptsLabel = new Label("Attempts left: " + attempts);
        guessedLettersLabel = new Label("Guessed letters: ");

        wordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
        attemptsLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: #ffffff;");
        guessedLettersLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: #ffffff;");

        inputField = new TextField();
        inputField.setMaxWidth(100);
        inputField.setOnAction(e -> handleGuess());

        guessButton = new Button("Guess");
        guessButton.setOnAction(e -> handleGuess());

        quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));

        hangmanPane = new Pane();
        hangmanPane.setPrefSize(200, 300);
        hangmanPane.setStyle("-fx-background-color: transparent;");
        drawGallows();

        HBox hangmanBox = new HBox(hangmanPane);
        hangmanBox.setStyle("-fx-alignment: center;");

        HBox buttonRow = new HBox(10, guessButton, quitButton);
        buttonRow.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(15, wordLabel, attemptsLabel, hangmanBox, guessedLettersLabel, inputField, buttonRow);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center; -fx-background-color: #6E4B34;");

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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
        guessedLettersLabel.setText("Guessed letters: " + guessedLetters.toString());

        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guessedChar) {
                hiddenWord.setCharAt(i, guessedChar);
                found = true;
            }
        }

        if (!found) {
            attempts--;
            drawHangmanPart(attempts);
        }

        wordLabel.setText(addSpacesToHiddenWord(hiddenWord.toString()));
        attemptsLabel.setText("Attempts left: " + attempts);

        if (hiddenWord.indexOf("_") == -1) {
            showAlert("Congratulations!", "You guessed the word: " + word);
            resetGame();
        } else if (attempts == 0) {
            showAlert("Game Over", "The word was: " + word);
            resetGame();
        }
    }

    private void drawGallows() {
        hangmanPane.getChildren().clear();
        Line base = new Line(20, 280, 180, 280);
        Line pole = new Line(50, 280, 50, 50);
        Line beam = new Line(50, 50, 130, 50);
        Line rope = new Line(130, 50, 130, 80);

        for (Line line : new Line[]{base, pole, beam, rope}) {
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(5);
        }

        hangmanPane.getChildren().addAll(base, pole, beam, rope);
    }

    private void drawHangmanPart(int attemptsLeft) {
        Shape part = null;

        if (attemptsLeft == 5) {
            part = new Circle(130, 100, 20);
        } else if (attemptsLeft == 4) {
            part = new Line(130, 120, 130, 180);
        } else if (attemptsLeft == 3) {
            part = new Line(130, 140, 100, 160);
        } else if (attemptsLeft == 2) {
            part = new Line(130, 140, 160, 160);
        } else if (attemptsLeft == 1) {
            part = new Line(130, 180, 100, 220);
        } else if (attemptsLeft == 0) {
            part = new Line(130, 180, 160, 220);
        }

        if (part != null) {
            part.setStroke(Color.WHITE);
            if (part instanceof Shape) {
                part.setStrokeWidth(3);
            }
            hangmanPane.getChildren().add(part);
        }
    }

    private void resetGame() {
        word = getRandomWord(selectedDifficulty);
        hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.append("_");
        }

        attempts = 6;
        guessedLetters.clear();

        wordLabel.setText(addSpacesToHiddenWord(hiddenWord.toString()));
        attemptsLabel.setText("Attempts left: " + attempts);
        guessedLettersLabel.setText("Guessed letters: ");
        inputField.clear();

        drawGallows();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String addSpacesToHiddenWord(String word) {
        StringBuilder spacedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            spacedWord.append(word.charAt(i));
            if (i != word.length() - 1) {
                spacedWord.append(" ");
            }
        }
        return spacedWord.toString();
    }

    private String getRandomWord(String difficulty) {
        Random rand = new Random();
        if ("EASY".equalsIgnoreCase(difficulty)) {
            return easyWords[rand.nextInt(easyWords.length)];
        } else if ("MEDIUM".equalsIgnoreCase(difficulty)) {
            return mediumWords[rand.nextInt(mediumWords.length)];
        } else {
            return hardWords[rand.nextInt(hardWords.length)];
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


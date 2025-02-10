# Course-Project-Proposal-Submission
Building a simple hangman game for CSI2300-11625.202510.
Simple hangman game created by Kilian Zimmermann Zambrano and Cosmin Socaci.
The hangman game will be created by drawing the initial game setting, and adding parts of the body for each incorrect guess by the User. We are hoping to have a new randomly generated word each time the user plays. This list will be drawn from the website randomwordgenerator.com, which produces different random words that can be filtered and customized for each length. We want to build this app in order to get familiar with different aspects of app design, like drawing elements and getting variables from other websites and projects. The user will input their guesses through a text field that will appear on the newly created application window, which also includes the hangman setting at the top, the length of the word with the letters appearing after each correct guess, a list of letters the player has already guessed and the strike system that acts as a form of how many lives the player has remaining. 

We are planning on dividing the work equally, trying to both work on the javafx aspect of the app, and the actual coding of the hangman application. For the game creation, we will require at least 4 different classes, the only class that needs to be specifically private is the word generation class, in order to keep the word hidden from the client/user. The 4 main grous would be titled, WordGenerator, LifeSystem, DrawingEffects, and LetterGuessing. We would preferably create a custom hangman figure, since it would be easier to update rather than pulling an image from the internet. The hardest part of the game to code, will probably be the letterGuessing class, which would be the most complicated. The hangmanStages class will be kind of annoying to create, since it will require tons of scenes and groups, but will be the easiest to create.


Word Generator: Class designed to generate a random word and then set that word as the one to guess. Will need to determine how to narrow down or generate words(will most liekly just choose from a list of a large amount of words at random).

LifeSystem: Will monitor incorrrect amount of guesses and ends game when it is equal to 0. Boolean used to determine whether true or false after every try. Resetlives will give wither give all lives back or ask for specified amount.

hangmanStages: Shows hangman based on progress. Currently placeholded by using a string to represent the hangman, will use javafx in later submission when meant to actually work. currentStage will keep track of current iteration of the hangman. 

letterGuessing: Class that allows user to guess characters to guess(will add complete word guess later) and updates status of correct and incorrect guesses. maskedWord will display guessed letters with underscores in place of unguessed letters. 

![Screenshot 2025-02-10 101049](https://github.com/user-attachments/assets/57fa599a-0d0c-4028-9d11-4a4ca27ef156)

# Course-Project-Proposal-Submission
Building a simple hangman game for CSI2300-11625.202510.
Simple hangman game created by Kilian Zimmermann Zambrano and Cosmin Socaci.
The hangman game will be created by drawing the initial game setting, and adding parts of the body for each incorrect guess by the User. The app uses 3 different array Lists that have a different "difficulty" of words. The game pulls a random word based on the choice the User makes from the difficulty dropdown box. We want to build this app in order to get familiar with different aspects of app design, like drawing elements and getting variables from other websites and projects. The user will input their guesses through a text field that will appear on the newly created application window, which also includes the hangman setting at the top, the length of the word with the letters appearing after each correct guess, a list of letters the player has already guessed and the strike system that acts as a form of how many lives the player has remaining. 

We are planning on dividing the work equally, trying to both work on the javafx aspect of the app, and the actual coding of the hangman application. For the game creation, we will require at least 4 different classes, the only class that needs to be specifically private is the word generation class, in order to keep the word hidden from the client/user. The 4 main grous would be titled, WordGenerator, LifeSystem, DrawingEffects, and LetterGuessing. We would preferably create a custom hangman figure, since it would be easier to update rather than pulling an image from the internet. The hardest part of the game to code, will probably be the letterGuessing class, which would be the most complicated. The hangmanStages class will be kind of annoying to create, since it will require tons of scenes, but will be the easiest to create.

DIRECTIONS:
How to use the app
- Copy the code into NetbeansIDE, as a maven-based project
- Make sure to change the name of the project, or the package, in order to ensure the programm runs as expected
- Build and run the project, not just the app file
- Choose a difficulty for the game, which impacts the length of the hidden words
- Play the game as much as you want, using the quit button to exit out of the app, and the difficulty dropdown to choose the difficulty of each round

![Screenshot 2025-04-21 120155](https://github.com/user-attachments/assets/1ba7d10a-f9b4-497e-b19e-7cbd106f6c0a)

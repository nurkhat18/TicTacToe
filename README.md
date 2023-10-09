# Tic-Tac-Toe Java Project
!(images/button.png)

## Introduction
Welcome to the Tic-Tac-Toe Java project! This versatile project allows you to play the classic Tic-Tac-Toe game in multiple ways: in the console, using a JavaFX graphical user interface (GUI), and even against AI bots of varying difficulty levels. Whether you prefer a simple text-based interface, clickable buttons, or visual representations of X and O, this project has it all.

## Project Components
This project is divided into the following components:

### 1. Console-Based Gameplay
- Play the game in a text-based console interface.
- Suitable for players who prefer a traditional experience.

### 2. JavaFX GUI Views
The JavaFX GUI offers three different views for playing Tic-Tac-Toe:

#### a. Text View
- A GUI interface that closely resembles the console-based gameplay.
- Players are prompted to input the row and column to make their moves.
- Suitable for players who prefer a familiar text-based interface with clickable buttons.

#### b. Button View
- A more graphical interface with clickable buttons for each cell.
- Players can make their moves by clicking the cells.
- Provides a visually appealing way to play the game.

#### c. Drawing View
- Offers a visual representation of X and O using images.
- Players can click cells to place their symbols.
- Provides a visually engaging and immersive gameplay experience.

### 3. AI Opponents
- Play against AI bots of varying difficulty levels: random bot and intermediate bot.
- Challenge yourself or practice your skills against these computer opponents.

## Getting Started
To get started with the Tic-Tac-Toe Java project, follow these steps:

1. **Clone the Repository:** Clone this repository to your local machine using Git:

    ```
    git clone https://github.com/nurkhat18/TicTacToe.git
    ```

2. **Run the JavaFX GUI:**
   - Open the project in ECLIPSE.
   - Click this link  https://gluonhq.com/products/javafx/ and scroll down to Downloads.
   - Download the correct SDK version of JavaFX for your computer.
   - Locate the `TicTacToeGUI.java` file and to run the program successfully, follow these steps:
      1. Select `Run > Run Configurations > (x) = Arguments` from the menu on ECLIPSE.
      2. Uncheck the box to the left of "Use the -XstartOnFirstThread" argument when launching with SWT. This step is crucial for ensuring the program's window opens correctly.
      3. In the "VM arguments" box, enter the following long command line argument (note that your `--module-path` may be different if you've installed JavaFX in a different location):
         - For Windows: --module-path C:\Program Files\javafx-sdk-20.0.2\lib --add-modules javafx.controls,javafx.media
         - For Mac: --module-path /Applications/javafx-sdk-20.0.2/lib --add-modules javafx.controls,javafx.media


   - Choose one of the available views (Text, Button, or Drawing) and select an AI opponent (Random Bot or Intermediate Bot).
   - Follow the on-screen instructions to play the game, making your moves according to the chosen view.

By following these steps, you can enjoy playing Tic-Tac-Toe using different views and challenge yourself against AI bots.

## Game Rules
- The game is played on a 3x3 grid.
- Players take turns to place their symbol (X or O) on the grid.
- The game ends when one player wins by having three of their symbols in a row (horizontally, vertically, or diagonally) or when the grid is full, resulting in a draw.

## Customization
Feel free to customize and extend this project to add more features or improve the user interface. Some ideas for customization include:

- Implementing additional AI difficulty levels.
- Adding multiplayer functionality for online play.
- Creating a more advanced AI opponent using algorithms like Minimax.

## Contributing
Contributions to this project are welcome! If you'd like to contribute, please fork the repository and create a pull request with your changes. Contributions such as bug fixes, enhancements, and additional features are appreciated.


Enjoy playing Tic-Tac-Toe, whether in the console or via the JavaFX GUI, and have fun exploring the different gameplay views and AI opponents!

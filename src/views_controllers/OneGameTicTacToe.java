// Nurkhat Jumabaev

package views_controllers;

import java.util.Scanner;

import model.TicTacToeGame;

public class OneGameTicTacToe {

	public static void main(String[] args) {

		TicTacToeGame oneGame = new TicTacToeGame();
		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.print("Enter row and column: ");
			String location = scanner.nextLine();

			String[] indexes = location.split(" ");
			int[] index = new int[2];

			index[0] = Integer.parseInt(indexes[0]);
			index[1] = Integer.parseInt(indexes[1]);

			char[][] arrBoard = oneGame.getTicTacToeBoard();

			if (arrBoard[index[0]][index[1]] != '_') {
				System.out.println("\nSquare taken, try again.");

			}
			oneGame.humanMove(index[0], index[1], false);
			String board = oneGame.toString();
			System.out.println(board + "\n");

			if (oneGame.didWin('O')) {

				System.out.println("\nO wins");
				break;
			}
			if (oneGame.didWin('X')) {

				System.out.println("\nX wins");
				break;
			}
			if (oneGame.tied()) {

				System.out.println("\nTie");
				break;
			}

		}

	}

}

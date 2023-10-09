// Nurkhat Jumabaev

package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.OurObserver;
import model.OurPoint;
import model.TicTacToeGame;

public class ButtonView extends BorderPane implements OurObserver {
	private TicTacToeGame theGame;
	GridPane inputPane;
	private Button button1 = new Button("_");
	private Button button2 = new Button("_");
	private Button button3 = new Button("_");
	private Button button4 = new Button("_");
	private Button button5 = new Button("_");
	private Button button6 = new Button("_");
	private Button button7 = new Button("_");
	private Button button8 = new Button("_");
	private Button button9 = new Button("_");
	private Button[][] buttons = new Button[3][3];
	char[][] board;
	Text prompt;

	public ButtonView(TicTacToeGame theModel) {
		theGame = theModel;
		
		initializePanel();
	}

	@Override
	public void update(Object theObserved) {
		// TODO Auto-generated method stub

		for (int row = 0; row <= 2; row++) {
			for (int col = 0; col <= 2; col++) {
				board = theGame.getTicTacToeBoard();
				if (board[row][col] == 'X') {
					buttons[row][col].setText("X");
				} else if (board[row][col] == 'O') {
					buttons[row][col].setText("O");
				} else {
					buttons[row][col].setText("_");
				}
			}
		}
	}

	private void initializePanel() {
		inputPane = new GridPane();

		RowConstraints topSpace = new RowConstraints();
		topSpace.setPrefHeight(20);
		inputPane.getRowConstraints().add(topSpace);

		buttons = new Button[3][3];
		int j = 1;
		int k = 1;
		Font font = Font.font("MonoSpace", 32);
		button1.setFont(font);
		button2.setFont(font);
		button3.setFont(font);
		button4.setFont(font);
		button5.setFont(font);
		button6.setFont(font);
		button7.setFont(font);
		button8.setFont(font);
		button9.setFont(font);

		buttons[0][0] = button1;
		buttons[0][1] = button2;
		buttons[0][2] = button3;
		buttons[1][0] = button4;
		buttons[1][1] = button5;
		buttons[1][2] = button6;
		buttons[2][0] = button7;
		buttons[2][1] = button8;
		buttons[2][2] = button9;

		inputPane.add(button1, 1, 1);
		inputPane.add(button2, 2, 1);
		inputPane.add(button3, 3, 1);
		inputPane.add(button4, 1, 2);
		inputPane.add(button5, 2, 2);
		inputPane.add(button6, 3, 2);
		inputPane.add(button7, 1, 3);
		inputPane.add(button8, 2, 3);
		inputPane.add(button9, 3, 3);

		button1.setOnAction(new input());
		button2.setOnAction(new input());
		button3.setOnAction(new input());
		button4.setOnAction(new input());
		button5.setOnAction(new input());
		button6.setOnAction(new input());
		button7.setOnAction(new input());
		button8.setOnAction(new input());
		button9.setOnAction(new input());

		inputPane.setHgap(5);
		inputPane.setVgap(10);
		inputPane.setAlignment(Pos.CENTER);

		this.setTop(inputPane);
		prompt = new Text("Click to make a move");
		font = Font.font("MonoSpace", FontWeight.BOLD, 20);

		prompt.setFont(font);
		this.setCenter(prompt);
	}

	private class input implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Button buttonClicked = (Button) arg0.getSource();

			for (int row = 0; row <= 2; row++) {
				for (int col = 0; col <= 2; col++) {
					if (buttons[row][col] == buttonClicked) {
						buttons[row][col].setText("X");
						OurPoint computerMove = theGame.humanMove(row, col, false);
						if (computerMove != null)
							buttons[computerMove.row][computerMove.col].setText("O");

					}

				}
			}
			if (theGame.tied()) {
				prompt.setText("Tie");
				for (int row = 0; row <= 2; row++) {
					for (int col = 0; col <= 2; col++) {
						buttons[row][col].setDisable(true);

					}
				}

			}
			if (theGame.didWin('X')) {
				prompt.setText("X WINS");
				for (int row = 0; row <= 2; row++) {
					for (int col = 0; col <= 2; col++) {
						buttons[row][col].setDisable(true);

					}
				}

			}
			if (theGame.didWin('O')) {
				prompt.setText("O WINS");
				for (int row = 0; row <= 2; row++) {
					for (int col = 0; col <= 2; col++) {
						buttons[row][col].setDisable(true);

					}
				}

			}
		}

	}
}

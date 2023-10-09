// Nurkhat Jumabaev

package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.OurObserver;
import model.TicTacToeGame;

public class TextAreaView extends BorderPane implements OurObserver {

	private TicTacToeGame theGame;
	private Button makeMove;
	private TextField row;
	private TextField col;
	private TextArea message;

	public TextAreaView(TicTacToeGame theModel) {
		theGame = theModel;
		initializePanel();
		registerHandlers();
	}

	private void initializePanel() {
		GridPane inputPane = new GridPane();

		inputPane.setHgap(10);
		inputPane.setVgap(10);

		row = new TextField();
		row.setMaxWidth(60);
		col = new TextField();
		col.setMaxWidth(60);

		makeMove = new Button("Make Move");
		inputPane.add(row, 3, 1);
		inputPane.add(col, 3, 2);
		inputPane.add(new Label("row"), 2, 1);
		inputPane.add(new Label("column"), 2, 2);
		inputPane.add(makeMove, 3, 3);

		message = new TextArea();
		message.setStyle("-fx-border-color: blue; border-width: 32;");
		Font font = new Font("Monospace", 32);
		message.setFont(font);
		message.setMaxHeight(180);
		String board = theGame.toString();
		message.setText(board);
		message.setEditable(false);
		this.setTop(inputPane);
		this.setCenter(message);
	}

	private void registerHandlers() {
		makeMove.setOnAction(new input());
	}

	private class input implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			String rowInput = row.getText();
			String colInput = col.getText();

			try {
				int intRow = Integer.parseInt(rowInput);
				int intCol = Integer.parseInt(colInput);

				if (intRow >= 0 && intRow <= 2 && intCol >= 0 && intCol <= 2) {
					if (theGame.available(intRow, intCol)) {
						theGame.humanMove(intRow, intCol, false);
						String board = theGame.toString();
						message.setText(board);
						makeMove.setText("Make Move");
					} else {
						row.clear();
						col.clear();
						makeMove.setText("Square Taken");
					}
				} else {
					makeMove.setText("Invalid choice");
					row.clear();
					col.clear();
				}

				if (theGame.tied()) {
					makeMove.setText("Tie");
					row.setEditable(false);
					col.setEditable(false);
				}
				if (theGame.didWin('X')) {
					makeMove.setText("X wins");
					row.setEditable(false);
					col.setEditable(false);
				}
				if (theGame.didWin('O')) {
					makeMove.setText("O wins");
					row.setEditable(false);
					col.setEditable(false);
				}
			} catch (NumberFormatException e) {
				makeMove.setText("Invalid choice");
				row.clear();
				col.clear();
			}
		}
	}

	// This method is called by Observable's notifyObservers()
	@Override
	public void update(Object observable) {
		String board = theGame.toString();
		message.setText(board);
		System.out.println("update called from the Observable TicTacToeGame");
	}
}
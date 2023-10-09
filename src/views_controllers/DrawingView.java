// Nurkhat Jumabaev

package views_controllers;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.OurObserver;
import model.TicTacToeGame;

public class DrawingView extends BorderPane implements OurObserver {

	private TicTacToeGame theGame;
	private Image X = new Image("file:images/X.png", false);
	private Image O = new Image("file:images/O.png", false);
	private char[][] board;
	private double oldX;
	private double oldY;
	private boolean drawing = false;
	private boolean clickDisabled = false;
	private Text prompt = new Text("Click to make a move");
	private Font font = new Font("MonoSpace", 20);

	private Canvas canvas;
	private GraphicsContext gc;

	public DrawingView(TicTacToeGame theModel) {
		theGame = theModel;
		initializePanel();
		registerHandlers();
	}

	private void initializePanel() {
		canvas = new Canvas(230, 230);
		gc = canvas.getGraphicsContext2D();
		drawSquares(gc);
		this.setCenter(canvas);
		prompt.setFont(font);
		this.setTop(prompt);
	}

	private void drawSquares(GraphicsContext gc) {
		gc.strokeRect(10, 10, 210, 210);

		gc.strokeLine(80, 10, 80, 220);
		gc.strokeLine(150, 10, 150, 220);

		gc.strokeLine(10, 80, 220, 80);
		gc.strokeLine(10, 150, 220, 150);
	}

	@Override
	public void update(Object theObserved) {
		board = theGame.getTicTacToeBoard();
		drawSymbol();
	}

	private void registerHandlers() {
		// Toggle drawing from off to on or on to off
		canvas.setOnMousePressed(new MousePressed());
	}

	private class MousePressed implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if (!clickDisabled) {
				// Toggle drawing
				drawing = !drawing;
				oldX = event.getSceneX();
				oldY = event.getSceneY();
				addImageToSquare(oldX, oldY);
			}

		}
	}

	private void addImageToSquare(double oldX, double oldY) {

		int col = (int) (oldX / 85);
		int row = (int) (oldY / 78);

		theGame.humanMove(row - 1, col, false);

		if (theGame.tied()) {
			prompt.setText("Tie");
			clickDisabled = true;

		}
		if (theGame.didWin('X')) {
			prompt.setText("X WINS");
			clickDisabled = true;

		}
		if (theGame.didWin('O')) {
			prompt.setText("O WINS");
			clickDisabled = true;
		}

	}

	private void drawSymbol() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		drawSquares(gc);

	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {
	            char symbol = board[row][col];

	            if (symbol == 'X') {
	                gc.drawImage(X, col * 70 + 15, row * 70 + 15, 60, 60);
	            } else if (symbol == 'O') {
	                gc.drawImage(O, col * 70 + 15, row * 70 + 15, 60, 60);
	            }
	        }
	    }
	}
}



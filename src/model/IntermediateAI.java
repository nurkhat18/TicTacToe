/**

 * Rick suggests, the IntermediateAI first check to stop a win of the opponent, 
 * then look for its own win. If neither is found, select any other open
 * spot randomly. You may use any other strategy as long as it beats RandomAI.
 * 
 * @authors Rick Mercer and Nurkhat Jumabaev
 */
package model;

public class IntermediateAI implements TicTacToeStrategy {

	@Override
	public OurPoint desiredMove(TicTacToeGame theGame) {
		if (theGame.maxMovesRemaining() == 0)
			throw new IGotNowhereToGoException(" -- Hey there programmer, the board is filled");

		char[][] board = theGame.getTicTacToeBoard();
		OurPoint point = this.findMove(board);
		while (point != null && theGame.available(point.row, point.col)) {
			return point;
		}
		return randomSelection(theGame);

	}

	private OurPoint findMove(char[][] board) {
		OurPoint point = checkRow(board, 'O');

		if (point != null)
			return point;
		point = checkCol(board, 'O');
		if (point != null)
			return point;
		point = checkDiagonal(board, 'O');
		if (point != null)
			return point;

		point = checkCol(board, 'X');
		if (point != null)
			return point;
		point = checkRow(board, 'X');
		if (point != null)
			return point;
		point = checkDiagonal(board, 'X');

		return point;

	}

	private OurPoint randomSelection(TicTacToeGame theGame) {
		RandomAI random = new RandomAI();
		return random.desiredMove(theGame);
	}

	private OurPoint checkCol(char[][] board, char playerChar) {
		int size = 3;
		OurPoint point = null;
		for (int c = 0; c < size; c++) {
			int colSum = 0;
			for (int r = 0; r < size; r++)
				if (board[r][c] == playerChar)
					colSum++;
				else {
					point = new OurPoint(r, c);
				}
			if (colSum == 2) {
				return point;
			}

		}
		return null;

	}

	private OurPoint checkRow(char[][] board, char playerChar) {
		int size = 3;
		OurPoint point = null;
		for (int r = 0; r < size; r++) {
			int rowSum = 0;
			for (int c = 0; c < size; c++)
				if (board[r][c] == playerChar)
					rowSum++;
				else {
					point = new OurPoint(r, c);
				}
			if (rowSum == 2) {
				return point;
			}

		}
		return null;

	}

	private OurPoint checkDiagonal(char[][] board, char playerChar) {
		// Check Diagonal from upper left to lower right
		int size = 3;
		int sum = 0;
		OurPoint point = null;

		for (int r = 0; r < size; r++)
			if (board[r][r] == playerChar) {
				sum++;
			} else {
				point = new OurPoint(r, r);
			}
		if (sum == 2)
			return point;

		// Check Diagonal from upper right to lower left
		sum = 0;
		for (int r = size - 1; r >= 0; r--)
			if (board[size - r - 1][r] == playerChar) {
				sum++;
			} else {
				point = new OurPoint(r, r);
			}

		if (sum == 2)
			return point;
		return null;
	}

}

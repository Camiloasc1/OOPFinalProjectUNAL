package game;

import entities.Board;
import entities.Piece;

public class Engine
{
	/**
	 * @param x
	 * @param y
	 * @return Successful
	 */
	public boolean movePiece(Piece piece, byte x, byte y)
	{
		if (!Rules.isValidMove(piece, x, y))
			return false;
		return Board.getBoard().move(piece, x, y);
	}
}

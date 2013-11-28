
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
		return Board.getBoard().movePiece(piece, x, y);
	}
	
	/**
	 * @param xi
	 * @param yi
	 * @param xf
	 * @param yf
	 * @return Successful
	 * @deprecated
	 */
	public boolean movePiece(byte xi, byte yi, byte xf, byte yf)
	{
		if (Board.getBoard().isEmptyPos(xi, yi))
			return false;
		
		return movePiece(Board.getBoard().getPieceAt(xi, yi), xf, yf);
	}
}

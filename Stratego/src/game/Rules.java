
package game;

import util.MathUtil;
import entities.Board;
import entities.Piece;
import entities.pieces.*;

/**
 * Rules for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public final class Rules
{
	/**
	 * Validates a movement for piece
	 * 
	 * @param piece
	 * @param x
	 * @param y
	 * @return true if is valid move piece to (x,y)
	 */
	public static boolean isValidMove(Piece piece, int x, int y)
	{
		if (piece instanceof Bomb || piece instanceof Flag)
		{
			return false;
		}
		
		if (piece instanceof Scout)
		{
			return (Board.getInstance().getPieceX(piece) == x || Board.getInstance().getPieceY(piece) == y);
		}
		
		return MathUtil.isEqualsDouble2(
				MathUtil.getDistance(Board.getInstance().getPieceX(piece), Board.getInstance().getPieceY(piece), x, y), 1);
// return (Math.abs(Board.getBoard().getPieceX(piece) - x) == 1 || Math.abs(Board.getBoard().getPieceY(piece) - y) == 1);
	}
}


package game;

import util.MathUtil;
import entities.Board;
import entities.Piece;
import entities.pieces.*;

public final class Rules
{
	/**
	 * @param piece
	 * @param x
	 * @param y
	 * @return Is a valid move for this piece
	 */
	public static boolean isValidMove(Piece piece, int x, int y)
	{
		if (piece instanceof Bomb || piece instanceof Flag)
		{
			return false;
		}
		
		if (piece instanceof Scout)
		{
			return (Board.getBoard().getPieceX(piece) == x || Board.getBoard().getPieceY(piece) == y);
		}
		
		return MathUtil.isEqualsDouble2(
				MathUtil.getDistance(Board.getBoard().getPieceX(piece), Board.getBoard().getPieceY(piece), x, y), 1);
	}
	
	/**
	 * @param ally
	 * @param enemy
	 * @return true if ally wins, false if ally loses
	 */
	public static boolean battle(Piece ally, Piece enemy)
	{
		
		if (ally.getOwner() == enemy.getOwner())
			return false;
		return ((ally.getLevel() - enemy.getLevel()) > 0);
	}
}

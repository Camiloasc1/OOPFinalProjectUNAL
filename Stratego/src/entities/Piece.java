
package entities;

import java.io.Serializable;

import util.MathUtil;

/**
 * Piece for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Piece implements Serializable
{
	private static final long serialVersionUID = -1557972597399797881L;
	
	private byte level;
	private boolean owner;
	
	/**
	 * @param level
	 * @param owner
	 */
	protected Piece(byte level, boolean owner)
	{
		super();
		this.level = level;
		this.owner = owner;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return Successful
	 */
	public boolean move(byte x, byte y)
	{
		if (!isValidMove(x, y))
			return false;
		return Board.getBoard().move(this, x, y);
	}
	
	/**
	 * @param enemy
	 *            The enemy piece
	 * @return true if this wins in battle versus enemy
	 */
	public boolean battle(Piece enemy)
	{
		if (this.owner == enemy.owner)
			return false;
		return ((this.level - enemy.level) > 0);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return Is a valid move for this piece
	 */
	public boolean isValidMove(int x, int y)
	{
		return MathUtil.isEqualsDouble2(
				MathUtil.getDistance(Board.getBoard().getPieceX(this), Board.getBoard().getPieceY(this), x, y), 1);
	}
}

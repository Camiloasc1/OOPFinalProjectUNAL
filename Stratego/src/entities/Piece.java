
package entities;

import java.io.Serializable;

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
	public Piece(byte level, boolean owner)
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
	
}

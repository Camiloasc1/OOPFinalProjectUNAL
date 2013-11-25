
package entities.pieces;

import entities.Piece;

public class Flag extends Piece
{
	private static final long serialVersionUID = 705086647830144235L;
	private static final byte LEVEL = -1;
	
	protected Flag(boolean owner)
	{
		super(LEVEL, owner);
	}
	
	/**
	 * @see entities.Piece#isValidMove(int, int)
	 */
	@Override
	public boolean isValidMove(int x, int y)
	{
		return false;
	}
}

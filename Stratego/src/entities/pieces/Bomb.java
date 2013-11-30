
package entities.pieces;

import entities.Piece;

public class Bomb extends Piece
{
	private static final long serialVersionUID = 7885307608401658657L;
	private static final byte LEVEL = 0;
	
	public Bomb(boolean owner)
	{
		super(LEVEL, owner);
	}
}

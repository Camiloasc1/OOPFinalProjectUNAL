
package entities.pieces;

import entities.Piece;

public class Lieutenant extends Piece
{
	private static final long serialVersionUID = 6606290614010021979L;
	private static final byte LEVEL = 5;
	
	protected Lieutenant(boolean owner)
	{
		super(LEVEL, owner);
	}
}


package entities.pieces;

import entities.Piece;

public class Lieutenant extends Piece
{
	private static final long serialVersionUID = 6606290614010021979L;
	private static final byte LEVEL = 6;
	
	public Lieutenant(boolean owner)
	{
		super(LEVEL, owner);
	}
}

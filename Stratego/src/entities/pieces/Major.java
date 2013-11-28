
package entities.pieces;

import entities.Piece;

public class Major extends Piece
{
	private static final long serialVersionUID = 3338087976595308382L;
	private static final byte LEVEL = 4;
	
	public Major(boolean owner)
	{
		super(LEVEL, owner);
	}
}

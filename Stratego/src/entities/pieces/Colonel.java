
package entities.pieces;

import entities.Piece;

public class Colonel extends Piece
{
	private static final long serialVersionUID = 6980602123811708487L;
	private static final byte LEVEL = 3;
	
	public Colonel(boolean owner)
	{
		super(LEVEL, owner);
	}
}


package entities.pieces;

import entities.Piece;

public class Spy extends Piece
{
	private static final long serialVersionUID = -1800302336896516083L;
	private static final byte LEVEL = 1;
	
	protected Spy(boolean owner)
	{
		super(LEVEL, owner);
	}
}

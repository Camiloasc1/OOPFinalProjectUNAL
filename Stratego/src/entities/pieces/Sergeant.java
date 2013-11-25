
package entities.pieces;

import entities.Piece;

public class Sergeant extends Piece
{
	private static final long serialVersionUID = -6688868929676851075L;
	private static final byte LEVEL = 4;
	
	protected Sergeant(boolean owner)
	{
		super(LEVEL, owner);
	}
}

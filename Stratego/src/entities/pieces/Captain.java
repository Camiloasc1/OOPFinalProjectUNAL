
package entities.pieces;

import entities.Piece;

public class Captain extends Piece
{
	private static final long serialVersionUID = 8866112921206365804L;
	private static final byte LEVEL = 6;
	
	protected Captain(boolean owner)
	{
		super(LEVEL, owner);
	}
	
}

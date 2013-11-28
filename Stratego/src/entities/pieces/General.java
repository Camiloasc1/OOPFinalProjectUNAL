
package entities.pieces;

import entities.Piece;

public class General extends Piece
{
	private static final long serialVersionUID = -4961754200317154756L;
	private static final byte LEVEL = 9;
	
	public General(boolean owner)
	{
		super(LEVEL, owner);
	}
}

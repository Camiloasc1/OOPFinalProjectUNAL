
package entities.pieces;

import entities.Piece;

public class Marshal extends Piece
{
	private static final long serialVersionUID = -8968574065529804558L;
	private static final byte LEVEL = 10;
	
	protected Marshal(boolean owner)
	{
		super(LEVEL, owner);
	}
}

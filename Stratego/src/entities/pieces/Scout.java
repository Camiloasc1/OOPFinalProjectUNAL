
package entities.pieces;

import entities.Board;
import entities.Piece;

public class Scout extends Piece
{
	private static final long serialVersionUID = 5584437327189831247L;
	private static final byte LEVEL = 2;
	
	protected Scout(boolean owner)
	{
		super(LEVEL, owner);
	}
}

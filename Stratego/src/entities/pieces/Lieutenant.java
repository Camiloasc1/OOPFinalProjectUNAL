
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Lieutenant extends Piece
{
	private static final long serialVersionUID = 6606290614010021979L;
	private static final byte LEVEL = 6;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Lieutenant(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

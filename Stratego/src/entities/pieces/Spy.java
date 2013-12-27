
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Spy extends Piece
{
	private static final long serialVersionUID = -1800302336896516083L;
	private static final byte LEVEL = 10;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Spy(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

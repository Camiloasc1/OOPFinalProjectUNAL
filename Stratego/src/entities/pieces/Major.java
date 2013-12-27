
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Major extends Piece
{
	private static final long serialVersionUID = 3338087976595308382L;
	private static final byte LEVEL = 4;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Major(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

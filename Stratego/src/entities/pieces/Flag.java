
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Flag extends Piece
{
	private static final long serialVersionUID = 705086647830144235L;
	private static final byte LEVEL = -1;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Flag(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

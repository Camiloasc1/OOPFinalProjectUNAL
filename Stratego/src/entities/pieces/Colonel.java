
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Colonel extends Piece
{
	private static final long serialVersionUID = 6980602123811708487L;
	private static final byte LEVEL = 3;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Colonel(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

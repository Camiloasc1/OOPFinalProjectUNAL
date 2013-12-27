
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class General extends Piece
{
	private static final long serialVersionUID = -4961754200317154756L;
	private static final byte LEVEL = 2;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public General(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

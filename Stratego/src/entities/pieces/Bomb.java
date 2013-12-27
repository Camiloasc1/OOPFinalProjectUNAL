
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Bomb extends Piece
{
	private static final long serialVersionUID = 7885307608401658657L;
	private static final byte LEVEL = 0;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Bomb(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

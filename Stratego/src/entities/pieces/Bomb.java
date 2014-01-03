
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Bomb extends Piece
{
	private static final long serialVersionUID = 7885307608401658657L;
	private static final byte LEVEL = 0;
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.BOMB);
	
	public Bomb(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

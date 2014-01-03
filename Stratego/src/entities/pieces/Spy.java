
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Spy extends Piece
{
	private static final long serialVersionUID = -1800302336896516083L;
	private static final byte LEVEL = 10;
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.SPY);
	
	public Spy(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

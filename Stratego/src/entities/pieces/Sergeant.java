
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Sergeant extends Piece
{
	private static final long serialVersionUID = -6688868929676851075L;
	private static final byte LEVEL = 7;
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.SERGEANT);
	
	public Sergeant(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}


package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Captain extends Piece
{
	private static final long serialVersionUID = 8866112921206365804L;
	private static final byte LEVEL = 5;
	private static final Sprite SPRITE = ResourceManager.getMap().get(ResourceManager.CAPTAIN);
	
	public Captain(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
	
}

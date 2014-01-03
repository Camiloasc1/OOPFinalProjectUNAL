
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Colonel extends Piece
{
	private static final long serialVersionUID = 6980602123811708487L;
	private static final byte LEVEL = 3;
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.COLONEL);
	
	public Colonel(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

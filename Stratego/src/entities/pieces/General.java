
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class General extends Piece
{
	private static final long serialVersionUID = -4961754200317154756L;
	private static final byte LEVEL = 2;
	private static final Sprite SPRITE = ResourceManager.getMap().get(ResourceManager.GENERAL);
	
	public General(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

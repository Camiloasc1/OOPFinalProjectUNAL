
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Scout extends Piece
{
	private static final long serialVersionUID = 5584437327189831247L;
	private static final byte LEVEL = 9;
	private static final Sprite SPRITE = ResourceManager.getMap().get(ResourceManager.SCOUT);
	
	public Scout(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

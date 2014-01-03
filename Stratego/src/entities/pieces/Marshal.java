
package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

public class Marshal extends Piece
{
	private static final long serialVersionUID = -8968574065529804558L;
	private static final byte LEVEL = 1;
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.MARSHAL);
	
	public Marshal(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

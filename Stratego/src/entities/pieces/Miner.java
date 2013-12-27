
package entities.pieces;

import entities.Piece;
import gui.Sprite;

public class Miner extends Piece
{
	private static final long serialVersionUID = -3591961735961800012L;
	private static final byte LEVEL = 8;
	private static final Sprite SPRITE = Sprite.getSpriteByLevel(LEVEL);
	
	public Miner(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

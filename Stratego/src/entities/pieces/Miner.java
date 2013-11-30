
package entities.pieces;

import entities.Piece;

public class Miner extends Piece
{
	private static final long serialVersionUID = -3591961735961800012L;
	private static final byte LEVEL = 8;
	
	public Miner(boolean owner)
	{
		super(LEVEL, owner);
	}
}


package entities;

import gui.Sprite;

import java.io.Serializable;

/**
 * Piece for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Piece implements Serializable
{
	private static final long serialVersionUID = -1557972597399797881L;
	
	private byte level;
	private boolean owner;
	private Sprite sprite;
	
	/**
	 * @param level
	 * @param owner
	 * @param sprite
	 */
	public Piece(byte level, boolean owner, Sprite sprite)
	{
		super();
		this.level = level;
		this.owner = owner;
		this.sprite = sprite;
	}
	
	/**
	 * @return the level
	 */
	public byte getLevel()
	{
		return level;
	}
	
	/**
	 * @return the owner
	 */
	public boolean getOwner()
	{
		return owner;
	}
	
	/**
	 * @return the sprite
	 */
	public Sprite getSprite()
	{
		return sprite;
	}
	
	/**
	 * draws the sprite
	 */
	public void draw()
	{
		Board board = Board.getInstance();
		int x = board.getPieceX(this) * sprite.getWidth();
		int y = board.getPieceY(this) * sprite.getHeight();
		if (owner)
		{
			sprite.draw(x, y);
		}
		else
		{
			Sprite.drawPiece(x, y);
		}
		return;
	}
}

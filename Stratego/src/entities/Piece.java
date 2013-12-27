
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
	 */
	protected Piece(byte level, boolean owner)
	{
		super();
		this.level = level;
		this.owner = owner;
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
	
}

/*******************************************************************************
 * File: Piece.java
 * Project: Stratego
 * 
 * Copyright (C) 2014 Camiloasc1.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package entities;

import entities.pieces.Bomb;
import entities.pieces.Captain;
import entities.pieces.Colonel;
import entities.pieces.Flag;
import entities.pieces.General;
import entities.pieces.Lieutenant;
import entities.pieces.Major;
import entities.pieces.Marshal;
import entities.pieces.Miner;
import entities.pieces.Scout;
import entities.pieces.Sergeant;
import entities.pieces.Spy;
import gui.Sprite;

import java.io.Serializable;

/**
 * Piece for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Piece implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1557972597399797881L;
	
	/**
	 * Level of this piece
	 */
	private byte level;
	/**
	 * Owner of this piece
	 */
	private boolean owner;
	/**
	 * Sprite for this piece
	 */
	private transient Sprite sprite;
	
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
	 * @return
	 */
	public boolean swapOwner()
	{
		return (owner = !owner);
	}
	
	/**
	 * @return the sprite
	 */
	public Sprite getSprite()
	{
		return sprite;
	}
	
	/**
	 * draws the Sprite
	 */
	public void draw()
	{
		Board board = Board.getInstance();
		int x = board.getPieceX(this) * Sprite.getPieceSprite().getWidth();
		int y = board.getPieceY(this) * Sprite.getPieceSprite().getHeight();
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Piece clone()// throws CloneNotSupportedException
	{
		if (this instanceof Bomb)
			return new Bomb(owner);
		if (this instanceof Captain)
			return new Captain(owner);
		if (this instanceof Colonel)
			return new Colonel(owner);
		if (this instanceof Flag)
			return new Flag(owner);
		if (this instanceof General)
			return new General(owner);
		if (this instanceof Lieutenant)
			return new Lieutenant(owner);
		if (this instanceof Major)
			return new Major(owner);
		if (this instanceof Marshal)
			return new Marshal(owner);
		if (this instanceof Miner)
			return new Miner(owner);
		if (this instanceof Scout)
			return new Scout(owner);
		if (this instanceof Sergeant)
			return new Sergeant(owner);
		if (this instanceof Spy)
			return new Spy(owner);
		return new Piece(level, owner, sprite);
	}
}

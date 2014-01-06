/*******************************************************************************
 * File: Lieutenant.java
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

package entities.pieces;

import entities.Piece;
import gui.ResourceManager;
import gui.Sprite;

/**
 * Lieutenant Piece for Stratego game
 * 
 * @author camiloasc1
 *
 */
public class Lieutenant extends Piece
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6606290614010021979L;
	/**
	 * Level of this piece
	 */
	private static final byte LEVEL = 6;
	/**
	 * Sprite for this piece
	 */
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.LIEUTENTANT);
	
	/**
	 * @param owner
	 */
	public Lieutenant(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

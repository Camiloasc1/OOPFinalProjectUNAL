/*******************************************************************************
 * File: Miner.java
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

public class Miner extends Piece
{
	private static final long serialVersionUID = -3591961735961800012L;
	private static final byte LEVEL = 8;
	private static final Sprite SPRITE = ResourceManager.getSpriteMap().get(ResourceManager.MINER);
	
	public Miner(boolean owner)
	{
		super(LEVEL, owner, SPRITE);
	}
}

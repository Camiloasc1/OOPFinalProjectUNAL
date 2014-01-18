/*******************************************************************************
 * File: Action.java
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

package net;

import java.io.Serializable;

import entities.Board;
import entities.Piece;

/**
 * An action in Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public final class Action implements Serializable
{
	private static final long serialVersionUID = 1793969592233254860L;
	
	private Actions action;
	private Board board;
	private Piece piece;
	private int x;
	private int y;
	
	/**
	 * @param action
	 * @param piece
	 * @param x
	 * @param y
	 */
	public Action(Actions action, Piece piece, int x, int y)
	{
		super();
		this.action = action;
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the action
	 */
	public Actions getAction()
	{
		return action;
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * @return the piece
	 */
	public Piece getPiece()
	{
		return piece;
	}
	
	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}
	
}

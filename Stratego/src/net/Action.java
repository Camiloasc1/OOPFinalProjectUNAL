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
	private boolean player;
	private boolean turn;
	private int value;
	private int x;
	private int y;
	
	/**
	 * @param action
	 */
	public Action(Actions action)
	{
		super();
		this.action = action;
	}
	
	/**
	 * @param action
	 * @param player
	 */
	public Action(Actions action, boolean player)
	{
		super();
		this.action = action;
		this.player = player;
	}
	
	/**
	 * @param action
	 * @param piece
	 * @param x
	 * @param y
	 */
	public Action(Piece piece, int x, int y)
	{
		super();
		action = Actions.MOVE;
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param action
	 * @param board
	 */
	public Action(Actions action, Board board)
	{
		super();
		this.action = action;
		this.board = board;
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
	 * @return the player
	 */
	public boolean getPlayer()
	{
		return player;
	}
	
	/**
	 * @return the turn
	 */
	public boolean getTurn()
	{
		return turn;
	}
	
	/**
	 * @return the value
	 */
	public int getValue()
	{
		return value;
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

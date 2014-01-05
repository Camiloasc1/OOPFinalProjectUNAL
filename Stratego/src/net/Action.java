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
public class Action implements Serializable
{
	private static final long serialVersionUID = 1793969592233254860L;
	public static final byte ACTIONMOVE = 0;
	public static final byte ACTIONSAVE = 1;
	public static final byte ACTIONLOAD = 2;
	public static final byte ACTIONEXIT = 2;
	
	private byte action;
	private Board board;
	private Piece piece;
	private int x;
	private int y;
	
	/**
	 * @return the action
	 */
	public synchronized byte getAction()
	{
		return action;
	}
	
	/**
	 * @param action
	 *            the action to set
	 */
	public synchronized void setAction(byte action)
	{
		this.action = action;
	}
	
	/**
	 * @return the board
	 */
	public synchronized Board getBoard()
	{
		return board;
	}
	
	/**
	 * @param board
	 *            the board to set
	 */
	public synchronized void setBoard(Board board)
	{
		this.board = board;
	}
	
	/**
	 * @return the piece
	 */
	public synchronized Piece getPiece()
	{
		return piece;
	}
	
	/**
	 * @param piece
	 *            the piece to set
	 */
	public synchronized void setPiece(Piece piece)
	{
		this.piece = piece;
	}
	
	/**
	 * @return the x
	 */
	public synchronized int getX()
	{
		return x;
	}
	
	/**
	 * @param x
	 *            the x to set
	 */
	public synchronized void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * @return the y
	 */
	public synchronized int getY()
	{
		return y;
	}
	
	/**
	 * @param y
	 *            the y to set
	 */
	public synchronized void setY(int y)
	{
		this.y = y;
	}
	
}

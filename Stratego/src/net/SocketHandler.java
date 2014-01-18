/**
 * File: SocketHandler.java
 * Package: Stratego.net.SocketHandler
 * Creation: 17/01/2014 at 14:11:38
 */

package net;

import entities.Board;

/**
 * @author camiloasc1
 * 
 */
public abstract class SocketHandler
{
	private Board board;
	
	/**
	 * @return the board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * @param board
	 *            the board to set
	 */
	protected void setBoard(Board board)
	{
		this.board = board;
	}
}

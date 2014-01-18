/*******************************************************************************
 * File: Engine.java
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

package game;

import entities.Board;
import entities.Piece;
import entities.pieces.Bomb;
import entities.pieces.Flag;
import entities.pieces.Marshal;
import entities.pieces.Miner;
import entities.pieces.Spy;

/**
 * Engine for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Engine
{
	/**
	 * Moves the piece to the specified position
	 * 
	 * @param piece
	 * @param x
	 * @param y
	 * @return true if piece has moved to (x,y)
	 */
	public static boolean movePiece(Piece piece, int x, int y)
	{
		if (!Rules.isValidMove(piece, x, y))
			return false;
		
		Board board = Board.getInstance();
		
		if (!board.isEmptyPos(x, y)) // Is a Battle
		{
			int res = battle(piece, board.getPieceAt(x, y));
			if ((res == 1) || (res == 2))
				return true;
			else
				return false;
		}
		
		return board.setPiecePos(piece, x, y);
	}
	
	/**
	 * Moves the piece in (xi, yi) to the specified position
	 * 
	 * @param xi
	 * @param yi
	 * @param xf
	 * @param yf
	 * @return true if piece in (xi,yi) has moved to (xf,yf)
	 * @deprecated
	 */
	@Deprecated
	public static boolean movePiece(byte xi, byte yi, byte xf, byte yf)
	{
		Board board = Board.getInstance();
		
		if (board.isEmptyPos(xi, yi))
			return false;
		
		return movePiece(board.getPieceAt(xi, yi), xf, yf);
	}
	
	/**
	 * Simulates a battle between ally piece an enemy piece
	 * When flag is captured the game ends
	 * 
	 * @param ally
	 * @param enemy
	 * @return <code>2</code> if draw (both die)
	 *         <p>
	 *         <code>1</code> if ally live & enemy die
	 *         <p>
	 *         <code>0</code> if friend fire, invalid ally (Bomb and Flag can't attack)
	 *         <p>
	 *         <code>-1</code> if ally die & enemy live
	 */
	public static int battle(Piece ally, Piece enemy)
	{
		if ((ally == null) || (enemy == null))
			return 0;
		if (ally.getOwner() == enemy.getOwner())
			return 0;
		if ((ally instanceof Bomb) || (ally instanceof Flag))
			return 0;
		
		Board board = Board.getInstance();
		
		if (ally.getLevel() == enemy.getLevel())
		{
			if (!board.isEmpty())
			{
				board.removePiece(enemy);
				board.removePiece(ally);
			}
			
			return 2;
		}
		
		boolean result;
		
		result = (ally.getLevel() > enemy.getLevel());
		
		if (enemy instanceof Bomb)
		{
			if (ally instanceof Miner)
			{
				result = true;
			}
			else
			{
				result = false;
			}
		}
		
		if (ally instanceof Spy)
		{
			if (enemy instanceof Marshal)
			{
				result = true;
			}
			else
			{
				result = false;
			}
		}
		
		if (enemy instanceof Flag) // Game ends
		{
			result = true;
			if (!board.isEmpty())
			{
				// TODO
				// endGame();
			}
		}
		
		if (result)
		{
			if (!board.isEmpty())
			{
				board.removePiece(enemy);
			}
		}
		else
		{
			if (!board.isEmpty())
			{
				board.removePiece(ally);
			}
		}
		
		return ((result) ? 1 : -1);
	}
}

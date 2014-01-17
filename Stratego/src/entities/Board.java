/*******************************************************************************
 * File: Board.java
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

import game.Engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Game board for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public final class Board implements Serializable, Iterable<Piece>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -333970939198499361L;
	/**
	 * Board size for Stratego
	 */
	public static final int SIZE = 10;
	/**
	 * Singleton instance
	 */
	private static volatile Board INSTANCE = new Board();
	
	/**
	 * Pieces in the board
	 */
	private Piece[][] map;
	
	/**
	 * Singleton Constructor
	 */
	private Board()
	{
		super();
		map = new Piece[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				map[i][j] = null;
			}
		}
	}
	
	/**
	 * @return The singleton instance of Board
	 */
	public static synchronized Board getInstance()
	{
		return INSTANCE;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
		// return getInstance();
	}
	
	/**
	 * @param owner
	 * @return
	 */
	public static Board getBoardPrint(boolean owner)
	{
		if (!owner)
		{
			Board enemy = new Board();
			
			for (int i = SIZE - 1; i >= 0; i--)
			{
				for (int j = SIZE - 1; j >= 0; j--)
				{
					Piece piece = INSTANCE.map[i][j];
					enemy.map[(SIZE - 1 - i)][(SIZE - 1 - j)] = new Piece(piece.getLevel(), !piece.getOwner(),
							piece.getSprite());
				}
			}
			
			return enemy;
		}
		return INSTANCE;
	}
	
	/**
	 * @return Board singleton even if was previously serialized
	 */
	// @SuppressWarnings("unused")
	// private Board readResolve()
	// {
	// return INSTANCE;
	// }
	
	/**
	 * @param x
	 *            File
	 * @param y
	 *            Column
	 * @return <b>true</b> if position (x,y) is empty
	 */
	public boolean isEmptyPos(int x, int y)
	{
		if (((y == 4) || (y == 5)) && ((x == 2) || (x == 3) || (x == 6) || (x == 7)))
			return false;
		
		if (((y == 4) || (y == 5)) && ((x == 2) || (x == 3) || (x == 6) || (x == 7)))
			return false;
		
		return (map[x][y] == null);
	}
	
	/**
	 * @param piece
	 * @param x
	 *            File
	 * @param y
	 *            Column
	 * @return <b>true</b> if piece is at (x,y)
	 */
	public boolean isPieceAt(Piece piece, int x, int y)
	{
		if (piece == null)
			return false;
		
		return (map[x][y] == piece);
	}
	
	/**
	 * @param piece
	 * @return (x) position of piece
	 */
	public int getPieceX(Piece piece)
	{
		if (piece == null)
			return -1;
		
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				if (isPieceAt(piece, i, j))
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param piece
	 * @return (y) position of piece
	 */
	public int getPieceY(Piece piece)
	{
		if (piece == null)
			return -1;
		
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				if (isPieceAt(piece, i, j))
					return j;
			}
		}
		return -1;
	}
	
	/**
	 * @param x
	 *            File
	 * @param y
	 *            Column
	 * @return Piece at (x,y)
	 */
	public Piece getPieceAt(int x, int y)
	{
		return map[x][y];
	}
	
	/**
	 * @param x
	 *            File
	 * @param y
	 *            Column
	 * @return <code>true</code> if piece has moved to (x,y)
	 */
	public boolean setPiecePos(Piece piece, int x, int y)
	{
		if ((piece != null) && (getPieceAt(x, y) == null))
		{
			map[getPieceX(piece)][getPieceY(piece)] = null;
			map[x][y] = piece;
			return true;
		}
		return false;
	}
	
	/**
	 * @param x
	 *            File
	 * @param y
	 *            Column
	 * @return <code>true</code> if piece has moved to (x,y)
	 */
	public boolean movePiece(Piece piece, int x, int y)
	{
		return Engine.movePiece(piece, x, y);
	}
	
	/**
	 * @param piece
	 * @return <code>true</code> if piece added
	 */
	public boolean addPiece(Piece piece)
	{
		for (int j = 0; j < SIZE; j++)
		{
			if ((j == 4) || (j == 5))
			{
				continue;
			}
			
			for (int i = 0; i < SIZE; i++)
			{
				if (isEmptyPos(i, j))
				{
					map[i][j] = piece;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param piece
	 * @return <code>true</code> if piece has removed
	 */
	public boolean removePiece(Piece piece)
	{
		if (piece == null)
			return false;
		
		map[getPieceX(piece)][getPieceY(piece)] = null;
		return true;
	}
	
	/**
	 * @param x
	 *            File
	 * @param y
	 *            Column
	 * @return <code>true</code> if piece at (x,y) has removed
	 */
	public boolean removePiece(int x, int y)
	{
		return removePiece(getPieceAt(x, y));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Piece> iterator()
	{
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Piece piece;
		for (int j = 0; j < SIZE; j++)
		{
			for (int i = 0; i < SIZE; i++)
			{
				piece = getPieceAt(i, j);
				if (piece != null)
				{
					pieces.add(piece);
				}
			}
		}
		return pieces.iterator();
	}
}

/*******************************************************************************
 * File: Rules.java
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
import entities.pieces.Scout;

/**
 * Rules for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public final class Rules
{
	/**
	 * Validates a movement for piece
	 * 
	 * @param piece
	 * @param x
	 * @param y
	 * @return true if is valid move piece to (x,y)
	 */
	public static boolean isValidMove(Piece piece, int x, int y)
	{
		if ((piece instanceof Bomb) || (piece instanceof Flag))
			return false;
		
		if (piece instanceof Scout)
			return ((Board.getInstance().getPieceX(piece) == x) || (Board.getInstance().getPieceY(piece) == y));
		return true;
		// return MathUtil.isEqualsDouble2(
		// MathUtil.getDistance(Board.getInstance().getPieceX(piece), Board.getInstance().getPieceY(piece), x, y), 1);
		// return (Math.abs(Board.getBoard().getPieceX(piece) - x) == 1 || Math.abs(Board.getBoard().getPieceY(piece) - y) == 1);
	}
}

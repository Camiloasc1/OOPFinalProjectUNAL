
package game;

import entities.Board;
import entities.Piece;
import entities.pieces.*;

/**
 * Engine for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Engine
{
	/**
	 * Initialize the game
	 */
	public static void initGame()
	{
		
	}
	
	/**
	 * Ends the game
	 */
	public static void endGame()
	{
		
	}
	
	/**
	 * Moves the piece to the specified position
	 * 
	 * @param piece
	 * @param x
	 * @param y
	 * @return true if piece has moved to (x,y)
	 */
	public static boolean movePiece(Piece piece, byte x, byte y)
	{
		if (!Rules.isValidMove(piece, x, y))
			return false;
		
		Board board = Board.getInstance();
		
		if (!board.isEmptyPos(x, y)) // Is a Battle
		{
			if (!battle(piece, board.getPieceAt(x, y)))
				return false;
		}
		
		return board.movePiece(piece, x, y);
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
	 * @return true if ally wins
	 *         <p>
	 *         false if ally loses
	 */
	public static boolean battle(Piece ally, Piece enemy)
	{
		if (ally.getOwner() == enemy.getOwner())
			return false;
		
		Board board = Board.getInstance();
		boolean result;
		
		result = (ally.getLevel() <= enemy.getLevel());
		
		if (enemy instanceof Bomb)
		{
			if (ally instanceof Miner)
				result = true;
			else
				result = false;
		}
		
		if (ally instanceof Spy)
		{
			if (enemy instanceof Marshal)
				result = true;
			else
				result = false;
		}
		
		if (enemy instanceof Flag) // Game ends
		{
			result = true;
			endGame();
		}
		
		if (result)
			board.removePiece(enemy);
		else
			board.removePiece(ally);
		
		return result;
	}
}

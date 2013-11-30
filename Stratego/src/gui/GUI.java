
package gui;

import java.io.Serializable;

import entities.Board;
import entities.Piece;

/**
 * Interface for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class GUI implements Serializable
{
	private static final long serialVersionUID = 8974292654822002105L;
	private static GUI gui;
	
	/**
	 * Constructor of the unique instance of Board
	 */
	private GUI()
	{
		
	}
	
	/**
	 * @return The unique instance of Board
	 */
	public static synchronized GUI getGUI()
	{
		if (gui == null)
		{
			gui = new GUI();
		}
		return gui;
	}
	
	@Override
	public Object clone()
	{
		return getGUI();
	}
	
	public void drawBoard()
	{
		Board board = Board.getBoard();
		Piece tmp = null;
		
		for (byte i = 0; i < Board.getSize(); i++)
		{
			for (byte j = 0; j < Board.getSize(); j++)
			{
				tmp = board.getPieceAt(i, j);
				if (tmp == null)
					System.out.print("[##]");
				else
					System.out.println("[" + tmp.getLevel() + "]");
			}
			System.out.println();
		}
		
	}
}

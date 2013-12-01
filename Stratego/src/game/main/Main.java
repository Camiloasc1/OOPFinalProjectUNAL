
package game.main;

import entities.Board;
import entities.Piece;
import entities.pieces.*;
import gui.GUI;

/**
 * @author Camilo Sanchez
 * 
 */
public class Main
{
	private static final byte NUMBOMB = 6;
	private static final byte NUMCAPTAIN = 4;
	private static final byte NUMCOLONEL = 2;
	private static final byte NUMFLAG = 1;
	private static final byte NUMGENERAL = 1;
	private static final byte NUMLIEUTERNANT = 4;
	private static final byte NUMMAJOR = 3;
	private static final byte NUMMARSHAL = 1;
	private static final byte NUMMINER = 5;
	private static final byte NUMSCOUT = 8;
	private static final byte NUMSERGEANT = 4;
	private static final byte NUMSPY = 1;
	
	public static void main(String[] args)
	{
		Board board = Board.getBoard();
		GUI.getGUI().drawBoard();
		System.out.println();
		init();
// while (true)
// {
		GUI.getGUI().drawBoard();
// try
// {
// Thread.sleep(5000);
// }
// catch (InterruptedException e)
// {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
		close();
	}
	
	/**
	 * Initialize the game
	 */
	public static void init()
	{
		Board board = Board.getBoard();
		for (int i = 0; i < NUMBOMB; i++)
		{
			board.addPiece(new Bomb(true));
		}
		for (int i = 0; i < NUMCAPTAIN; i++)
		{
			board.addPiece(new Captain(true));
		}
		for (int i = 0; i < NUMCOLONEL; i++)
		{
			board.addPiece(new Colonel(true));
		}
		for (int i = 0; i < NUMFLAG; i++)
		{
			board.addPiece(new Flag(true));
		}
		for (int i = 0; i < NUMGENERAL; i++)
		{
			board.addPiece(new General(true));
		}
		for (int i = 0; i < NUMLIEUTERNANT; i++)
		{
			board.addPiece(new Lieutenant(true));
		}
		for (int i = 0; i < NUMMAJOR; i++)
		{
			board.addPiece(new Major(true));
		}
		for (int i = 0; i < NUMMARSHAL; i++)
		{
			board.addPiece(new Marshal(true));
		}
		for (int i = 0; i < NUMMINER; i++)
		{
			board.addPiece(new Miner(true));
		}
		for (int i = 0; i < NUMSCOUT; i++)
		{
			board.addPiece(new Scout(true));
		}
		for (int i = 0; i < NUMSERGEANT; i++)
		{
			board.addPiece(new Sergeant(true));
		}
		for (int i = 0; i < NUMSPY; i++)
		{
			board.addPiece(new Spy(true));
		}
		for (int i = 0; i < NUMBOMB; i++)
		{
			board.addPiece(new Bomb(false));
		}
		for (int i = 0; i < NUMCAPTAIN; i++)
		{
			board.addPiece(new Captain(false));
		}
		for (int i = 0; i < NUMCOLONEL; i++)
		{
			board.addPiece(new Colonel(false));
		}
		for (int i = 0; i < NUMFLAG; i++)
		{
			board.addPiece(new Flag(false));
		}
		for (int i = 0; i < NUMGENERAL; i++)
		{
			board.addPiece(new General(false));
		}
		for (int i = 0; i < NUMLIEUTERNANT; i++)
		{
			board.addPiece(new Lieutenant(false));
		}
		for (int i = 0; i < NUMMAJOR; i++)
		{
			board.addPiece(new Major(false));
		}
		for (int i = 0; i < NUMMARSHAL; i++)
		{
			board.addPiece(new Marshal(false));
		}
		for (int i = 0; i < NUMMINER; i++)
		{
			board.addPiece(new Miner(false));
		}
		for (int i = 0; i < NUMSCOUT; i++)
		{
			board.addPiece(new Scout(false));
		}
		for (int i = 0; i < NUMSERGEANT; i++)
		{
			board.addPiece(new Sergeant(false));
		}
		for (int i = 0; i < NUMSPY; i++)
		{
			board.addPiece(new Spy(false));
		}
	}
	
	/**
	 * Finalize the game
	 */
	public static void close()
	{
		
	}
}

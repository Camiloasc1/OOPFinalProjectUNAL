
package game.main;

import entities.Board;

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
		@SuppressWarnings("unused")
		Board board = Board.getBoard();
		init();
		close();
	}
	
	/**
	 * Initialize the game
	 */
	public static void init()
	{
		
	}
	
	/**
	 * Finalize the game
	 */
	public static void close()
	{
		
	}
}

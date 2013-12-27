
package game.main;

import entities.Board;

public class Main
{
	
	public static void main(String[] args)
	{
		Board board = Board.getBoard();
		board.getPieceAt((byte) 0, (byte) 0);
	}
	
}

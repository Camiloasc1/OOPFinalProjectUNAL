
package game.main;

import entities.Board;
import entities.pieces.Bomb;
import gui.GUI;
import gui.states.InGame;

import org.lwjgl.opengl.Display;

public class MainClient
{
	public static boolean EXIT_GAME = false;
	
	public static void main(String[] args)
	{
		GUI.init();
		
		// TODO delete this
		Board board = Board.getBoard();
		for (int i = 0; i < 40; i++)
		{
			board.addPiece(new Bomb(true));
			board.addPiece(new Bomb(false));
		}
		
		// Main Loop
		while (!Display.isCloseRequested() && !EXIT_GAME)
		{
			// TODO Switch different game states
			InGame.run();
		}
		
		// Game Exit
		GUI.cleanup();
		
		System.exit(0);
// return;
	}
}

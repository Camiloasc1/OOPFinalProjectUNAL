
package game.main;

import entities.Board;
import entities.pieces.Bomb;
import gui.GUI;
import gui.states.GameStates;
import gui.states.InGame;

import org.lwjgl.opengl.Display;

public class MainClient
{
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
		GameStates.setState(GameStates.INGAME);
		
		// Main Loop
		while (!Display.isCloseRequested() && !GameStates.getExitFlag())
		{
			switch (GameStates.getState())
			{
				case GameStates.MAINMENU:
				{
					break;
				}
				case GameStates.PAUSEMENU:
				{
					break;
				}
				case GameStates.SETUP:
				{
					break;
				}
				case GameStates.INITGAME:
				{
					break;
				}
				case GameStates.INGAME:
				{
					InGame.run();
					break;
				}
			}
		}
		
		// Game Exit
		GUI.cleanup();
		
		System.exit(0);
		// return;
	}
}

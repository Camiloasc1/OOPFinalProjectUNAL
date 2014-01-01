
package game.main;

import entities.Board;
import entities.pieces.Bomb;
import gui.GUI;
import gui.states.GameStates;
import gui.states.InGame;
import gui.states.InitGame;
import gui.states.MainMenu;
import gui.states.PauseMenu;
import gui.states.Setup;

import org.lwjgl.opengl.Display;

public class MainClient
{
	public static void main(String[] args)
	{
		GUI.init();
		
		// TODO delete this
		Board board = Board.getInstance();
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
					MainMenu.getInstance().run();
					break;
				}
				case GameStates.PAUSEMENU:
				{
					PauseMenu.getInstance().run();
					break;
				}
				case GameStates.SETUP:
				{
					Setup.getInstance().run();
					break;
				}
				case GameStates.INITGAME:
				{
					InitGame.getInstance().run();
					break;
				}
				case GameStates.INGAME:
				{
					InGame.getInstance().run();
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

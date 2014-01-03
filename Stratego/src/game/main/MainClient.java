
package game.main;

import entities.Board;
import entities.pieces.Bomb;
import gui.GUI;
import gui.states.GameStates;
import gui.states.InGame;
import gui.states.InitGame;
import gui.states.LoadGame;
import gui.states.MainMenu;
import gui.states.PauseMenu;
import gui.states.SaveGame;
import gui.states.Setup;

import java.io.File;

import org.lwjgl.opengl.Display;

public class MainClient
{
	public static void main(String[] args)
	{
		System.setProperty("java.library.path", "lib");
		// Init libraries
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl-2.9.0/native/linux/").getAbsolutePath());
		
		GUI.init();
		
		// TODO delete this
		Board board = Board.getInstance();
		for (int i = 0; i < 40; i++)
		{
			board.addPiece(new Bomb(true));
			board.addPiece(new Bomb(false));
		}
		
		GameStates state = GameStates.getInstance();
		state = GameStates.MAINMENU;
		
		// Main Loop
		while (!Display.isCloseRequested() && !GameStates.isExitFlag())
		{
			switch (state)
			{
				case MAINMENU:
				{
					MainMenu.getInstance().run();
					break;
				}
				case PAUSEMENU:
				{
					PauseMenu.getInstance().run();
					break;
				}
				case SETUPGAME:
				{
					Setup.getInstance().run();
					break;
				}
				case LOADGAME:
				{
					LoadGame.getInstance().run();
					break;
				}
				case SAVEGAME:
				{
					SaveGame.getInstance().run();
					break;
				}
				case INITGAME:
				{
					InitGame.getInstance().run();
					break;
				}
				case INGAME:
				{
					InGame.getInstance().run();
					break;
				}
				case EXITGAME:
					break;
				default:
					break;
			}
		}
		
		// Game Exit
		GUI.cleanup();
		
		System.exit(0);
		// return;
	}
}

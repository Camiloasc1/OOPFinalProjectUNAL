/*******************************************************************************
 * File: MainClient.java
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

package game.main;

import gui.GUI;
import gui.states.GameStates;
import gui.states.InGame;
import gui.states.InitGame;
import gui.states.LoadGame;
import gui.states.MainMenu;
import gui.states.PauseMenu;
import gui.states.SaveGame;
import gui.states.SetupGame;

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
		
		GameStates.SetState(GameStates.MAINMENU);
		
		// Main Loop
		while (!Display.isCloseRequested() && !GameStates.isExitFlag())
		{
			switch (GameStates.getState())
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
					SetupGame.getInstance().run();
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

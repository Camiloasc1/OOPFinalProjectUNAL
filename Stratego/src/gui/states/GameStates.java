/*******************************************************************************
 * File: GameStates.java
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

package gui.states;

/**
 * @author camiloasc1
 * 
 */
public enum GameStates
{
	EXITGAME, MAINMENU, PAUSEMENU, SETUPGAME, LOADGAME, SAVEGAME, INITGAME, INGAME;
	
	private static volatile GameStates INSTANCE = MAINMENU;
	
	public static void activateExitFlag()
	{
		INSTANCE = EXITGAME;
	}
	
	public static boolean isExitFlag()
	{
		return (INSTANCE == EXITGAME);
	}
	
	public static synchronized GameStates getState()
	{
		return INSTANCE;
	}
	
	public static synchronized void SetState(GameStates state)
	{
		if (state == EXITGAME)
		{
			return;
		}
		INSTANCE = state;
	}
}

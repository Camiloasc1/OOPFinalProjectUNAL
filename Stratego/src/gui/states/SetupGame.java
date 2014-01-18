/*******************************************************************************
 * File: SetupGame.java
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

import gui.util.DisplayMessage;

import org.lwjgl.opengl.GL11;

/**
 * SetupGame state for Stratego game
 * 
 * @author camiloasc1
 * 
 */
public final class SetupGame extends GameState
{
	/**
	 * Singleton Instance
	 */
	private static volatile GameState INSTANCE = new SetupGame();
	
	@Override
	protected void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	@Override
	protected void logic()
	{
		// TODO Auto-generated method stub
		GameStates.SetState(GameStates.INITGAME);
		DisplayMessage.show("Ordena tus piezas en la parte inferior");
	}
	
	/**
	 * Singleton Constructor
	 */
	private SetupGame()
	{
	}
	
	/**
	 * @return the Singleton Instance
	 */
	public static synchronized GameState getInstance()
	{
		return INSTANCE;
	}
}

/*******************************************************************************
 * File: GameState.java
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

import gui.GUI;

import org.lwjgl.opengl.Display;

/**
 * @author camiloasc1
 * 
 */
public abstract class GameState implements Cloneable
{
	/**
	 * Main method to run the game state
	 */
	// public abstract void run();
	public final void run()
	{
		// Only bother rendering if the window is active, visible or dirty
		if (Display.isActive() || Display.isVisible() || Display.isDirty())
		{
			// While no attempt to close the display is made..
			// Put render code here.
			logic();
			render();
			// Put input handling code here.
			Display.update();
			// Refresh the display and poll input.
			Display.sync(GUI.FRAMERATE);
			// Wait until 16.67 milliseconds have passed. (Maintain 60 frames-per-second)
		}
		else
		{
			// The window is not in the foreground, so we can allow other stuff to run and
			// infrequently update
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 */
	protected abstract void render();
	
	/**
	 * 
	 */
	protected abstract void logic();
	
	// private static volatile GameState INSTANCE = new State();
	// private State();
	// public static GameState getInstance();
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}

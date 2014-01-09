/*******************************************************************************
 * File: GUI.java
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

package gui;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Manager of GUI for Stratego game
 * 
 * @author camiloasc1
 * 
 */
public class GUI
{
	/**
	 * Width of window
	 */
	public static final int WIDTH = 640;
	/**
	 * Height of window
	 */
	public static final int HEIGHT = 640;
	/**
	 * Frame rate (FPS) of window
	 */
	public static final int FRAMERATE = 60;
	/**
	 * Is full screen activated
	 */
	private static final boolean FULL_SCREEN = false;
	/**
	 * Window title
	 */
	private static final String WINDOW_TITLE = "Stratego";
	
	/**
	 * Internally used for getDelta()
	 */
	private static long lastFrame;
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime()
	{
		return System.nanoTime() / 1000000;
	}
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime2()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * @return Delta time
	 */
	public static int getDelta()
	{
		long time = getTime();
		// long time = getTime2();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	/**
	 * Initializes the window
	 */
	public static void init()
	{
		// Creates the Window
		try
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(WINDOW_TITLE);
			Display.setFullscreen(FULL_SCREEN);
			Display.setVSyncEnabled(true);
			
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		// Init
		initGL();
		getDelta();
		
		// Load Resources
		loadResources();
	}
	
	/**
	 * Initializes OpenGL
	 */
	private static void initGL()
	{
		// Set Up the Canvas
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glClearColor(0, 0, 0, 0);
		
		// Enable Texturing
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		// Enable Blending to support transparency
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	/**
	 * Load the game resources
	 */
	private static void loadResources()
	{
		ResourceManager.getSpriteMap();
		
	}
	
	/**
	 * Free the game resources
	 */
	public static void cleanup()
	{
		ResourceManager.cleanup();
		Display.destroy();
	}
}


package gui;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

public class GUI
{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 640;
	public static final int FRAMERATE = 60;
	private static final boolean FULL_SCREEN = false;
	private static final String WINDOW_TITLE = "Stratego";
	
	private static long lastFrame;
	
	// Public Resources
	public static ArrayList<TrueTypeFont> fonts = new ArrayList<TrueTypeFont>();
	
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
	
	public static void init()
	{
		// Init libraries
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl-2.9.0/native/linux/").getAbsolutePath());
		
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
	
	private static void loadResources()
	{
		ResourceManager.getMap();
		
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		fonts.add(new TrueTypeFont(awtFont, false));
	}
	
	public static void cleanup()
	{
		ResourceManager.cleanup();
		Display.destroy();
	}
}

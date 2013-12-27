
package game.main;

import entities.Board;
import gui.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MainClient
{
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final int FRAMERATE = 60;
	private static final boolean FULL_SCREEN = false;
	private static final String WINDOW_TITLE = "Stratego";
	private static boolean EXIT_GAME = false;
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public static void main(String[] args)
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
		
		// Main Loop
		while (!Display.isCloseRequested() && !EXIT_GAME)
		{
			run();
		}
		
		// Game Exit
		cleanup();
		
		return;
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
		
		try
		{
			String str;
			Texture tex;
			
			str = "res/images/grid.png";
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(str));
			sprites.add(new Sprite(tex));
			/*
			 * str = "res/images/oneplayer.png";
			 * tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(str));
			 * sprites.add(new Sprite(tex));
			 */
		}
		catch (IOException e)
		{
			System.err.println("Failed to load resources");
			e.printStackTrace();
			cleanup();
			System.exit(1);
		}
	}
	
	private static void cleanup()
	{
		for (Sprite spr : sprites)
		{
			spr.getTexture().release();
		}
		Display.destroy();
		// System.exit(0);
	}
	
	private static void run()
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
			Display.sync(FRAMERATE);
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
	
	private static void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		// GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
		
		// Draw basic (Board, icons, etc ...)
		for (Sprite spr : sprites)
		{
			spr.draw(0, 0);
		}
		
		// Draw the pieces
		Board board = Board.getBoard();
	}
	
	private static void logic()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			EXIT_GAME = true;
		}
	}
}

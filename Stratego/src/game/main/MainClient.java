
package game.main;

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
	
	private static ArrayList<Texture> textures = new ArrayList<Texture>();
	
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
		
		// Enable Texturing
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		// Enable Blending to support transparency
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		// TODO Load resources
		try
		{
			String str;
			Texture tex;
			
			str = "res/images/b.png";
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(str));
			textures.add(tex);
			
			str = "res/images/s.png";
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(str));
			textures.add(tex);
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
		for (Texture tex : textures)
		{
			tex.release();
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
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures.get(1).getTextureID());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(0, 100);
			
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(100, 100);
			
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(100, 0);
		}
		GL11.glEnd();
	}
	
	private static void logic()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			EXIT_GAME = true;
		}
	}
}

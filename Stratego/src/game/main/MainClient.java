
package game.main;

import entities.Board;
import entities.Piece;
import entities.pieces.Bomb;
import gui.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MainClient
{
	private static final int WIDTH = 640;
	private static final int HEIGHT = 640;
	private static final int FRAMERATE = 60;
	private static final boolean FULL_SCREEN = false;
	private static final String WINDOW_TITLE = "Stratego";
	
	private static boolean EXIT_GAME = false;
	private static int selectedX = 0;
	private static int selectedY = 0;
	private static long lastFrame;
	
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
		getDelta();
		
		// TODO delete this
		Board board = Board.getBoard();
		for (int i = 0; i < 40; i++)
		{
			board.addPiece(new Bomb(true));
			board.addPiece(new Bomb(false));
		}
		
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
		// TODO Get the board from socket
		Board board = Board.getBoard();
		for (Piece piece : board)
		{
			piece.draw();
		}
		
		int x = (selectedX / (WIDTH / 10));
		int y = ((HEIGHT - selectedY) / (HEIGHT / 10));
		
		Texture tex = Sprite.getPieceSprite().getTexture();
		GL11.glPushMatrix();
		GL11.glTranslatef(x * tex.getImageWidth(), y * tex.getImageHeight(), 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4ub((byte) 0, (byte) 0, (byte) 255, (byte) 128);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0, tex.getHeight());
			GL11.glVertex2f(0, tex.getImageHeight());
			GL11.glTexCoord2f(tex.getWidth(), tex.getHeight());
			GL11.glVertex2f(tex.getImageWidth(), tex.getImageHeight());
			GL11.glTexCoord2f(tex.getWidth(), 0);
			GL11.glVertex2f(tex.getImageWidth(), 0);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	private static void logic()
	{
		int delta = getDelta();
		
		// Keyboard Events
		
		// Polled
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			EXIT_GAME = true;
		}
		//@formatter:off
		/**/
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			selectedX -= 0.25f * delta;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			selectedX += 0.25f * delta;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			selectedY += 0.25f * delta;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			selectedY -= 0.25f * delta;
		}
		/**/
		//@formatter:on
		// Queued
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				// Pressed
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					EXIT_GAME = true;
				}
				//@formatter:off
				/*
				if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
				{
					selectedX += 1;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_LEFT)
				{
					selectedX -= 1;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					selectedY += 1;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					selectedY -= 1;
				}
				*/
				//@formatter:on
			}
			else
			{
				// Released
			}
		}
		
		// Mouse Events
		
		// Polled
		if (Mouse.isButtonDown(0))
		{
			selectedX = Mouse.getX();
			selectedY = Mouse.getY();
		}
		// Queued
		while (Mouse.next())
		{
			if (Mouse.getEventButtonState())
			{
				// Pressed
				if (Mouse.getEventButton() == 0)
				{
					selectedX = Mouse.getX();
					selectedY = Mouse.getY();
				}
			}
			else
			{
				// Released
			}
		}
		
		// Verifications
		
		if (selectedX < 0)
			selectedX = 0 + 1;
		if (selectedX > WIDTH)
			selectedX = WIDTH - 1;
		if (selectedY < 0)
			selectedY = 0 + 1;
		if (selectedY > HEIGHT)
			selectedY = HEIGHT - 1;
	}
}


package gui.states;

import entities.Board;
import entities.Piece;
import gui.GUI;
import gui.Sprite;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class InGame
{
	private static int activeX = 1;
	private static int activeY = 1;
	private static int selectedX = 0;
	private static int selectedY = 0;
	private static byte flashAlpha = 96; // (32 - 96)
	private static byte flashdA = 5;
	private static boolean selectedAlphaStatus = false;
	
	public static void run()
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
	
	private static void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		// GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
		
		// Draw basic (Board, icons, etc ...)
		for (Sprite spr : GUI.sprites)
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
		
		if (selectedAlphaStatus)
		{
			flashAlpha += flashdA;
			if (flashAlpha > 96)
			{
				flashAlpha = 96;
				selectedAlphaStatus = !selectedAlphaStatus;
			}
		}
		else
		{
			flashAlpha -= flashdA;
			if (flashAlpha < 32)
			{
				flashAlpha = 32;
				selectedAlphaStatus = !selectedAlphaStatus;
			}
		}
		
		int x;
		int y;
		Texture tex = Sprite.getPieceSprite().getTexture();
		// Active Piece
		x = (activeX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - activeY) / (GUI.HEIGHT / Board.SIZE));
		
		GL11.glPushMatrix();
		GL11.glTranslatef(x * tex.getImageWidth(), y * tex.getImageHeight(), 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4ub((byte) 0, (byte) 0, (byte) 255, (byte) flashAlpha);
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
		// Selected Piece
		x = (selectedX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - selectedY) / (GUI.HEIGHT / Board.SIZE));
		
		GL11.glPushMatrix();
		GL11.glTranslatef(x * tex.getImageWidth(), y * tex.getImageHeight(), 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4ub((byte) 255, (byte) 0, (byte) 0, (byte) flashAlpha);
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
		@SuppressWarnings("unused")
		int delta = GUI.getDelta();
		
		// Keyboard Events
		
		// Polled
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			GUI.EXIT_GAME = true;
		}
		//@formatter:off
		/*
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
		*/
		//@formatter:on
		// Queued
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				// Pressed
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					GUI.EXIT_GAME = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == Keyboard.KEY_SPACE)
				{
					if (selectedX == 0 && selectedY == 0)
					{
						selectedX = activeX;
						selectedY = activeY;
					}
					else
					{
						// TODO Movement event handler
						selectedX = 0;
						selectedY = 0;
					}
				}
				//@formatter:off
				/**/
				if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
				{
					activeX += Sprite.getPieceSprite().getWidth();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_LEFT)
				{
					activeX -= Sprite.getPieceSprite().getWidth();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					activeY -= Sprite.getPieceSprite().getHeight();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					activeY += Sprite.getPieceSprite().getHeight();
				}
				/**/
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
			activeX = Mouse.getX();
			activeY = Mouse.getY();
		}
		// Queued
		while (Mouse.next())
		{
			if (Mouse.getEventButtonState())
			{
				// Pressed
				if (Mouse.getEventButton() == 0)
				{
					activeX = Mouse.getX();
					activeY = Mouse.getY();
				}
				if (Mouse.getEventButton() == 1)
				{
					if (selectedX == 0 && selectedY == 0)
					{
						activeX = Mouse.getX();
						activeY = Mouse.getY();
						
						selectedX = Mouse.getX();
						selectedY = Mouse.getY();
					}
					else
					{
						// TODO Movement event handler
						selectedX = 0;
						selectedY = 0;
					}
				}
			}
			else
			{
				// Released
			}
		}
		
		// Verifications
		
		if (activeX < 0)
			activeX = 0 + 1;
		if (activeX > GUI.WIDTH)
			activeX = GUI.WIDTH - 1;
		if (activeY < 0)
			activeY = 0 + 1;
		if (activeY > GUI.HEIGHT)
			activeY = GUI.HEIGHT - 1;
	}
}


package gui.states;

import java.awt.Rectangle;

import entities.Board;
import entities.Piece;
import gui.GUI;
import gui.ResourceManager;
import gui.Sprite;
import gui.util.DrawUtil;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public final class InGame extends GameState
{
	private static volatile GameState INSTANCE = new InGame();
	
	private int activeX = 1;
	private int activeY = 1;
	private int selectedX = 0;
	private int selectedY = 0;
	
	@Override
	protected void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		// Draw basic (Board, icons, etc ...)
		ResourceManager.getSpriteMap().get(ResourceManager.BOARD).draw(0, 0);
		
		// Draw the pieces
		// TODO Get the board from socket
		Board board = Board.getInstance();
		for (Piece piece : board)
		{
			piece.draw();
		}
		
		int x;
		int y;
		Texture tex = Sprite.getPieceSprite().getTexture();
		// Active Piece
		x = (activeX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - activeY) / (GUI.HEIGHT / Board.SIZE));
		
		DrawUtil.flashAlpha();
		DrawUtil.drawFlashRectangle(
				new Rectangle(x * tex.getImageWidth(), y * tex.getImageWidth(), tex.getImageWidth(), tex.getImageHeight()),
				(byte) 0, (byte) 0, (byte) 255);
		
		// Selected Piece
		x = (selectedX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - selectedY) / (GUI.HEIGHT / Board.SIZE));
		DrawUtil.drawFlashRectangle(
				new Rectangle(x * tex.getImageWidth(), y * tex.getImageWidth(), tex.getImageWidth(), tex.getImageHeight()),
				(byte) 255, (byte) 0, (byte) 0);
	}
	
	@Override
	protected void logic()
	{
		// Keyboard
		
		// Keyboard Events
		
		// Polled
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			// GameStates.SetState(GameStates.PAUSEMENU);
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
					GameStates.SetState(GameStates.PAUSEMENU);
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
		
		// Mouse
		
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
	
	private InGame()
	{
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}

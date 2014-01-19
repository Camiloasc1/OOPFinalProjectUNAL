/*******************************************************************************
 * File: InGame.java
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

import entities.Board;
import entities.Piece;
import game.main.MainClient;
import gui.GUI;
import gui.ResourceManager;
import gui.Sprite;
import gui.util.DrawUtil;
import net.Action;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * InGame state for Stratego game
 * 
 * @author camiloasc1
 * 
 */
public final class InGame extends GameState
{
	/**
	 * Singleton Instance
	 */
	private static volatile GameState INSTANCE = new InGame();
	
	/**
	 * X coordinate of active Piece
	 */
	private int activeX = 1;
	/**
	 * Y coordinate of active Piece
	 */
	private int activeY = 1;
	/**
	 * X coordinate of selected Piece
	 */
	private int selectedX = 0;
	/**
	 * Y coordinate of selected Piece
	 */
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
		Board board = Board.getInstance();
		for (Piece piece : board)
		{
			piece.draw();
		}
		
		int x;
		int y;
		Sprite spr = Sprite.getPieceSprite();
		// Active Piece
		x = (activeX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - activeY) / (GUI.HEIGHT / Board.SIZE));
		
		DrawUtil.flashAlpha();
		DrawUtil.drawFlashRectangle(spr.getRectangle(x, y), (byte) 0, (byte) 0, (byte) 255);
		
		// Selected Piece
		x = (selectedX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - selectedY) / (GUI.HEIGHT / Board.SIZE));
		DrawUtil.drawFlashRectangle(spr.getRectangle(x, y), (byte) 255, (byte) 0, (byte) 0);
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
		// Queued
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				// Pressed
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					// GameStates.SetState(GameStates.PAUSEMENU);
				}
				if ((Keyboard.getEventKey() == Keyboard.KEY_RETURN) || (Keyboard.getEventKey() == Keyboard.KEY_SPACE))
				{
					if ((selectedX == 0) && (selectedY == 0))
					{
						selectedX = activeX;
						selectedY = activeY;
					}
					else
					{
						moveHandler();
						selectedX = 0;
						selectedY = 0;
					}
				}
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
					if ((selectedX == 0) && (selectedY == 0))
					{
						activeX = Mouse.getX();
						activeY = Mouse.getY();
						
						selectedX = Mouse.getX();
						selectedY = Mouse.getY();
					}
					else
					{
						activeX = Mouse.getX();
						activeY = Mouse.getY();
						moveHandler();
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
		{
			activeX = 0 + 1;
		}
		if (activeX > GUI.WIDTH)
		{
			activeX = GUI.WIDTH - 1;
		}
		if (activeY < 0)
		{
			activeY = 0 + 1;
		}
		if (activeY > GUI.HEIGHT)
		{
			activeY = GUI.HEIGHT - 1;
		}
	}
	
	/**
	 * 
	 */
	private void moveHandler()
	{
		int x;
		int y;
		int xPiece;
		int yPiece;
		// Destination
		x = (activeX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - activeY) / (GUI.HEIGHT / Board.SIZE));
		// Selected Piece
		xPiece = (selectedX / (GUI.WIDTH / Board.SIZE));
		yPiece = ((GUI.HEIGHT - selectedY) / (GUI.HEIGHT / Board.SIZE));
		
		MainClient.getClientThread().getSocketClient().writeObject(new Action(xPiece, yPiece, x, y));
	}
	
	/**
	 * Singleton Constructor
	 */
	private InGame()
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

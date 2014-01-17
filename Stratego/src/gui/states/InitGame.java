/*******************************************************************************
 * File: InitGame.java
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
import entities.pieces.Bomb;
import entities.pieces.Captain;
import entities.pieces.Colonel;
import entities.pieces.Flag;
import entities.pieces.General;
import entities.pieces.Lieutenant;
import entities.pieces.Major;
import entities.pieces.Marshal;
import entities.pieces.Miner;
import entities.pieces.Scout;
import entities.pieces.Sergeant;
import entities.pieces.Spy;
import gui.GUI;
import gui.ResourceManager;
import gui.Sprite;
import gui.util.DisplayMessage;
import gui.util.DrawUtil;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * InitGame state for Stratego game
 * 
 * @author camiloasc1
 * 
 */
public final class InitGame extends GameState
{
	/**
	 * Singleton Instance
	 */
	private static volatile GameState INSTANCE = new InitGame();
	
	/**
	 * Number of Bombs
	 */
	private static final byte NUMBOMB = 6;
	/**
	 * Number of Captains
	 */
	private static final int NUMCAPTAIN = 4;
	/**
	 * Number of Colonels
	 */
	private static final int NUMCOLONEL = 2;
	/**
	 * Number of Flags
	 */
	private static final int NUMFLAG = 1;
	/**
	 * Number of Generals
	 */
	private static final int NUMGENERAL = 1;
	/**
	 * Number of Lieutenants
	 */
	private static final int NUMLIEUTENANT = 4;
	/**
	 * Number of Majors
	 */
	private static final int NUMMAJOR = 3;
	/**
	 * Number of Marshals
	 */
	private static final int NUMMARSHAL = 1;
	/**
	 * Number of Miners
	 */
	private static final int NUMMINER = 5;
	/**
	 * Number of Scouts
	 */
	private static final int NUMSCOUT = 8;
	/**
	 * Number of Sergeants
	 */
	private static final int NUMSERGEANT = 4;
	/**
	 * Number of Spies
	 */
	private static final int NUMSPY = 1;
	
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
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		// 3D
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		
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
		DrawUtil.flashAlpha();
		
		// Active Piece
		x = (activeX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - activeY) / (GUI.HEIGHT / Board.SIZE));
		
		DrawUtil.drawFlashRectangle(spr.getRectangle(x, y), (byte) 0, (byte) 0, (byte) 255);
		
		// Selected Piece
		x = (selectedX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - selectedY) / (GUI.HEIGHT / Board.SIZE));
		DrawUtil.drawFlashRectangle(spr.getRectangle(x, y), (byte) 255, (byte) 0, (byte) 0);
		
		// Void Spaces
		for (x = 0; x < Board.SIZE; x++)
		{
			for (y = 6; y < Board.SIZE; y++)
			{
				if (board.isEmptyPos(x, y))
				{
					DrawUtil.drawFlashRectangle(spr.getRectangle(x, y), (byte) 0, (byte) 255, (byte) 0);
				}
			}
		}
		
		// int pos;
		// pos = GUI.HEIGHT;
		// pos -= ResourceManager.getFontMap().get(ResourceManager.FONTMENU1).getLineHeight();
		// pos /= 2;
		
		// GL11.glDisable(GL11.GL_DEPTH_TEST);
		// GL11.glDisable(GL11.GL_LIGHTING);
		// GL11.glPushMatrix();
		// GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		// GL11.glDisable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glDisable(GL11.GL_BLEND);
		// GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
		
		// ResourceManager.getFontMap().get(ResourceManager.FONTBOARDMSG).drawString(10, pos, "Coloca tus piezas en la parte inferior");
		
		// GL11.glEnable(GL11.GL_BLEND);
		// GL11.glDisable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glPopMatrix();
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
		checkAllPiecesSorted();
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
		Board board = Board.getInstance();
		// Destination
		x = (activeX / (GUI.WIDTH / Board.SIZE));
		y = ((GUI.HEIGHT - activeY) / (GUI.HEIGHT / Board.SIZE));
		// Selected Piece
		xPiece = (selectedX / (GUI.WIDTH / Board.SIZE));
		yPiece = ((GUI.HEIGHT - selectedY) / (GUI.HEIGHT / Board.SIZE));
		// is valid move
		if (y >= 6)
		{
			board.setPiecePos(board.getPieceAt(xPiece, yPiece), x, y);
		}
	}
	
	/**
	 * Put the player Pieces on the Board
	 */
	private void initPieces()
	{
		Board board = Board.getInstance();
		for (int i = 0; i < NUMBOMB; i++)
		{
			board.addPiece(new Bomb(true));
		}
		for (int i = 0; i < NUMCAPTAIN; i++)
		{
			board.addPiece(new Captain(true));
		}
		for (int i = 0; i < NUMCOLONEL; i++)
		{
			board.addPiece(new Colonel(true));
		}
		for (int i = 0; i < NUMFLAG; i++)
		{
			board.addPiece(new Flag(true));
		}
		for (int i = 0; i < NUMGENERAL; i++)
		{
			board.addPiece(new General(true));
		}
		for (int i = 0; i < NUMLIEUTENANT; i++)
		{
			board.addPiece(new Lieutenant(true));
		}
		for (int i = 0; i < NUMMAJOR; i++)
		{
			board.addPiece(new Major(true));
		}
		for (int i = 0; i < NUMMARSHAL; i++)
		{
			board.addPiece(new Marshal(true));
		}
		for (int i = 0; i < NUMMINER; i++)
		{
			board.addPiece(new Miner(true));
		}
		for (int i = 0; i < NUMSCOUT; i++)
		{
			board.addPiece(new Scout(true));
		}
		for (int i = 0; i < NUMSERGEANT; i++)
		{
			board.addPiece(new Sergeant(true));
		}
		for (int i = 0; i < NUMSPY; i++)
		{
			board.addPiece(new Spy(true));
		}
		//@formatter:off
		/*
		for (int i = 0; i < NUMBOMB; i++)
		{
			board.addPiece(new Bomb(false));
		}
		for (int i = 0; i < NUMCAPTAIN; i++)
		{
			board.addPiece(new Captain(false));
		}
		for (int i = 0; i < NUMCOLONEL; i++)
		{
			board.addPiece(new Colonel(false));
		}
		for (int i = 0; i < NUMFLAG; i++)
		{
			board.addPiece(new Flag(false));
		}
		for (int i = 0; i < NUMGENERAL; i++)
		{
			board.addPiece(new General(false));
		}
		for (int i = 0; i < NUMLIEUTERNANT; i++)
		{
			board.addPiece(new Lieutenant(false));
		}
		for (int i = 0; i < NUMMAJOR; i++)
		{
			board.addPiece(new Major(false));
		}
		for (int i = 0; i < NUMMARSHAL; i++)
		{
			board.addPiece(new Marshal(false));
		}
		for (int i = 0; i < NUMMINER; i++)
		{
			board.addPiece(new Miner(false));
		}
		for (int i = 0; i < NUMSCOUT; i++)
		{
			board.addPiece(new Scout(false));
		}
		for (int i = 0; i < NUMSERGEANT; i++)
		{
			board.addPiece(new Sergeant(false));
		}
		for (int i = 0; i < NUMSPY; i++)
		{
			board.addPiece(new Spy(false));
		}
		*/
		//@formatter:on
	}
	
	/**
	 * Check if all pieces was sorted by the player and the ready state of the other player
	 */
	private void checkAllPiecesSorted()
	{
		Board board = Board.getInstance();
		for (Piece piece : board)
		{
			if (board.getPieceY(piece) < 6)
				return;
		}
		// TODO wait for other player
		DisplayMessage.show(true, "Esperando por el otro jugador");
		GameStates.SetState(GameStates.INGAME);
	}
	
	/**
	 * Singleton Constructor
	 */
	private InitGame()
	{
		initPieces();
	}
	
	/**
	 * @return the Singleton Instance
	 */
	public static synchronized GameState getInstance()
	{
		return INSTANCE;
	}
}

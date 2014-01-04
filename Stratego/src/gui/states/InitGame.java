
package gui.states;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

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
import gui.util.DrawUtil;

public final class InitGame extends GameState
{
	private static volatile GameState INSTANCE = new InitGame();
	
	private static final byte NUMBOMB = 6;
	private static final byte NUMCAPTAIN = 4;
	private static final byte NUMCOLONEL = 2;
	private static final byte NUMFLAG = 1;
	private static final byte NUMGENERAL = 1;
	private static final byte NUMLIEUTERNANT = 4;
	private static final byte NUMMAJOR = 3;
	private static final byte NUMMARSHAL = 1;
	private static final byte NUMMINER = 5;
	private static final byte NUMSCOUT = 8;
	private static final byte NUMSERGEANT = 4;
	private static final byte NUMSPY = 1;
	
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
	
	private static void initPieces()
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
		for (int i = 0; i < NUMLIEUTERNANT; i++)
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
	
	private InitGame()
	{
		initPieces();
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}

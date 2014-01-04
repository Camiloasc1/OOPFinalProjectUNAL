
package gui.states;

import java.awt.Rectangle;
import java.util.ArrayList;

import gui.GUI;
import gui.ResourceManager;
import gui.util.DrawUtil;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public final class MainMenu extends GameState
{
	private static volatile GameState INSTANCE = new MainMenu();
	
	private static byte activeMenu = 0;
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	@Override
	protected void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		int pos;
		
		// Title
		pos = GUI.WIDTH;
		pos -= ResourceManager.getFontMap().get(ResourceManager.FONT1).getWidth("Menu Principal");
		pos /= 2;
		ResourceManager.getFontMap().get(ResourceManager.FONT1).drawString(pos, 100, "Menu Principal");
		
		// Menus whit icons
		pos = 0;
		ResourceManager.getSpriteMap().get(ResourceManager.NEWGAME).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONT2).drawString(100, rectangles.get(pos).y, "Jugar");
		
		pos++;
		ResourceManager.getSpriteMap().get(ResourceManager.LOADGAME).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONT3).drawString(100, rectangles.get(pos).y, "Cargar Juego");
		
		pos++;
		ResourceManager.getSpriteMap().get(ResourceManager.EXIT).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONT4).drawString(100, rectangles.get(pos).y, "Salir");
		
		// Selected Menu
		
		DrawUtil.flashAlpha();
		DrawUtil.drawFlashRectangle(rectangles.get(activeMenu));
	}
	
	@Override
	protected void logic()
	{
		// Keyboard
		
		// Keyboard Events
		
		// Polled
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			// GameStates.activateExitFlag();
		}
		// Queued
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				// Pressed
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					GameStates.activateExitFlag();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == Keyboard.KEY_SPACE)
				{
					selectMenu();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					activeMenu--;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					activeMenu++;
				}
			}
			else
			{
				// Released
			}
		}
		
		// Mouse
		
		int x = Mouse.getX();
		int y = GUI.HEIGHT - Mouse.getY();
		
		for (Rectangle rtg : rectangles)
		{
			if (rtg.contains(x, y))
			{
				activeMenu = (byte) ((y - 200) / 100);
			}
		}
		
		// Mouse Events
		
		// Polled
		if (Mouse.isButtonDown(0))
		{
			for (Rectangle rtg : rectangles)
			{
				if (rtg.contains(x, y))
				{
					selectMenu();
				}
			}
		}
		// Queued
		while (Mouse.next())
		{
			if (Mouse.getEventButtonState())
			{
				// Pressed
				if (Mouse.getEventButton() == 0)
				{
					for (Rectangle rtg : rectangles)
					{
						if (rtg.contains(x, y))
						{
							selectMenu();
						}
					}
				}
			}
			else
			{
				// Released
			}
		}
		
		// Verifications
		
		if (activeMenu < 0)
			activeMenu = 0;
		if (activeMenu >= rectangles.size())
			activeMenu = (byte) rectangles.size();
	}
	
	private void selectMenu()
	{
		switch (activeMenu)
		{
			case 0:
			{
				GameStates.SetState(GameStates.SETUPGAME);
				break;
			}
			case 1:
			{
				GameStates.SetState(GameStates.LOADGAME);
				break;
			}
			case 2:
			{
				GameStates.activateExitFlag();
				break;
			}
		}
	}
	
	private MainMenu()
	{
		int y = 200;
		int height = ResourceManager.getFontMap().get(ResourceManager.FONT2).getLineHeight();
		
		rectangles.add(new Rectangle(50, y, 300, height));
		y += 100;
		rectangles.add(new Rectangle(50, y, 300, height));
		y += 100;
		rectangles.add(new Rectangle(50, y, 300, height));
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}

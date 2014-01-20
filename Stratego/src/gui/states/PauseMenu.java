/*******************************************************************************
 * File: PauseMenu.java
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

import gui.GUI;
import gui.ResourceManager;
import gui.util.DrawUtil;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * PauseMenu state for Stratego game
 * 
 * @author camiloasc1
 * 
 */
public final class PauseMenu extends GameState
{
	/**
	 * Singleton Instance
	 */
	private static volatile GameState INSTANCE = new PauseMenu();
	
	/**
	 * The active menu
	 */
	private static byte activeMenu = 0;
	/**
	 * Rectangles of menus
	 */
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
		pos -= ResourceManager.getFontMap().get(ResourceManager.FONTMENU1).getWidth("Pausa");
		pos /= 2;
		ResourceManager.getFontMap().get(ResourceManager.FONTMENU1).drawString(pos, 100, "Pausa");
		
		// Menus whit icons
		pos = 0;
		ResourceManager.getSpriteMap().get(ResourceManager.PLAYER).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONTMENU2).drawString(100, rectangles.get(pos).y, "Continuar");
		
		pos++;
		ResourceManager.getSpriteMap().get(ResourceManager.DRAW).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONTMENU3).drawString(100, rectangles.get(pos).y, "Declarar Empate");
		
		pos++;
		ResourceManager.getSpriteMap().get(ResourceManager.SAVEGAME).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONTMENU4).drawString(100, rectangles.get(pos).y, "Guardar");
		
		pos++;
		ResourceManager.getSpriteMap().get(ResourceManager.EXIT).draw(50, rectangles.get(pos).y);
		ResourceManager.getFontMap().get(ResourceManager.FONTMENU5).drawString(100, rectangles.get(pos).y, "Menu Principal");
		
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
			// GameStates.SetState(GameStates.INGAME);
		}
		// Queued
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				// Pressed
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					GameStates.SetState(GameStates.INGAME);
				}
				if ((Keyboard.getEventKey() == Keyboard.KEY_RETURN) || (Keyboard.getEventKey() == Keyboard.KEY_SPACE))
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
		{
			activeMenu = 0;
		}
		if (activeMenu >= rectangles.size())
		{
			activeMenu = (byte) rectangles.size();
		}
	}
	
	/**
	 * Select the active menu
	 */
	private void selectMenu()
	{
		switch (activeMenu)
		{
			case 0:
			{
				GameStates.SetState(GameStates.INGAME);
				break;
			}
			case 1:
			{
				// TODO Add draw handler
				break;
			}
			case 2:
			{
				GameStates.SetState(GameStates.SAVEGAME);
				break;
			}
			case 3:
			{
				GameStates.SetState(GameStates.MAINMENU);
				// GameStates.activateExitFlag();
				break;
			}
		}
	}
	
	/**
	 * Singleton Constructor
	 */
	private PauseMenu()
	{
		int y = 200;
		int height = ResourceManager.getFontMap().get(ResourceManager.FONTMENU2).getLineHeight();
		
		rectangles.add(new Rectangle(50, y, 300, height));
		y += 100;
		rectangles.add(new Rectangle(50, y, 300, height));
		y += 100;
		rectangles.add(new Rectangle(50, y, 300, height));
		y += 100;
		rectangles.add(new Rectangle(50, y, 300, height));
	}
	
	/**
	 * @return the Singleton Instance
	 */
	public static synchronized GameState getInstance()
	{
		return INSTANCE;
	}
}

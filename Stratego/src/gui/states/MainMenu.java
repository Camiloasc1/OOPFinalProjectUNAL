
package gui.states;

import gui.GUI;
import gui.ResourceManager;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public final class MainMenu extends GameState
{
	private static volatile GameState INSTANCE = new MainMenu();
	
	private byte activeMenu = 0;
	
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
		pos = 200;
		ResourceManager.getSpriteMap().get(ResourceManager.NEWGAME).draw(50, pos);
		ResourceManager.getFontMap().get(ResourceManager.FONT2).drawString(100, pos, "Jugar");

		pos += 100;
		ResourceManager.getSpriteMap().get(ResourceManager.LOADGAME).draw(50, pos);
		ResourceManager.getFontMap().get(ResourceManager.FONT3).drawString(100, pos, "Cargar Juego");

		pos += 100;
		ResourceManager.getSpriteMap().get(ResourceManager.EXIT).draw(50, pos);
		ResourceManager.getFontMap().get(ResourceManager.FONT4).drawString(100, pos, "Salir");
	}
	
	@Override
	protected void logic()
	{
		// Keyboard Events
		
		// Polled
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			GameStates.activateExitFlag();
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
					switch (activeMenu)
					{
					
					}
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
		
		// Mouse Events
		
		// Polled
		if (Mouse.isButtonDown(0))
		{
			// TODO real calc
			activeMenu = (byte) Mouse.getY();
		}
		// Queued
		while (Mouse.next())
		{
			if (Mouse.getEventButtonState())
			{
				// Pressed
				if (Mouse.getEventButton() == 0)
				{
					activeMenu = (byte) Mouse.getY();
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
		if (activeMenu >= 2)
			activeMenu = 2;
	}
	
	private MainMenu()
	{
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
	
}

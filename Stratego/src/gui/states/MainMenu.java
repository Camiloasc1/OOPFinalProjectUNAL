
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
	private byte flashAlpha = 96; // (32 - 96)
	private byte flashdA = 5;
	private boolean selectedAlphaStatus = false;
	
	@Override
	protected void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		int pos;
		int height;
		int width;
		
		height = ResourceManager.getFontMap().get(ResourceManager.FONT1).getLineHeight();
		width = 300;
		
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
		
		// Selected Menu
		
		pos = 200;
		pos += activeMenu * 100;
		
		flashAlpha();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(100, pos, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) flashAlpha);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(0, height);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(width, height);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(width, 0);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	@Override
	protected void logic()
	{
		// Keyboard
		
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
		
		if ((x > 0 && x < 400) && (y > 200 && y < 500))
		{
			activeMenu = (byte) ((y - 200) / 100);
		}
		
		// Mouse Events
		
		// Polled
		if (Mouse.isButtonDown(0))
		{
			selectMenu();
		}
		// Queued
		while (Mouse.next())
		{
			if (Mouse.getEventButtonState())
			{
				// Pressed
				if (Mouse.getEventButton() == 0)
				{
					selectMenu();
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
	
	private void flashAlpha()
	{
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
}

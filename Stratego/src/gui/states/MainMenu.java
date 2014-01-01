
package gui.states;

import gui.GUI;

import org.lwjgl.opengl.GL11;

public final class MainMenu extends GameState
{
	private static GameState INSTANCE = new MainMenu();
	
	@Override
	protected void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		GUI.fonts.get(0).drawString(50, 50, "Menu Principal");
	}
	
	@Override
	protected void logic()
	{
		// TODO Auto-generated method stub
		
	}
	
	private MainMenu()
	{
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
	
}

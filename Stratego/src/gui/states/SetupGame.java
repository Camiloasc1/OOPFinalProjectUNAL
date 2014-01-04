
package gui.states;

import org.lwjgl.opengl.GL11;

public final class SetupGame extends GameState
{
	private static volatile GameState INSTANCE = new SetupGame();
	
	@Override
	protected void render()
	{
		// 2D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		// 3D
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	@Override
	protected void logic()
	{
		// TODO Auto-generated method stub
	}
	
	private SetupGame()
	{
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}

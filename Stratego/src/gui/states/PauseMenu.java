
package gui.states;

public final class PauseMenu extends GameState
{
	private static volatile GameState INSTANCE = new PauseMenu();
	
	@Override
	protected void render()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void logic()
	{
		// TODO Auto-generated method stub
		
	}
	
	private PauseMenu()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}

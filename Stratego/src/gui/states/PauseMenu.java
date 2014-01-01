
package gui.states;

public final class PauseMenu extends GameState
{
	private static GameState INSTANCE = new PauseMenu();
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
	
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

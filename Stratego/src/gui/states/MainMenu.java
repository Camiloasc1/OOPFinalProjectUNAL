
package gui.states;

public final class MainMenu extends GameState
{
	private static GameState INSTANCE = new MainMenu();
	
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
	
	private MainMenu()
	{
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
	
}

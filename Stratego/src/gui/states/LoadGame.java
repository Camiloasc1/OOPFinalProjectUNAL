
package gui.states;

public final class LoadGame extends GameState
{
	private static volatile GameState INSTANCE = new LoadGame();
	
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
	
	private LoadGame()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}


package gui.states;

public final class InitGame extends GameState
{
	private static volatile GameState INSTANCE = new InitGame();
	
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
	
	private InitGame()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}


package gui.states;

public final class Setup extends GameState
{
	private static volatile GameState INSTANCE = new Setup();
	
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
	
	private Setup()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}

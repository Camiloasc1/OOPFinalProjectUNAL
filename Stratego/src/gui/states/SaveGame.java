
package gui.states;

public final class SaveGame extends GameState
{
	private static volatile GameState INSTANCE = new SaveGame();
	
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
	
	private SaveGame()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static GameState getInstance()
	{
		return INSTANCE;
	}
}


package gui.states;

public abstract class GameState implements Cloneable
{
	public abstract void run();
	
	protected abstract void render();
	
	protected abstract void logic();
	
	// private static GameState INSTANCE = new State();
	// private State();
	// public static GameState getInstance();
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}

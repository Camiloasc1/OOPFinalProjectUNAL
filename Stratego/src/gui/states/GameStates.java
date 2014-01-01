
package gui.states;

public final class GameStates
{
	public static final byte EXITGAME = -1;
	public static final byte MAINMENU = 0;
	public static final byte PAUSEMENU = 1;
	public static final byte SETUP = 2;
	public static final byte INITGAME = 3;
	public static final byte INGAME = 4;
	
	/**
	 * Actual game state
	 */
	private static byte state = 0;
	
	/**
	 * 
	 */
	private GameStates()
	{
		super();
		// this.state = 0;
	}
	
	/**
	 * @return the state
	 */
	public static synchronized byte getState()
	{
		return state;
	}
	
	/**
	 * @param state
	 * @return is successful set
	 * @throws ExitGameFlag
	 *             not allowed to set the EXITGAME status
	 */
	public static synchronized boolean setState(byte state)
	{
		if (state == GameStates.EXITGAME)
		{
			return false;
		}
		// this.state = state;
		GameStates.state = state;
		return true;
	}
	
	/**
	 * @return is EXITGAME state set
	 */
	public static synchronized boolean getExitFlag()
	{
		return (state == GameStates.EXITGAME);
	}
	
	/**
	 * @param actives
	 *            the EXITGAME state
	 */
	public static synchronized void activateExitFlag()
	{
		GameStates.state = GameStates.EXITGAME;
	}
	
}

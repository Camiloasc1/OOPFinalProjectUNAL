/**
 * 
 */

package gui.states;

/**
 * @author camiloasc1
 * 
 */
public enum GameStates
{
	EXITGAME, MAINMENU, PAUSEMENU, SETUP, INITGAME, INGAME;
	
	private static GameStates INSTANCE = MAINMENU;
	
	public void activateExitFlag()
	{
		INSTANCE = EXITGAME;
	}
	
	public boolean isExitFlag()
	{
		return (this == EXITGAME);
	}
	
	public static GameStates getInstance()
	{
		return INSTANCE;
	}
}

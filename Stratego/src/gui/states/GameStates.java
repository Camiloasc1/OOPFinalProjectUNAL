
package gui.states;

/**
 * @author camiloasc1
 * 
 */
public enum GameStates
{
	EXITGAME, MAINMENU, PAUSEMENU, SETUPGAME, LOADGAME, SAVEGAME, INITGAME, INGAME;
	
	private static volatile GameStates INSTANCE = MAINMENU;
	
	public static void activateExitFlag()
	{
		INSTANCE = EXITGAME;
	}
	
	public static boolean isExitFlag()
	{
		return (INSTANCE == EXITGAME);
	}
	
	public static synchronized GameStates getInstance()
	{
		return INSTANCE;
	}
	
	public static synchronized void SetState(GameStates state)
	{
		if (state == EXITGAME)
		{
			return;
		}
		INSTANCE = state;
	}
}

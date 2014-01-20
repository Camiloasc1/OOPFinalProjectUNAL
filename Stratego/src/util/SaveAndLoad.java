/**
 * File: SaveAndLoad.java
 * Package: Stratego.util.SaveAndLoad
 * Creation: 20/01/2014 at 07:59:30
 */

package util;

import entities.Board;

public final class SaveAndLoad
{
	/**
	 * 
	 */
	private static final String FILE = "savegames/stratego.svg";
	
	/**
	 * Load pieces into board, from save game
	 * 
	 * @param action
	 *            action contains name file to load
	 */
	public static void loadGame()
	{
		Object obj = FileSerializator.deserializeObject(FILE);
		
		if (obj != null)
		{
			if (obj instanceof Board)
			{
				Board board = (Board) obj;
				Board.setInstance(board.clone());
			}
		}
	}
	
	/**
	 * Save game in a file into folder
	 * <p>
	 * file named year month day hour minute seconds
	 * 
	 */
	public static void saveGame()
	{
		FileSerializator.serializeObject(Board.getInstance(), FILE);
		
	}
}

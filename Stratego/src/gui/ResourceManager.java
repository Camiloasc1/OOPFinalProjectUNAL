
package gui;

import java.io.IOException;
import java.util.EnumMap;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * @author camiloasc1
 * 
 */
public enum ResourceManager
{
	BOARD, PIECE, BOMB, CAPTAIN, COLONEL, FLAG, GENERAL, LIEUTENTANT, MAJOR, MARSHAL, MINER, SCOUT, SERGEANT, SPY;
	
	/**
	 * Need differed init or differed populate
	 */
	private static volatile EnumMap<ResourceManager, Sprite> MAP = new EnumMap<ResourceManager, Sprite>(ResourceManager.class);
	
	/**
	 * @return the EnumMap<ResourceManager, Sprite>
	 */
	public static synchronized final EnumMap<ResourceManager, Sprite> getMap()
	{
		if (MAP.isEmpty())
		{
			populateMap();
		}
		return MAP;
	}
	
	private static synchronized final void populateMap()
	{
		if (!MAP.isEmpty())
		{
			return;
		}
		
		// TODO Load Resources
		
		loadResource(BOARD, "res/images/grid.png");
		loadResource(PIECE, "res/images/piece.png");
		loadResource(BOMB, "res/images/b.png");
		loadResource(CAPTAIN, "res/images/piece.png");
		loadResource(COLONEL, "res/images/piece.png");
		loadResource(FLAG, "res/images/f.png");
		loadResource(GENERAL, "res/images/piece.png");
		loadResource(LIEUTENTANT, "res/images/piece.png");
		loadResource(MAJOR, "res/images/piece.png");
		loadResource(MARSHAL, "res/images/piece.png");
		loadResource(MINER, "res/images/piece.png");
		loadResource(SCOUT, "res/images/piece.png");
		loadResource(SERGEANT, "res/images/piece.png");
		loadResource(SPY, "res/images/s.png");
	}
	
	private static synchronized final void loadResource(ResourceManager key, String file)
	{
		try
		{
			MAP.put(key, new Sprite(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(file))));
		}
		catch (IOException e)
		{
			System.err.println("Failed to load resources");
			// e.printStackTrace();
			GUI.cleanup();
			System.exit(1);
		}
	}
	
	public static synchronized final void cleanup()
	{
		if (MAP.isEmpty())
		{
			return;
		}
		
		for (Sprite spr : ResourceManager.getMap().values())
		{
			spr.getTexture().release();
		}
		
		MAP.clear();
	}
}

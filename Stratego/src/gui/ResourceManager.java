
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
		loadResource(CAPTAIN, "res/images/5.png");
		loadResource(COLONEL, "res/images/3.png");
		loadResource(FLAG, "res/images/f.png");
		loadResource(GENERAL, "res/images/2.png");
		loadResource(LIEUTENTANT, "res/images/6.png");
		loadResource(MAJOR, "res/images/4.png");
		loadResource(MARSHAL, "res/images/1.png");
		loadResource(MINER, "res/images/8.png");
		loadResource(SCOUT, "res/images/9.png");
		loadResource(SERGEANT, "res/images/7.png");
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

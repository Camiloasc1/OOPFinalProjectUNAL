
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
	BOARD, PIECE, ETC;
	
	/**
	 * Need differed init or differed populate
	 */
	private static volatile EnumMap<ResourceManager, Sprite> MAP = new EnumMap<ResourceManager, Sprite>(ResourceManager.class);
	
	/**
	 * @return the EnumMap<ResourceManager, Sprite>
	 */
	public static synchronized EnumMap<ResourceManager, Sprite> getMap()
	{
		if (MAP.isEmpty())
		{
			populateMap();
		}
		return MAP;
	}
	
	private static synchronized void populateMap()
	{
		if (!MAP.isEmpty())
		{
			return;
		}
		
		// TODO Load Resources
		
		loadResource("res/images/grid.png", BOARD);
		
	}
	
	private static synchronized void loadResource(String file, ResourceManager key)
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
	
	public static synchronized void cleanup()
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


package gui;

import java.awt.Font;
import java.io.IOException;
import java.util.EnumMap;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * @author camiloasc1
 * 
 */
public enum ResourceManager
{
	//@formatter:off
	BOARD,
	PIECE, 
	BOMB, CAPTAIN, COLONEL, FLAG, GENERAL, LIEUTENTANT, MAJOR, MARSHAL, MINER, SCOUT, SERGEANT, SPY,
	NEWGAME, LOADGAME, SAVEGAME, DRAW, EXIT,
	SPLASH,
	FONT1, FONT2, FONT3, FONT4, FONT5;
	//@formatter:on
	
	/**
	 * Need differed init or differed populate
	 */
	private static volatile EnumMap<ResourceManager, Sprite> SPRITEMAP = new EnumMap<ResourceManager, Sprite>(
			ResourceManager.class);
	private static volatile EnumMap<ResourceManager, TrueTypeFont> FONTMAP = new EnumMap<ResourceManager, TrueTypeFont>(
			ResourceManager.class);
	
	/**
	 * @return the EnumMap<ResourceManager, Sprite>
	 */
	public static synchronized final EnumMap<ResourceManager, Sprite> getSpriteMap()
	{
		if (SPRITEMAP.isEmpty())
		{
			populateSpriteMap();
		}
		return SPRITEMAP;
	}
	
	/**
	 * @return the EnumMap<ResourceManager, TrueTypeFont>
	 */
	public static synchronized EnumMap<ResourceManager, TrueTypeFont> getFontMap()
	{
		if (FONTMAP.isEmpty())
		{
			populateFontMap();
		}
		return FONTMAP;
	}
	
	private static synchronized final void populateSpriteMap()
	{
		if (!SPRITEMAP.isEmpty())
		{
			return;
		}
		
		// Load Resources
		
		loadSprite(BOARD, "res/images/grid.png");
		
		loadSprite(PIECE, "res/images/piece.png");
		
		loadSprite(BOMB, "res/images/b.png");
		loadSprite(CAPTAIN, "res/images/5.png");
		loadSprite(COLONEL, "res/images/3.png");
		loadSprite(FLAG, "res/images/f.png");
		loadSprite(GENERAL, "res/images/2.png");
		loadSprite(LIEUTENTANT, "res/images/6.png");
		loadSprite(MAJOR, "res/images/4.png");
		loadSprite(MARSHAL, "res/images/1.png");
		loadSprite(MINER, "res/images/8.png");
		loadSprite(SCOUT, "res/images/9.png");
		loadSprite(SERGEANT, "res/images/7.png");
		loadSprite(SPY, "res/images/s.png");
		
		loadSprite(NEWGAME, "res/images/web.png");
		loadSprite(LOADGAME, "res/images/open.png");
		loadSprite(SAVEGAME, "res/images/save.png");
		loadSprite(DRAW, "res/images/draw.png");
		loadSprite(EXIT, "res/images/skin.png");
		
		loadSprite(SPLASH, "res/images/splash.png");
	}
	
	private static synchronized final void loadSprite(ResourceManager key, String file)
	{
		try
		{
			SPRITEMAP.put(key, new Sprite(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(file))));
		}
		catch (IOException e)
		{
			System.err.println("Failed to load resources");
			// e.printStackTrace();
			GUI.cleanup();
			System.exit(1);
		}
	}
	
	private static synchronized final void populateFontMap()
	{
		FONTMAP.put(FONT1, new TrueTypeFont(new Font("SansSerif", Font.BOLD, 32), false));
		FONTMAP.put(FONT2, new TrueTypeFont(new Font("SansSerif", Font.PLAIN, 24), false));
		FONTMAP.put(FONT3, new TrueTypeFont(new Font("SansSerif", Font.PLAIN, 24), false));
		FONTMAP.put(FONT4, new TrueTypeFont(new Font("SansSerif", Font.PLAIN, 24), false));
		FONTMAP.put(FONT5, new TrueTypeFont(new Font("SansSerif", Font.PLAIN, 24), false));
	}
	
	public static synchronized final void cleanup()
	{
		if (!SPRITEMAP.isEmpty())
		{
			for (Sprite spr : SPRITEMAP.values())
			{
				spr.getTexture().release();
			}
			
			SPRITEMAP.clear();
		}
		
		if (!FONTMAP.isEmpty())
		{
			// for (TrueTypeFont ttf : FONTMAP.values())
			
			FONTMAP.clear();
		}
	}
}

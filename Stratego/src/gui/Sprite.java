
package gui;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Sprite implements Serializable
{
	private static final long serialVersionUID = -5820243227474305917L;
	private static final Sprite PIECE = ResourceManager.getSpriteMap().get(ResourceManager.PIECE);
	
	private Texture texture;
	
	/**
	 * @param texture
	 */
	public Sprite(Texture texture)
	{
		super();
		this.texture = texture;
	}
	
	/**
	 * @return the texture
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	/**
	 * Get the width of this sprite in pixels
	 * 
	 * @return The width of this sprite in pixels
	 */
	public int getWidth()
	{
		return texture.getImageWidth();
	}
	
	/**
	 * Get the height of this sprite in pixels
	 * 
	 * @return The height of this sprite in pixels
	 */
	public int getHeight()
	{
		return texture.getImageHeight();
	}
	
	/**
	 * Draw the sprite at the specified location
	 * 
	 * @param x
	 *            The x location at which to draw this sprite
	 * @param y
	 *            The y location at which to draw this sprite
	 */
	public void draw(int x, int y)
	{
		// store the current model matrix
		GL11.glPushMatrix();
		
		// bind to the appropriate texture for this sprite
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
		
		// translate to the right location and prepare to draw
		GL11.glTranslatef(x, y, 0);
		
		// draw a square textured to match the sprite
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0, texture.getHeight());
			GL11.glVertex2f(0, texture.getImageHeight());
			GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
			GL11.glVertex2f(texture.getImageWidth(), texture.getImageHeight());
			GL11.glTexCoord2f(texture.getWidth(), 0);
			GL11.glVertex2f(texture.getImageWidth(), 0);
		}
		GL11.glEnd();
		
		// restore the model view matrix to prevent contamination
		GL11.glPopMatrix();
	}
	
	/**
	 * Draw the sprite at the specified location
	 * 
	 * @param x
	 *            The x location at which to draw this sprite
	 * @param y
	 *            The y location at which to draw this sprite
	 */
	public static void drawPiece(int x, int y)
	{
		PIECE.draw(x, y);
	}
	
	public static Sprite getPieceSprite()
	{
		return PIECE;
	}
}

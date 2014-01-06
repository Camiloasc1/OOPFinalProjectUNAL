/*******************************************************************************
 * File: Sprite.java
 * Project: Stratego
 * 
 * Copyright (C) 2014 Camiloasc1.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package gui;

import java.awt.Rectangle;
import java.io.Serializable;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

/**
 * Basic graphic unit to display, contains a texture from ResourceManager
 * 
 * @author camiloasc1
 *
 */
public class Sprite implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820243227474305917L;
	/**
	 * Basic piece sprite used for enemy piece
	 */
	private static final Sprite PIECE = ResourceManager.getSpriteMap().get(ResourceManager.PIECE);
	
	/**
	 * The texture of this sprite
	 */
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
	 * @return the sprite rectangle
	 */
	public Rectangle getRectangle(int x, int y)
	{
		return new Rectangle(x * texture.getImageWidth(), y * texture.getImageWidth(), texture.getImageWidth(),
				texture.getImageHeight());
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
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
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
	
	/**
	 * @return the basic piece sprite used for enemy pie
	 */
	public static Sprite getPieceSprite()
	{
		return PIECE;
	}
}

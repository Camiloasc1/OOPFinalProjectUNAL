
package gui;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Sprite implements Serializable
{
	private static final long serialVersionUID = -5820243227474305917L;
	
	private Texture texture;
	
	/**
	 * @param texture
	 */
	public Sprite(Texture texture)
	{
		super();
		this.texture = texture;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Sprite))
		{
			return false;
		}
		Sprite other = (Sprite) obj;
		if (texture == null)
		{
			if (other.texture != null)
			{
				return false;
			}
		}
		else if (!texture.equals(other.texture))
		{
			return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Sprite [texture=" + texture + "]";
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
		// texture.bind();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		
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
}

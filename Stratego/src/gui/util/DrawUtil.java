/*******************************************************************************
 * File: DrawUtil.java
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

package gui.util;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

/**
 * Draw utilities for OpenGL in LWJGL
 * 
 * @author camiloasc1
 * 
 */
public final class DrawUtil
{
	/**
	 * Actual value of Alpha
	 */
	private static byte flashAlpha = 96; // (32 - 96)
	/**
	 * Delta for flashAlpha
	 */
	private static byte flashdA = 5;
	/**
	 * Is crescent or decrement
	 */
	private static boolean selectedAlphaStatus = false;
	
	/**
	 * Updates the value of flashAlpha
	 */
	public static void flashAlpha()
	{
		if (selectedAlphaStatus)
		{
			flashAlpha += flashdA;
			if (flashAlpha > 96)
			{
				flashAlpha = 96;
				selectedAlphaStatus = !selectedAlphaStatus;
			}
		}
		else
		{
			flashAlpha -= flashdA;
			if (flashAlpha < 32)
			{
				flashAlpha = 32;
				selectedAlphaStatus = !selectedAlphaStatus;
			}
		}
	}
	
	/**
	 * Draws a rectangle whit specified color in RGBA
	 * 
	 * @param rtg
	 *            Rectangle to draw
	 * @param red
	 *            <b>R</b>GBA
	 * @param green
	 *            R<b>G</b>BA
	 * @param blue
	 *            RG<b>B</b>A
	 * @param alpha
	 *            RGB<b>A</b>
	 */
	public static void drawRectangle(Rectangle rtg, byte red, byte green, byte blue, byte alpha)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(rtg.x, rtg.y, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4ub(red, green, blue, alpha);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(0, rtg.height);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(rtg.width, rtg.height);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(rtg.width, 0);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	/**
	 * Draws a flashAlpha() controlled rectangle whit specified color in RGB
	 * 
	 * @param rtg
	 *            Rectangle to draw
	 * @param red
	 *            <b>R</b>GB
	 * @param green
	 *            R<b>G</b>B
	 * @param blue
	 *            RG<b>B</b>
	 */
	public static void drawFlashRectangle(Rectangle rtg, byte red, byte green, byte blue)
	{
		drawRectangle(rtg, red, green, blue, (byte) flashAlpha);
	}
	
	/**
	 * Draws a white flashAlpha() controlled rectangle
	 * 
	 * @param rtg
	 *            Rectangle to draw
	 */
	public static void drawFlashRectangle(Rectangle rtg)
	{
		drawFlashRectangle(rtg, (byte) 255, (byte) 255, (byte) 255);
	}
	
	/**
	 * 
	 */
	private DrawUtil()
	{
	}
}


package gui.util;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

public final class DrawUtil
{
	private static byte flashAlpha = 96; // (32 - 96)
	private static byte flashdA = 5;
	private static boolean selectedAlphaStatus = false;
	
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
	
	public static void drawFlashRectangle(Rectangle rtg)
	{
		// flashAlpha();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(rtg.x, rtg.y, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) flashAlpha);
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
	
	private DrawUtil()
	{
	}
}

package com.ixion.Art;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

public class ImageDrawer {

	
	public void DrawImage(Texture image, float x, float y, int width, int height)
	{
					glEnable(GL_TEXTURE_2D);
					glEnable (GL_BLEND);
					glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
					image.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0); // First texture coordinate
						glVertex2f(x, y); // Top left corner
						glTexCoord2f(1, 0);
						glVertex2f(x + width, y); // Top right corner
						glTexCoord2f(1, 1);
						glVertex2f(x + width, y + height); // Bottom left corner
						glTexCoord2f(0, 1);
						glVertex2f(x, y + height); // Bottom right corner
					glEnd(); // End drawing
					glDisable(GL_TEXTURE_2D);
	}
	
	public void DrawImageFloat(Texture image, float x, float y, float width, float height)
	{
					glEnable(GL_TEXTURE_2D);
					glEnable (GL_BLEND);
					glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
					image.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0); // First texture coordinate
						glVertex2f(x, y); // Top left corner
						glTexCoord2f(1, 0);
						glVertex2f(x + width, y); // Top right corner
						glTexCoord2f(1, 1);
						glVertex2f(x + width, y + height); // Bottom left corner
						glTexCoord2f(0, 1);
						glVertex2f(x, y + height); // Bottom right corner
					glEnd(); // End drawing
					glDisable(GL_TEXTURE_2D);
	}
	
}

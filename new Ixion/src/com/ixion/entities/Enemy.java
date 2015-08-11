package com.ixion.entities;

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

import java.awt.Rectangle;

import org.newdawn.slick.opengl.Texture;

import com.ixion.Art.ImageLoader;

public class Enemy {

	private int width, height;
	private ImageLoader imageLoader;
	private int xp,yp;
	private Texture enemy;
	private int xVel,yVel;
	private int checks;
	
	public Enemy()
	{
		imageLoader = new ImageLoader();
		enemy = imageLoader.loadTexture("npcLeftStill");
		xp = 200;
		yp = 228;
		xVel = 2;
		yVel = 0;
		checks = 2;
		width = 45;
		height = 45;
	}
	
	public void Update(int [][] level)
	{ 
		final int TILE_SIZE = 32;
		
		
		for (int i = 0; i < checks; i++)
		{
			xp += xVel / checks;
			yp += yVel / checks;
			
			for (int y = 0; y < level.length; y++)
			{
				for (int x = 0; x < level[y].length; x++)
				{
					if (level[y][x] == 0)
						continue;
					
						Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
						if (collision(xp, yp, 45, 45, tile.x, tile.y, tile.width, tile.height))
						{
							if (tile.y >= yp + height - 11)
							{
								yVel = 0;
							}
							else if (tile.y + tile.height <= yp + 11)
							{
								yp++;
								yVel = 8;
							}
							
							if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
							{
								xVel = (-xVel);
								xp--;
							}
							else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
							{
								xVel = (-xVel);
								xp++;
							}
				}
			}
		}
	}
}
	
	public void Render()
	{
		// Start drawing the guy
		glEnable(GL_TEXTURE_2D);
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		enemy.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0); // First texture coordinate
			glVertex2f(xp, yp); // Top left corner
			glTexCoord2f(1, 0);
			glVertex2f(xp + 60, yp); // Top right corner
			glTexCoord2f(1, 1);
			glVertex2f(xp+ 60, yp + 60); // Bottom left corner
			glTexCoord2f(0, 1);
			glVertex2f(xp, yp + 60); // Bottom right corner
		glEnd(); // End drawing
		glDisable(GL_TEXTURE_2D);
	}
	
	
	private void Ai(int [][] level)
	{
		// 170 - 280
		//System.out.println(goingRight);
		// collision for blocks

	}
	
	// collison boolean
	private static boolean collision( float ax, float ay, float aw, float ah, 
			float bx, float by, float bw, float bh )
	{
		if ( ay + ah < by ) return false;
		else if ( ay > by + bh ) return false;
		else if ( ax + ah < bx ) return false;
		else if ( ax > bx + bw ) return false;
		return true;
	}
	
}

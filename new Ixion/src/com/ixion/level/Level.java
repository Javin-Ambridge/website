package com.ixion.level; // which package

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import com.ixion.Art.ImageLoader;
import com.ixion.entities.Player;

public class Level {
		
	// size of the tile
	private final int TILE_SIZE = 32;
	
	// textures of the tile
	private Texture blockOne,blockTwo,fire,stone,portal,coin,pushable;
	
	// new instance of the tile loader class
	private ImageLoader imageLoader;
	private Player player;
	
	// Constructor
	public Level()
	{
		imageLoader = new ImageLoader();
		player = new Player(80,10);
		blockOne = imageLoader.loadTexture("blockOne");
		blockTwo = imageLoader.loadTexture("blockTwoTest");
		fire = imageLoader.loadTexture("fire");
		stone = imageLoader.loadTexture("stone");
		portal = imageLoader.loadTexture("portal");
		coin = imageLoader.loadTexture("coin");
		pushable = imageLoader.loadTexture("pushable");
	}
	
	// rendering the blocks onto the level array depending on which number you put
	public void RenderLevel(int[][] level)
	{
		player.Update(level);
		for (int y = 0; y < level.length; y++)
		{
			for (int x = 0; x < level[y].length; x++)
			{
				if (level[y][x] != 0)
				{	
					// if its a 2 draw this block and here
					if(level[y][x] == 1)
					{
						glEnable(GL_TEXTURE_2D);
						 blockTwo.bind();
					      glBegin(GL_QUADS);
					       glTexCoord2f(0, 0); // First texture coordinate
					       glVertex2f(x * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 0); // Second texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 1); // Third texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
					       glTexCoord2f(0, 1); // Fourth texture coordinate
					       glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
					      glEnd();
					      glDisable(GL_TEXTURE_2D);
					}
					
					// if its a 1, draw this block and here
					if (level[y][x] == 2)
				     {
					glEnable(GL_TEXTURE_2D);
					 blockOne.bind();
				      glBegin(GL_QUADS);
				       glTexCoord2f(0, 0); // First texture coordinate
				       glVertex2f(x * TILE_SIZE, y * TILE_SIZE);
				       glTexCoord2f(1, 0); // Second texture coordinate
				       glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
				       glTexCoord2f(1, 1); // Third texture coordinate
				       glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
				       glTexCoord2f(0, 1); // Fourth texture coordinate
				       glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
				      glEnd();
				      glDisable(GL_TEXTURE_2D);
				     }
					
					if(level[y][x] == 3)
					{
						glEnable(GL_TEXTURE_2D);
						 stone.bind();
					      glBegin(GL_QUADS);
					       glTexCoord2f(0, 0); // First texture coordinate
					       glVertex2f(x * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 0); // Second texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 1); // Third texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
					       glTexCoord2f(0, 1); // Fourth texture coordinate
					       glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
					      glEnd();
					      glDisable(GL_TEXTURE_2D);
					}
					
					if (level[y][x] == 4)
				     {
					glEnable(GL_TEXTURE_2D);
					 portal.bind();
				      glBegin(GL_QUADS);
				       glTexCoord2f(0, 0); // First texture coordinate
				       glVertex2f(x * TILE_SIZE, y * TILE_SIZE);
				       glTexCoord2f(1, 0); // Second texture coordinate
				       glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
				       glTexCoord2f(1, 1); // Third texture coordinate
				       glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
				       glTexCoord2f(0, 1); // Fourth texture coordinate
				       glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
				      glEnd();
				      glDisable(GL_TEXTURE_2D);
				     }
					if(level[y][x] == 5 && player.got1 == false)
					{
						glEnable(GL_TEXTURE_2D);
						 coin.bind();
					      glBegin(GL_QUADS);
					       glTexCoord2f(0, 0); // First texture coordinate
					       glVertex2f(x * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 0); // Second texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 1); // Third texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
					       glTexCoord2f(0, 1); // Fourth texture coordinate
					       glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
					      glEnd();
					      glDisable(GL_TEXTURE_2D);
					}
					
					if(level[y][x] == 6)
					{
						glEnable(GL_TEXTURE_2D);
						pushable.bind();
					      glBegin(GL_QUADS);
					       glTexCoord2f(0, 0); // First texture coordinate
					       glVertex2f((x) * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 0); // Second texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
					       glTexCoord2f(1, 1); // Third texture coordinate
					       glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
					       glTexCoord2f(0, 1); // Fourth texture coordinate
					       glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
					      glEnd();
					      glDisable(GL_TEXTURE_2D);
					}
				}
			}
		}
	}

} // end of class

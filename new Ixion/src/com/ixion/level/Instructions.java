package com.ixion.level;

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

import com.ixion.Art.ImageDrawer;
import com.ixion.Art.ImageLoader;
import com.ixion.entities.Player;

public class Instructions {
	
	private ImageLoader imageLoader;
	private Texture intstuction1,intstuction2;
	private int WIDTH,HEIGHT;
	private boolean seeFirst;
	public boolean seeSecond;
	private Player player;
	private ImageDrawer draw;
	
	public Instructions()
	{
		imageLoader = new ImageLoader();
		intstuction1 = imageLoader.loadTexture("Instructions1");
		intstuction2 = imageLoader.loadTexture("Instructions2");
		WIDTH = 512;
		HEIGHT = 128;
		seeFirst = true;
		seeSecond = false;
		player = new Player(80,10);
		draw = new ImageDrawer();
	}
	
	public void Render(float x, float y, float w, float h)
	{
		if(seeFirst)
		{
			draw.DrawImage(intstuction1, (x + 150), y, WIDTH, HEIGHT);
		}
		
		if(seeSecond)
		{
			draw.DrawImage(intstuction2, (x + 150), y, WIDTH, HEIGHT);
		}
		
	}
	
	public void Update(int[][] level)
	{
		player.Update(level);
		if(player.xp <= 450)
		{
			seeFirst = true;
		}
		else
			seeFirst = false;
		
		if(player.xp >= 1018 && player.xp <= 1080)
		{
			seeSecond = true;
		}
		else
			seeSecond = false;
		
	}
	
}

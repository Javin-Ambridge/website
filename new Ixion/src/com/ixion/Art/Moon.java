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

import com.ixion.entities.Player;

public class Moon {
	
	// Textures/ imageloader / player class instance
	private ImageLoader imageLoader;
	private ImageDrawer draw;
	private Texture moon,sun,timer5,timer4,timer3,timer2,timer1;
	private Player player;
	
	// Ints/ floats
	private int width, height;
	private int width2, height2;
	public float moonX = 650f, moonY = 200f;
	public float textX = 10, textY = 10;
	private int moonTimer;
	private float moonXVel, moonYVel;
	public boolean night, day;
	private int redTimer = 5;
		
	// Constructor
	public Moon()
	{
		imageLoader = new ImageLoader();
		moon = imageLoader.loadTexture("moon");
		sun = imageLoader.loadTexture("Sun");
		timer5 = imageLoader.loadTexture("redTimer5");
		timer4 = imageLoader.loadTexture("redTimer4");
		timer3 = imageLoader.loadTexture("redTimer3");
		timer2 = imageLoader.loadTexture("redTimer2");
		timer1 = imageLoader.loadTexture("redTimer1");
		width = 64;
		width2 = 128;
		height = 64;
		height2 = 32;
		moonTimer = 0;
		moonXVel = -0.5f;
		moonYVel = -0.2f;
		night = false;
		day = true;
		player = new Player(80,10);
		draw = new ImageDrawer();
	}
	
	// Render the timer at the top
	public void RenderTimer(float x, float y, float w, float h)
	{
		
		if(redTimer == 5)
		{
			draw.DrawImage(timer5, x, y, width2, height2);
		}
		
		else if(redTimer == 4)
		{
			draw.DrawImage(timer4, x, y, width2, height2);
		}
		
		else if(redTimer == 3)
		{
			draw.DrawImage(timer3, x, y, width2, height2);
		}
		
		else if(redTimer == 2)
		{
			draw.DrawImage(timer2, x, y, width2, height2);
		}
		
		else if(redTimer == 1)
		{
			draw.DrawImage(timer1, x, y, width2, height2);	
		}
		
		else
		{
			redTimer = 5;
		}
		
	}
	
	// Render moon/sun
	public void Render(float x, float y, float w, float h)
	{
		if(night)
		{
			draw.DrawImage(moon, x, y, width, height);
		} 
		else if(day)
		{
			draw.DrawImage(sun, x, y, width, height);
		}
		
	}
	
	// Update moon/sun
	public void Update(int[][] level)
	{
		player.Update(level);
		
		if(player.isNormal())
		{
			day = true;
			night = false;
		}
		
		else if (!player.isNormal())
		{
			day = false;
			night = true;
		}
		
		moonTimer += 1;
		
		if(moonTimer >= 5)
		{
			moonX += moonXVel;
			moonY += moonYVel;
			moonTimer = 0;
		}
		
		if(moonY <= 80)
		{
			moonYVel = 0.2f;
		}
		
		if(moonX <= -80 && day)
		{
			moonX = 780;
			night = true;
			day = false;
			moonYVel = -0.2f;
		}
		
		if(moonX <= -80 && night)
		{
			moonX = 780;
			night = false;
			day = true;
			moonYVel = -0.2f;
		}
		
		if(player.counterRed <30)
		{
			redTimer = 5;
		}
		
		else if(player.counterRed > 30 && player.counterRed < 60)
		{
			redTimer = 4;
		}
		
		else if(player.counterRed > 60 && player.counterRed < 90)
		{
			redTimer = 3;
		}
		
		else if(player.counterRed > 90 && player.counterRed < 120)
		{
			redTimer = 2;
		}
		
		else if(player.counterRed > 120 && player.counterRed < 150)
		{
			redTimer = 1;
		}
		
		else
		{
			redTimer = 0;
		}
		
	}
	
}

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

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import com.ixion.Art.ImageDrawer;
import com.ixion.Art.ImageLoader;

public class Pause {
	
	private ImageLoader imageLoader;
	private ImageDrawer draw;
	private Texture pauseMenu,pauseResume, pauseResumeOn,pauseExit,pauseExitOn;
	public boolean pause;
	private int width, height;
	private int widthT, heightT;
	private boolean clicked;
	private boolean onResume = false,onExit = false;
	
	public Pause()
	{
		imageLoader = new ImageLoader();
		draw = new ImageDrawer();
		pauseMenu = imageLoader.loadTexture("pauseMenu");
		pauseResume = imageLoader.loadTexture("pauseResume");
		pauseResumeOn = imageLoader.loadTexture("pauseResumeOn");
		pauseExit = imageLoader.loadTexture("pauseExit");
		pauseExitOn = imageLoader.loadTexture("pauseExitOn");
		pause = false;
		width = 256;
		widthT = 300;
		height = 256;
		heightT = 52;
	}
	
	public void UpdateInGame()
	{
		UpdateControlsInGame();
	}
	
	public void UpdateOnPause()
	{
		Mouse();
	}
	
	public void Render(float x, float y, float w, float h)
	{
		if(pause)
		{
			// draw pause button
			draw.DrawImage(pauseMenu,x,y,width,height);
			
			if(onResume == false)
			{
				// draw resume
				draw.DrawImage(pauseResume, x - 20, y + 80, widthT, heightT);	
			}
				
			else if(onResume)
			{
				// draw resume on
				draw.DrawImage(pauseResumeOn, x - 20, y + 80, widthT, heightT);
			}
			
			if(onExit == false)
			{
				// draw exit
				draw.DrawImage(pauseExit, x - 20, y + 130, widthT, heightT);
			}
				
			else if(onExit)
			{
				// draw exit on
				draw.DrawImage(pauseExitOn, x - 20, y + 130, widthT, heightT);
			}
			
		}
	}
	
	private void UpdateControlsInGame()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && pause == false)
		{
			pause = true;
			clicked = false;
		}
	}
		
	private void Mouse()
	{
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		System.out.println("mouse x: "+mouseX+", Mouse y: "+mouseY);
		
		if(Mouse.isButtonDown(0))
		{
			clicked = true;
		}
		
		if(mouseX >= 342 && mouseX <= 450 && mouseY <= 382 && mouseY >= 317)
		{
			onResume = true;
			if(clicked)
			{
				pause = false;
				clicked = false;
			}
		}
		
		else if(mouseX >= 369 && mouseX <= 421 && mouseY <= 293 && mouseY >= 265)
		{
			onExit = true;
			if(clicked)
			{
				AL.destroy();
				Display.destroy();
				System.exit(0);
			}
		}
		
		else
		{
			clicked = false;
			onResume = false;
			onExit = false;
		}
		
	}
	
}

package com.ixion.credits; // which package

import static org.lwjgl.opengl.GL11.GL_QUADS; // imports
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import com.ixion.Art.ImageLoader;

public class Credits {

	// width/ height of the screen
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	
	// textures
	private Texture bg;
	
	// new instance of the image loader class
	private ImageLoader imageLoader;
	
	// constructor
	public Credits()
	{
		imageLoader = new ImageLoader();
		bg = imageLoader.loadTexture("backgroundCredits");
	}
	
	// drawing the credits
	public void drawCredits()
	{
		// Background
		glEnable(GL_TEXTURE_2D);
		bg.bind();
	      glBegin(GL_QUADS);
	       glTexCoord2f(0, 0); // First texture coordinate							
	       glVertex2f(0 , 0);
	       glTexCoord2f(1, 0); // Second texture coordinate
	       glVertex2f(SCREEN_WIDTH,0);
	       glTexCoord2f(1, 1); // Third texture coordinate
	       glVertex2f(SCREEN_WIDTH,SCREEN_HEIGHT);
	       glTexCoord2f(0, 1); // Fourth texture coordinate
	       glVertex2f(0,SCREEN_HEIGHT);
	      glEnd();
	      glDisable(GL_TEXTURE_2D);
	}
	
} // end of class

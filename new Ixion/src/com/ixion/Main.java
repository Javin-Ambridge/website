package com.ixion; // Which package its in

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.Texture;

import com.ixion.Art.ImageDrawer;
import com.ixion.Art.ImageLoader;
import com.ixion.Art.Moon;
import com.ixion.credits.Credits;
import com.ixion.entities.Player;
import com.ixion.level.Camera;
import com.ixion.level.Instructions;
import com.ixion.level.Level;
import com.ixion.level.Pause;
import com.ixion.text.Saves;

public class Main {
	
	//Widht,Height and title 
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	private static final String SCREEN_TITLE = "Ixion Pre-Alpha 0.6";
	private float alpha = 0;
	
	//Button Booleans
	private boolean onStart = false, onCredits = false, onExit = false,clicked = false;
	
	// New instances of the class
	private Player player;
	private Level level;
	private Credits credits;
	private Camera camera;
	private Pause pause;	
	private Moon moon;
	private Instructions intro;
	private Saves save;
	
	//What the screen state is
	private enum screenState { MAIN_MENU, LOAD_GAME, GAME, PAUSE, CREDITS };
	private screenState state;
	
	//Textures
	private Texture bg,logo,startButtonOff,startButtonOn,creditsButtonOff,creditsButtonOn,exitButtonOff,exitButtonOn,bgGame,loadOne,loadTwo,loadThree,loadOneOn,loadTwoOn,loadThreeOn;
	private Texture erase,eraseOn;
	
	// Image loader, to load the images (class)
	private ImageLoader imageLoader;
	private ImageDrawer draw;
	// Ints and booleans
	boolean enter = false;
	public boolean firstLevel = true;
	public boolean secondLevel = false;
	public boolean thirdLevel = false;
	public boolean fourthLevel = false;
	public boolean fifthLevel = false;
	
	int menuMusicTimer = 0;
	
	private int levelOutputCount = 0;
	
	java.io.File levelFile1 = new java.io.File("res/Saves/save1.txt");
	java.io.File levelFile2 = new java.io.File("res/Saves/save2.txt");
	java.io.File levelFile3 = new java.io.File("res/Saves/save3.txt");
	java.io.File Level1 = new java.io.File("res/level1.txt");
	
	private int mouseXL = 0, mouseYL = 0;
	private boolean onLoadOne = false, onLoadTwo = false, onLoadThree = false,clickedL = false,wentOut = false;
	private boolean onFirstErase = false, onSecondErase = false, onThirdErase = false;
	private boolean tookLoadOne = false, tookLoadTwo = false, tookLoadThree = false;
	int [] COUNT = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
	int [] COUNT2 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
	
	// What level to load
	private void WhatLevel()
	{
		if(save.WhatLevel() == 1)
		{
			firstLevel = true;
			secondLevel = false;
			thirdLevel = false;
			fourthLevel = false;
			fifthLevel = false;
		}
		else if(save.WhatLevel() == 2)
		{
			firstLevel = false;
			secondLevel = true;
			thirdLevel = false;
			fourthLevel = false;
			fifthLevel = false;
		}
		else if(save.WhatLevel() == 3)
		{
			firstLevel = false;
			secondLevel = false;
			thirdLevel = true;
			fourthLevel = false;
			fifthLevel = false;
		}
		else if(save.WhatLevel() == 4)
		{
			firstLevel = false;
			secondLevel = false;
			thirdLevel = false;
			fourthLevel = true;
			fifthLevel = false;
		}
		else if(save.WhatLevel() == 5)
		{
			firstLevel = false;
			secondLevel = false;
			thirdLevel = false;
			fourthLevel = false;
			fifthLevel = true;
		}
	}
	
	// Update the level selecting screen
	public void UpdateLevelSelector()
	{
		mouseXL = Mouse.getX();
		mouseYL = Mouse.getY();
		
		if(Mouse.isButtonDown(0))
		{
			clickedL = true;
		}
		
		if(mouseXL >= 81 && mouseXL <= 521 && mouseYL <= 386 && mouseYL >= 318)
		{
			// Load one
			onLoadOne = true;
			onLoadTwo = false;
			onLoadThree = false;
			
			if(clickedL)
			{
				if(wentOut)
				{
					save.WhatLoad(1);
					tookLoadOne = true;
					save.ReadLevel(1);
					WhatLevel();
					state = screenState.GAME;
				}
			}
			
		}
		
		else if(mouseXL >= 81 && mouseXL <= 521 && mouseYL <= 285 && mouseYL >= 219)
		{
			// load two
			onLoadOne = false;
			onLoadTwo = true;
			onLoadThree = false;
			
			if(clickedL)
			{
				save.WhatLoad(2);
				save.ReadLevel(2);
				WhatLevel();
				state = screenState.GAME;
			}
			
		}
		
		else if(mouseXL >= 45 && mouseXL <= 550 && mouseYL <= 189 && mouseYL >= 121)
		{
			// load three
			onLoadOne = false;
			onLoadTwo = false;
			onLoadThree = true;
			
			if(clickedL)
			{
				save.WhatLoad(3);
				tookLoadThree = true;
				save.ReadLevel(3);
				WhatLevel();
				state = screenState.GAME;
			}
			
		}
		
		else if(mouseXL >= 580 && mouseXL <= 720 && mouseYL <= 379 && mouseYL >= 337)
		{
			// First erase button
			onFirstErase = true;
			onSecondErase = false;
			onThirdErase = false;
			
			if(clickedL)
			{
				save.Erase(1);
			}
			
		}
		
		else if(mouseXL >= 580 && mouseXL <= 720 && mouseYL <= 272 && mouseYL >= 232)
		{
			// second erase button
			onFirstErase = false;
			onSecondErase = true;
			onThirdErase = false;
			
			if(clickedL)
			{
				save.Erase(2);
			}
			
		}
		
		else if(mouseXL >= 580 && mouseXL <= 720 && mouseYL <= 167 && mouseYL >= 127)
		{
			// third erase button
			onFirstErase = false;
			onSecondErase = false;
			onThirdErase = true;
			
			if(clickedL)
			{
				save.Erase(3);
			}
			
		}
		
		else
		{
			onLoadOne = false;
			onLoadTwo = false;
			onLoadThree = false;
			onFirstErase = false;
			onSecondErase = false;
			onThirdErase = false;
			clickedL = false;
			wentOut = true;
		}
		
	}
	
	// Render the level selecting screen
	public void RenderLevelSelector()
	{
		// Background
	      draw.DrawImage(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	      
	      // Logo
		      draw.DrawImage(logo, 140, -50, 512, 256);
		      
		      if(onLoadOne == false)
		      {
		    	  // load one off
		    	  draw.DrawImage(loadOne, (40), (180), (512), (128));
		      }
		      
		      if(onLoadOne)
		      {
		    	  // load one on
				   draw.DrawImage(loadOneOn, (40), (180), (512), (128));
		      }
		      
		      if(onLoadTwo == false)
		      {
		    	  // load two off
				   draw.DrawImage(loadTwo, (40), (280), (512), (128));
		      }
			    
		      if(onLoadTwo)
		      {
		    	  // load two on
				   draw.DrawImage(loadTwoOn, (40), (280), (512), (128));
		      }
		      
			      if(onLoadThree == false)
			      {
			    	  // load three off
			    	  draw.DrawImage(loadThree, (45), (380), (512), (128));
			      }
					if(onLoadThree)
					{
				     // load three on
					 draw.DrawImage(loadThreeOn, (45), (380), (512), (128));
					}  
					
					if(onFirstErase == false)
					{
						draw.DrawImage(erase, (570), (210), (156), (64));
					}
					
					if(onFirstErase)
					{
						draw.DrawImage(eraseOn, (570), (210), (156), (64));
					}
					
					if(onSecondErase == false)
					{
						draw.DrawImage(erase, (570), (315), (156), (64));
					}
					
					if(onSecondErase)
					{
						draw.DrawImage(eraseOn, (570), (315), (156), (64));
					}
					
					if(!onThirdErase)
					{
						draw.DrawImage(erase, (570), (420), (156), (64));
					}
					
					if(onThirdErase)
					{
						draw.DrawImage(eraseOn, (570), (420), (156), (64));
					}
					
	}
	
	// Save the level
	public void OutputLevel()
	{
		if(secondLevel && levelOutputCount == 0 && tookLoadOne)
		{
			if(COUNT[0] == 0)
			{
			save.SaveLevel(2,1);
			COUNT[0] += 1;
			}
		}
		
		if(secondLevel && levelOutputCount == 0 && tookLoadTwo)
		{
			if(COUNT[1] == 1)
			{
				save.SaveLevel(2,2);
				COUNT[1] += 1;
			}
			
		}
		
		if(secondLevel && levelOutputCount == 0 && tookLoadThree)
		{
			if(COUNT[2] == 2)
			{
				save.SaveLevel(2,3);
				COUNT[2] += 1;
			}
			
		}
		
		if(thirdLevel && levelOutputCount == 0 && tookLoadOne)
		{
			if(COUNT[3] == 3)
			{
				save.SaveLevel(3,1);
				COUNT[3] += 1;
			}
			
		}
		
		if(thirdLevel && levelOutputCount == 0 && tookLoadTwo)
		{
			if(COUNT[4] == 4)
			{
				save.SaveLevel(3,2);
				COUNT[4] += 1;
			}
			
		}
		
		if(thirdLevel && levelOutputCount == 0 && tookLoadThree)
		{
			if(COUNT[5] == 5)
			{
				save.SaveLevel(3,3);
				COUNT[5] += 1;
			}
			
		}
		
	}
	
	// Update what is drawn on each level
	public void UpdateLevels()
	{
		if(firstLevel)
		{
			moon.Update(map);
			player.Update(map);
			player.restart();
			pause.UpdateInGame();
			intro.Update(map);
			Continue(map);
		}
		if(secondLevel)
		{
			moon.Update(map2);
			player.Update(map2);
			player.restart();
			pause.UpdateInGame();
			Continue(map2);
		}
		
		if(thirdLevel)
		{
			moon.Update(map3);
			player.Update(map3);
			player.restart();
			pause.UpdateInGame();
			Continue(map3);
		}
		if(fourthLevel)
		{
			moon.Update(map4);
			player.Update(map4);
			player.restart();
			pause.UpdateInGame();
			Continue(map4);
		}
		
	}
	
	// Render what is drawn on each level
	public void RenderLevels()
	{
		if(firstLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			intro.Render(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map);
			player.Render();	
		}
		
		if(secondLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map2);
			player.Render();
		}
		
		if(thirdLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map3);
			player.Render();
		}
		if(fourthLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map4);
			player.Render();
		}
		
	}

	// Render what is drawn on the pause screen depending on what level
	public void RenderPauseLevels()
	{
		if(firstLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map);
			player.Render();
			pause.Render(270 + camera.getCameraXposition(), 165 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			intro.Render(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
		}
		
		if(secondLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map2);
			player.Render();
			pause.Render(270 + camera.getCameraXposition(), 165 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
		}
		
		if(thirdLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map3);
			player.Render();
			pause.Render(270 + camera.getCameraXposition(), 165 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
		}
		if(fourthLevel)
		{
			drawBackground(0 + camera.getCameraXposition(), 0 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.Render(moon.moonX + camera.getCameraXposition(), moon.moonY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			moon.RenderTimer(moon.textX + camera.getCameraXposition(), moon.textY + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
			level.RenderLevel(map4);
			player.Render();
			pause.Render(270 + camera.getCameraXposition(), 165 + camera.getCameraYposition(),SCREEN_WIDTH + camera.getCameraXposition(), SCREEN_HEIGHT + camera.getCameraYposition());
		}
		
	}
	
	// Continuing to the next level
	public void Continue(int [][] level)
	{
		for (int y = 0; y < level.length; y++)
		{
			for (int x = 0; x < level[y].length; x++)
			{
				final int TILE_SIZE = 32;
				
				if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))
				{
					enter = true;
				}
			
				if(level[y][x] == 4)
				{
					Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					if (collision(player.xp, player.yp, player.width, player.height, tile.x, tile.y, tile.width, tile.height))
					{
						if(firstLevel && player.xp >= tile.x - 32 && player.xp <= tile.x + 32)
						{
							if(enter)
							{
								firstLevel = false;
								secondLevel = true;
								enter = false;
							}
							else 
								enter = false;
						}
					}
				}
				
				if(level[y][x] == 4)
				{
					Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					if (collision(player.xp, player.yp, player.width, player.height, tile.x, tile.y, tile.width, tile.height))
					{
						if(secondLevel && player.xp >= tile.x - 32 && player.xp <= tile.x + 32)
						{
							System.out.println("x: "+player.xp);
								if(enter)
								{
								levelOutputCount = 0;
								secondLevel = false;
								thirdLevel = true;
								enter = false;
								}
								else
									enter = false;
						}
					}
				}
			}
		}
	}
	
	// Drawing main menu
	public void drawMainMenu()
	{
		// Background
	      draw.DrawImage(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	      // Logo
		      draw.DrawImage(logo, 140, -50, 512, 256);
		      
		      if(onStart == false)
		      {
			      // Start button off
		    	  draw.DrawImage(startButtonOff, 140, 180, 512, 128);
		      }
		      	if(onStart)
		      	{
				      // Start button on
		      			draw.DrawImage(startButtonOn, 140, 180, 512, 128);
		      	}
		      	
		      	if(onCredits == false)
		      	{
				      // credits button off
		      		draw.DrawImage(creditsButtonOff, 140, 280, 512, 128);
		      	}
		      	
		      	if(onCredits)
		      	{
				      // credits button on
		      		draw.DrawImage(creditsButtonOn, 140, 280, 512, 128);
		      	}
		      	
		      	if(onExit == false)
		      	{
				      // exit button off
		      		draw.DrawImage(exitButtonOff, 130, 380, 512, 128);
		      	}
		      	
		      	if(onExit)
		      	{
				      // exit button on
		      		draw.DrawImage(exitButtonOn, 130, 380, 512, 128);
		      	}

	}
	
	// Drawing background of game
	public void drawBackground(float x, float y, float w, float h)
	{
	 draw.DrawImageFloat(bgGame, x, y, w, h);
	}
	
	public void mouse() // Checking if the mouse is on the buttons, and if you clicked the button
	{
		int mouseY = Mouse.getY();
		int mouseX = Mouse.getX();
		
		if(Mouse.isButtonDown(0))
		{
			clicked = true;
		}
		
		if(mouseY <= 393 && mouseY >= 338 && mouseX >= 271 && mouseX <= 513)
		{
			onStart = true;
			onCredits = false;
			onExit = false;
			
			if(clicked)
			{
				clickedL = false;
				state = screenState.LOAD_GAME;
			}
			
		}
		
		else if(mouseY <= 287 && mouseY >= 238 && mouseX >= 227 && mouseX <= 561)
		{
			onStart = false;
			onCredits = true;
			onExit = false;
			
			if(clicked)
			{
				state = screenState.CREDITS;
			}
			
		}
		
		else if(mouseY <= 186 && mouseY >= 132 && mouseX >= 276 && mouseX <= 507)
		{
			onStart = false;
			onCredits = false;
			onExit = true;
			
			if(clicked)
			{
				Display.destroy();
				System.exit(0);
			}
			
		}
		
		else
		{
			onStart = false;
			onCredits = false;
			onExit = false;
			clicked = false;
		}
		
	}
	
	public Main() // constructor
	{		
		// Creating the display
		try
		{
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
			Display.setTitle(SCREEN_TITLE);
			Display.create();
			
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		Init();
		
		// Main game loop
		while (!Display.isCloseRequested())
		{ 
			// Render
			Render();
			// Update
			Update();
		}
		
		Display.destroy();	
		AL.destroy();
	}
		
	private void Init() // What's in the game
	{
		imageLoader = new ImageLoader();
		bg = imageLoader.loadTexture("backgroundMenu");
		bgGame = imageLoader.loadTexture("background");
		logo = imageLoader.loadTexture("logo");
		startButtonOff = imageLoader.loadTexture("playButtonNot");
		startButtonOn = imageLoader.loadTexture("playButton");
		creditsButtonOff = imageLoader.loadTexture("creditsButtonNot");
		creditsButtonOn = imageLoader.loadTexture("creditsButton");
		exitButtonOff = imageLoader.loadTexture("exitButtonOff");
		exitButtonOn = imageLoader.loadTexture("exitButton");
		loadOne = imageLoader.loadTexture("/LevelSelectorImages/loadOne");
		loadTwo = imageLoader.loadTexture("/LevelSelectorImages/loadTwo");
		loadThree = imageLoader.loadTexture("/LevelSelectorImages/loadThree");
		loadOneOn = imageLoader.loadTexture("/LevelSelectorImages/loadOneOn");
		loadTwoOn = imageLoader.loadTexture("/LevelSelectorImages/loadTwoOn");
		loadThreeOn = imageLoader.loadTexture("/LevelSelectorImages/loadThreeOn");
		erase = imageLoader.loadTexture("/LevelSelectorImages/erase");
		eraseOn = imageLoader.loadTexture("/LevelSelectorImages/eraseOn");
		state = screenState.MAIN_MENU;
		glViewport( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glDisable( GL_DEPTH_TEST );
		glDisable( GL_LIGHTING );
		Display.setVSyncEnabled(true);
		
		player = new Player(80, 10);
		level = new Level();
		credits = new Credits();
		moon = new Moon();
		camera = new Camera();
		pause = new Pause();
		intro = new Instructions();
		draw = new ImageDrawer();
		save = new Saves();
	}
	
	private void Update() // Updating the game, which state, how many frames etc
	{
		switch (state)
		{
		case MAIN_MENU:
			mouse();
			break;
		case LOAD_GAME:
			UpdateLevelSelector();
			break;
		 case CREDITS:
			 credits.drawCredits();
			 break;
		case GAME:
			UpdateLevels();
			OutputLevel();
			if (player.getX() > SCREEN_WIDTH / 2)
			{
				camera.Move(player.getXvel(),0);
				player.pastPoint = true;
			}
			else
				player.pastPoint = false;
			
			if(pause.pause)
			{
				state = screenState.PAUSE;
			}
			break;
		case PAUSE:
			pause.UpdateOnPause();
			if(pause.pause == false)
			{
				state = screenState.GAME;
			}
			break;
			}
		Display.update();
		Display.sync(60);
		SoundStore.get().poll(0);
	}
	
	private void Render() // Render the things on the menu screen
	{
		glClear(GL_COLOR_BUFFER_BIT);
		switch (state)
		{
		case MAIN_MENU:
			drawMainMenu();
			break;
		case LOAD_GAME:
			RenderLevelSelector();
			break;
		case GAME:
			FadeIn(alpha);
			if (alpha != 1)
				alpha += 0.05f;
			RenderLevels();
			break;
		case PAUSE:
			RenderPauseLevels();
			break;
		}
	}
	
	// First level array (map)
	private int[][] map = {			
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
        	{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
        	{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
    		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
    		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,6,0,0,1,1,3,3,0,0,0,3,3,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{ 1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,0,0,0,3,3,3,3,1,1,1,1,1,1,0,0,0,0,0,0,0,0, },
			{ 3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,1,1,0,0,0,0,0,0, },
			{ 3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,1,0,0, },
	};
	
	private int[][] map2 = {
			
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,1,0,2,2,2,1,0,0,0,0,2,3,3,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,3,3,3,3,3,3,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3,3,3,3,1,0,0,0,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,},
			{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,0,0,0,0,2,2,2,0,0,},
			{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,},
			{0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			
	};
	
	private int[][] map3 = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,1,1,2,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,1,0,0,0,0,0,0,0,2,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,2,1,0,0,0,0,0,0,0,1,2,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,1,0,0,0,0,0,0,2,2,1,1,1,1,1,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3,3,3,3,1,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, },
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },	
			
	};
	
	private int[][] map4 = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,3,3,3,3,3,3,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,1,1,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,1,1,1,0,0,0,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,3,3,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,1,1,1,},
			{0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3,3,3,3,3,3,3,1,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},	
	};
	
	private void FadeIn(float alpha)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(255, 255, 255, alpha);
		
		glBegin(GL_QUADS);
			glVertex2f(0, 0);
			glVertex2f(SCREEN_WIDTH, 0);
			glVertex2f(SCREEN_WIDTH, SCREEN_HEIGHT);
			glVertex2f(0, SCREEN_HEIGHT);
		glEnd();
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
	
	// starting the constructer in the main method
	public static void main(String[] args)
	{
		new Main();
	}
            
} // End of class
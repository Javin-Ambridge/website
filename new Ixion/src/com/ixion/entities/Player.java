package com.ixion.entities; // which package its in

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

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import com.ixion.Art.ImageDrawer;
import com.ixion.Art.ImageLoader;
import com.ixion.level.Camera;


public class Player {
	
	// player position, attributes, and velocity's	
	public int xp;
	public int yp;
	public int width;
	public int height;
	private int xvel, yvel;
	
	// Textures
	private Texture guy,guyRedS;
	
	// booleans for moving the character
	private boolean left = false, right = true, moving = false;
	
	// changing the sprite floats
	private float xo = 0, xoff = 0.5f,yo = 0,yoff = 0.5f;
	final float PLAYER_WALK_ANIMATION_DELAY = 0.05f;
	float playerWalkDelay = 0;
	
	// Misc booleans / ints
	private int count = 0;
	private int gimmickCount = 0;
	private boolean change = false, changeBack = false;
	public int counterRed = 0;
	public boolean pastPoint = false;
	public int deaths = 0;
	private int jumpTimer = 0;
	
	// jumping booleans
	private boolean jump, col, onGround;
	
	// making a new instance of the camera class
	private Camera camera;
	
	// calling a referance to the image loader class to use images
	private ImageLoader imageLoader;
	
	// booleans for the color of the guy
	public boolean guyNormal = true;
	public boolean guyRed = false;
	
	// booleans if certain blocks make the guy go through or not
	public boolean night = false;
	public boolean day = true;
	public boolean got1 = false;
	public int xPlace = 0;
	
	//Audio
	private Audio jumpAudio,falling;
	
	// Fixing the jump glitch
	private void JumpBugFix()
	{
		if(jump)
		{
			jumpTimer += 1;
			if(jumpTimer >= 27)
			{
				jump = false;
			}
		}
		
		else
		{
			jumpTimer = 0;
		}
	}
	
	// Boolean to see if the guy is blue or red
	public boolean isNormal()
	{
		if(guyNormal)
			return true;
		else
			return false;
	}
	
	// Constructor
	public Player(int locationX, int locationY)
	{
		imageLoader = new ImageLoader();
		guy = imageLoader.loadTexture("CharacterSpriteSheet");
		guyRedS = imageLoader.loadTexture("CharacterSpriteSheetRed");
		xp = locationX;
		yp = locationY;
		width = 45;
		height = 45;
		xvel = 0;
		yvel = 0;
		
		jump = false;
		col = false;
		onGround = false;
		
		camera = new Camera();
		
		try{
			jumpAudio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/audio/Jump.wav"));
			falling = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/audio/Falling Off.wav"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	// Controls for gimmick
	public void UpdateGimicControls()
	{		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q) && gimmickCount == 0)
		{
			if(guyNormal)
			{
				change = true;
			}
			
			if(guyRed)
			{
				changeBack = true;
			}
			
			gimmickCount += 1;
		}
	}
	
	// Changing the character
	public void Gimmick()
	{
		if(guyRed)
		{
			counterRed += 1;
			night = true;
			day = false;
		}
		
		if(guyNormal)
		{
			day = true;
			night = false;
		}
		
		if(change)
		{
				guyNormal = false;
				guyRed = true;
				gimmickCount = 0;
				change = false;
		}
		
		if(changeBack && counterRed >= 150)
		{
			guyRed = false;
			guyNormal = true;
			gimmickCount = 0;
			changeBack = false;
			counterRed = 0;
		}
		
	}
	
	// Draws the player
	public void Render()
	{
		if(guyNormal)
		{
		// Start drawing the guy
		glEnable(GL_TEXTURE_2D);
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		guy.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(xo, yo); // First texture coordinate
			glVertex2f(xp, yp); // Top left corner
			glTexCoord2f(xoff, yo);
			glVertex2f(xp + width, yp); // Top right corner
			glTexCoord2f(xoff, yoff);
			glVertex2f(xp + width, yp + height); // Bottom left corner
			glTexCoord2f(xo, yoff);
			glVertex2f(xp, yp + height); // Bottom right corner
		glEnd(); // End drawing
		glDisable(GL_TEXTURE_2D);
		}
		
		if(guyRed)
		{
			// Start drawing the guy
			glEnable(GL_TEXTURE_2D);
			glEnable (GL_BLEND);
			glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			guyRedS.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(xo, yo); // First texture coordinate
				glVertex2f(xp, yp); // Top left corner
				glTexCoord2f(xoff, yo);
				glVertex2f(xp + width, yp); // Top right corner
				glTexCoord2f(xoff, yoff);
				glVertex2f(xp + width, yp + height); // Bottom left corner
				glTexCoord2f(xo, yoff);
				glVertex2f(xp, yp + height); // Bottom right corner
			glEnd(); // End drawing
			glDisable(GL_TEXTURE_2D);
		}
		
	}
	
	// If you fall off the ledge it puts you back
	public void restart()
	{
		if(yp >= 630)
		{
			yp = 10;
			deaths += 1;
		}		
	}
	
	//updating the level, and everything in this class
	public void Update(int[][] level)
	{
		UpdateGimicControls();
		UpdateControls();
		Gimmick();
		JumpBugFix();
		
		col = false;
		
		int checks = 2;
		
		final int TILE_SIZE = 32;
		
		
		// sprite movement
		if(xvel != 0)
		{
			moving = true;
		}
		
		else
		{
			moving = false;
		}
	 playerWalkDelay += 0.01f;
	if (playerWalkDelay > PLAYER_WALK_ANIMATION_DELAY)
	 {
	  playerWalkDelay = 0.0f;
		if(moving)
		{
			xo += 1;
			xoff += 1;
			
			if(right)
			{
				yo = 0;
				yoff = 0.5f;
			}
			
			else if(left)
			{
				yo = 0.5f;
				yoff = 1;
			}
		}
	 }
	
		// collision for blocks
		for (int i = 0; i < checks; i++)
		{
			xp += xvel / checks;
			yp += yvel / checks;
			
			for (int y = 0; y < level.length; y++)
			{
				for (int x = 0; x < level[y].length; x++)
				{
					if (level[y][x] == 0)
						continue;
					
					
					// if its day he can stand on normal blocks, if not he falls
					if(level[y][x] == 1 && day)
					{
					Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					if (collision(xp, yp, width, height, tile.x, tile.y, tile.width, tile.height))
					{
						
						if(yp <= tile.y - 43)
						{
							yp = tile.y - 43;
						}
						
						if (tile.y >= yp + height - 11)
						{
							yvel = 0;
							col = true;
							onGround = true;
						}
						else if (tile.y + tile.height <= yp + 11)
						{
							yp++;
							yvel = 8;
						}
						
						if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
						{
							xvel = 0;
							xp--;
						}
						else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
						{
							xvel = 0;
							xp++;
						}
					}
				   }
					
					// if its night he can stand on night blocks, if not he falls
					if(level[y][x] == 2 && night)
					{
						Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
						if (collision(xp, yp, width, height, tile.x, tile.y, tile.width, tile.height))
						{
							if (tile.y >= yp + height - 11)
							{
								yvel = 0;
								col = true;
								onGround = true;
							}
							else if (tile.y + tile.height <= yp + 11)
							{
								yp++;
								yvel = 8;
							}
							
							if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
							{
								xvel = 0;
								xp--;
							}
							else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
							{
								xvel = 0;
								xp++;
							}
						}
					}
					
					// if its day he can stand on normal blocks, if not he falls
					if(level[y][x] == 3 && day)
					{
					Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					if (collision(xp, yp, width, height, tile.x, tile.y, tile.width, tile.height))
					{
						
						if(yp <= tile.y - 43)
						{
							yp = tile.y - 43;
						}
						
						if (tile.y >= yp + height - 11)
						{
							yvel = 0;
							col = true;
							onGround = true;
						}
						else if (tile.y + tile.height <= yp + 11)
						{
							yp++;
							yvel = 8;
						}
						
						if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
						{
							xvel = 0;
							xp--;
						}
						else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
						{
							xvel = 0;
							xp++;
						}
					}
				   }

					
					if(level[y][x] == 4 && night)
					{
						Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
						if (collision(xp, yp, width, height, tile.x, tile.y, tile.width, tile.height))
						{
							if (tile.y >= yp + height - 11)
							{
								yvel = 0;
								col = true;
								onGround = true;
							}
							else if (tile.y + tile.height <= yp + 11)
							{
								yp++;
								yvel = 8;
							}
							
							if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
							{
								xvel = 0;
								xp--;
							}
							else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
							{
								xvel = 0;
								xp++;
							}
						}
					}
					
					if(level[y][x] == 5 && !got1)
					{
						Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
						if (collision(xp, yp, width, height, tile.x, tile.y, tile.width, tile.height))
						{
							if (tile.y >= yp + height - 11)
							{
								got1 = true;
							}
							else if (tile.y + tile.height <= yp + 11)
							{
								got1 = true;
							}
							
							if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
							{
								got1 = true;
							}
							else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
							{
								got1 = true;
							}
						}
					}
					
					if(level[y][x] == 6)
					{
						Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
						if (collision(xp, yp, width, height, tile.x, tile.y, tile.width, tile.height))
						{
							if (tile.y >= yp + height - 11)
							{
								yvel = 0;
								col = true;
								onGround = true;
							}
							else if (tile.y + tile.height <= yp + 11)
							{
								yp++;
								yvel = 8;
							}
							if (xp + width >= tile.x - 6 && yp + height >= tile.y + 6 && xp + width <= tile.x + 20)
							{
								level[y][x + 1] = level[y][x];
								level[y][x] = 0;								
							}
							else if (xp <= tile.x + tile.width && yp + height >= tile.y + 6)
							{
								level[y][x - 1] = level[y][x];
								level[y][x] = 0;
							}
						}
					}
				}
			}
		}
		
		if (!col && !jump)
			yvel = 8;
		
		if (jump && yvel < 8)
			yvel++;
		else
			jump = false;
	}
	
	// update the controls of the character
	private void UpdateControls()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A))
		{
				camera.goingLeft = true;
				camera.goingRight = false;
				xvel = -2; 
				left = true;
				right = false;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			camera.goingRight = true;
			camera.goingLeft = false;
			xvel = 2;
			right = true;
			left = false;
		}
		else
			xvel = 0;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			Jump();
	}
	
	// jump method
	private void Jump()
	{
		if (onGround && !jump)
		{
			jumpAudio.playAsMusic(1.0f, 1.0f, false);
			jump = true;
			onGround = false;
			yvel -= 18;
			yp -= 5;
		}
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
	
	// Returns the x-position of the player
	public int getX()
	{
		return xp;
	}
	
	// Returns the y-position of the player
	public int getY()
	{
		return yp;
	}
	
	// Returns the x-vel of the player
	public int getXvel()
	{
		return xvel;
	}
	
	// Returns the y-vel of the player
	public int getYvel()
	{
		return yvel;
	}

	
} // end of class

package com.ixion.level; // which package

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glTranslatef;
public class Camera {
	
	// int/boolean so you can reset if he falls off
	public int distance = 0;
	public boolean goingRight = false, goingLeft = false;
	
	public float camxp;
	public float camyp;
	
	// moving the camera
	public void Move(int xp, int yp)
	{
		if(goingRight)
		{
			distance += 2;
		}
		
		else if(goingLeft)
		{
			distance -= 2;
		}
		
		glMatrixMode(GL_MODELVIEW);
		glTranslatef(-xp, -yp, 0);
		
		camxp += xp;
		camyp += yp;
	}
	
	
	
	// Seperate from move method
	public float getCameraXposition()
	{
	return camxp;
	}

	public float getCameraYposition()
	{
	return camyp;
	}

} // end of class

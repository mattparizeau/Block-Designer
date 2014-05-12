package com.matt.blockdesigner.renderer;

import org.lwjgl.opengl.GL11;

public class BlockRenderer {
	
	private float pitch, yaw, roll;
	
	public BlockRenderer()
	{
		
	}
	
	public void rotate()
	{
		this.pitch++;
		this.yaw++;
		if (this.pitch > 360)
			this.pitch = 0;
		if (this.yaw > 360)
			this.yaw = 0;
	}
	
	public void draw()
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glRotatef(roll, 0, 0, 1.0F);
		GL11.glRotatef(yaw, 0, 1.0F, 0);
		GL11.glRotatef(pitch, 1.0F, 0, 0);
		GL11.glBegin(GL11.GL_QUADS);
		{
			// Front
			this.addVertex(0, 0, 0, 0, 0);
			this.addVertex(0, 1, 0, 0, 1);
			this.addVertex(1, 1, 0, 1, 1);
			this.addVertex(1, 0, 0, 1, 0);
			
			// Back
			this.addVertex(0, 1, 1, 0, 1);
			this.addVertex(1, 1, 1, 1, 1);
			this.addVertex(1, 0, 1, 1, 0);
			this.addVertex(0, 0, 1, 0, 0);
			
			// Left
			this.addVertex(0, 0, 0, 0, 0);
			this.addVertex(0, 1, 0, 0, 1);
			this.addVertex(0, 1, 1, 1, 1);
			this.addVertex(0, 0, 1, 1, 0);
			
			// Right
			this.addVertex(1, 1, 0, 0, 1);
			this.addVertex(1, 1, 1, 1, 1);
			this.addVertex(1, 0, 1, 1, 0);
			this.addVertex(1, 0, 0, 0, 0);
			
			// Top
			this.addVertex(0, 1, 0, 0, 0);
			this.addVertex(0, 1, 1, 0, 1);
			this.addVertex(1, 1, 1, 1, 1);
			this.addVertex(1, 1, 0, 1, 0);
			
			// Bottom
			this.addVertex(0, 0, 1, 0, 1);
			this.addVertex(1, 0, 1, 1, 1);
			this.addVertex(1, 0, 0, 1, 0);
			this.addVertex(0, 0, 0, 0, 0);
		}
		GL11.glEnd();
	}
	
	private void addVertex(float x, float y, float z, float u, float v)
	{
		GL11.glTexCoord2f(u, v);
		GL11.glVertex3f(x, y, z);
	}
	
}

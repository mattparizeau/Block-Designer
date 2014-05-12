package com.matt.blockdesigner;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.matt.blockdesigner.renderer.BlockRenderer;

public class BlockDesigner {
	
	private static BlockDesigner instance;
	
	public static final void main(String[] args)
	{
		BlockDesigner.instance = new BlockDesigner();
	}
	
	public static final BlockDesigner getInstance()
	{
		return BlockDesigner.instance;
	}
	
	private Window window;
	private BlockRenderer renderer;
	
	public BlockDesigner()
	{
		this.window = new Window(800, 600);
		this.renderer = new BlockRenderer();
		this.initGL();
		
		long lastTime = System.nanoTime();
		float ns = 1.0F / 60.0F;
		
		long unprocessedTime = 0;
		long frameTime = 0;
		
		int updates = 0;
		int frames = 0;
		
		while(!Display.isCloseRequested())
		{
			long startTime = System.nanoTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)ns;
			frameTime += passedTime;
			
			while (unprocessedTime >= 1000000000L)
			{
				unprocessedTime -= 1000000000L;
				
				this.update();
				updates++;
			}
			if (frameTime >= 1000000000L)
			{
				this.window.setTitle("FPS: " + frames + " | UPS: " + updates);
				frameTime = 0;
				updates = 0;
				frames = 0;
			}
			this.render();
			frames++;
		}
		this.window.dispose();
	}
	
	public void initGL()
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45, (float)window.getWidth() / (float)window.getHeight(), 1f, 100);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public void update()
	{
		this.renderer.rotate();
		this.window.update();
	}
	
	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0, 0, -10);
		this.renderer.draw();
	}
	
}

package com.matt.blockdesigner;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	private int width, height;
	
	public Window(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.create();
	}
	
	private void create()
	{
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void update()
	{
		Display.update();
	}
	
	public void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public void setTitle(String title)
	{
		Display.setTitle("Block Designer | " + title);
	}
	
	public boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
}

package com.matt.blockdesigner.renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

public class Texture {
	
	private int id;
	
	public Texture(int id)
	{
		this.id = id;
	}

	public static final Texture loadTexture(String path)
	{
		
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(Texture.class.getResourceAsStream(path));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		if (image == null)
			return null;
		
		Texture texture = null;
		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		
		int id = GL11.glGenTextures();
		
		texture = new Texture(id);
		texture.bind();
		
		
		
		return texture;
		
	}
	
	public void bind()
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.id);
	}
	
	public void unbind()
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
}

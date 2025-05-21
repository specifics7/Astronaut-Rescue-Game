package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject implements IDrawable
{
	private int size;
	private int color;
	private Point location;
	private GameWorld gw;
	
	// Constructor for GameObject, takes size and color
	public GameObject(int size, int color, GameWorld gw)
	{
		this.color = color;
		this.size = size;
		this.location = getRandomLocation();
		this.gw = gw;
	}

	public GameWorld getGameWorld() 
	{
	    return gw;
	}

	// Gets size
	public int getSize()
	{
		return size;
	}
	
	// Gets color
	public int getColor()
	{
		return color;
	}
	
	// Gets location
	public Point getLocation()
	{
	    return location;
	}
	
	// Sets size
	public void setSize(int size)
	{
		this.size = size;
	}
	
	// Sets color
	public void setColor(int color)
	{
		this.color = color;
	}
	
	// Sets color
	public void setLocation(Point location)
	{
		this.location = location;
	}
	

	// Gets random location
	public Point getRandomLocation() 
	{
	    Random rand = new Random();
	    float x = rand.nextFloat() * 1000; // Random float from 0.0 to 1000.0
	    float y = rand.nextFloat() * 1000;
	    return new Point(x, y);
	}

	
	// Returns string RGB description of color
	public String colorToString()
	{
		 String myDesc = " color=" + "[" + ColorUtil.red(this.color) + ","
		+ ColorUtil.green(this.color) + ","
		+ ColorUtil.blue(this.color) + "]";
		 return myDesc;
	}
}



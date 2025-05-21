package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

// Alien inherits Opponent
public class Alien extends Opponent 
{
    // Constructor for Alien, setting size and color to blue
    public Alien(GameWorld gw) 
    {
        super(getRandomSize(), ColorUtil.BLUE, gw); // Set color to blue for Alien
        setSpeed(5*1); // Fixed speed for Alien is always 5 * Constant. Constant = 1
    }
    
    @Override
     public void draw(Graphics g, Point pCmpRelPrnt)
    {
    	int objSize = this.getSize();
    	int x = (int) (pCmpRelPrnt.getX() + getLocation().getX());
    	int y = (int) (pCmpRelPrnt.getY() + getLocation().getY());
    	
    	g.setColor(getColor());
    	
    	g.fillRect(x - objSize/2, y - objSize/2, objSize, objSize);
    	
    	
    }
	
    @Override
    // Implemented move method, movement based on speed and direction
    public void move() 
    {
        float deltaX = (float) Math.cos(Math.toRadians(90 - getDirection())) * getSpeed();
        float deltaY = (float) Math.sin(Math.toRadians(90 - getDirection())) * getSpeed();
        Point currentLocation = getLocation();
        float newX = currentLocation.getX() + deltaX;
        float newY = currentLocation.getY() + deltaY;
        setLocation(new Point(newX, newY));
    }

    // Get random size between 20 and 50
    private static int getRandomSize() 
    {
        Random rand = new Random();
        return rand.nextInt(81) + 40; // Generates a size between 20 and 50
    }
    
    // toString method for Alien
	public String toString()
	{
		Point location = getLocation();
	    String colorDesc = colorToString(); 
	    
	    return "Alien: " +
	            "loc=" + location.getX() + "," + location.getY() +
	            colorDesc +
	            " size=" + getSize() +
	            " speed=" + getSpeed() +
	            " dir=" + getDirection();
	}
	
	// Sets color
    @Override
	public void setColor(int color)
	{
		super.setColor(color);
	}
	
	// Gets color
    @Override
	public int getColor()
	{
		return super.getColor();
	}
}

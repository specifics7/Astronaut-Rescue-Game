package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;
import java.util.Random;
import com.codename1.ui.Graphics;
import java.util.ArrayList;


// Astronaut inherits Opponent
public class Astronaut extends Opponent implements ISelectable
{
    private int health; // Health can range from 0-5
    private static final int MAX_HEALTH = 5; // Initial health 5
    private static final int RED_COLOR = ColorUtil.rgb(155, 0, 0); // Dark red RGB color

    
    private boolean selected = false;

    // Constructor for Astronaut
    public Astronaut(GameWorld gw) 
    {
        super(getRandomSize(), RED_COLOR, gw); // Pass gw to the superclass constructor
        this.health = MAX_HEALTH; // Start with full health
        updateSpeed(); // Set initial speed based on health
    }
    @Override
    public void draw(Graphics g, Point pCmpRelPrnt)
    {
    	int objSize = this.getSize();
    	int x = (int) (pCmpRelPrnt.getX() + getLocation().getX());
    	int y = (int) (pCmpRelPrnt.getY() + getLocation().getY());
    	
    	int[] xPoints = {x, x - objSize/2, x + objSize/2};
    	int[] yPoints = {y + objSize/2, y - objSize/2, y - objSize/2};
    	
    	g.setColor(getColor());
    	
    	if (isSelected())
    	{
    		g.drawPolygon(xPoints, yPoints, 3);
    	}
    	
    	else
    	{
    		g.fillPolygon(xPoints, yPoints, 3);
    	}
    	
    	g.setColor(ColorUtil.BLACK);
    	g.drawString("", health, x - objSize/8, y - objSize/4);
    	
    }
    
    public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
    {
    	float L = pCmpRelPrnt.getX() + this.getLocation().getX() - this.getSize()/2;
    	float R = pCmpRelPrnt.getX() + this.getLocation().getX() + this.getSize()/2;
    	float T = pCmpRelPrnt.getY() + this.getLocation().getY() + this.getSize()/2;
    	float B = pCmpRelPrnt.getY() + this.getLocation().getY() - this.getSize()/2;
    	
    	float x = pPtrRelPrnt.getX();
    	float y = pPtrRelPrnt.getY();
    	
    	if (x < L || x >R || y < B || y > T)
    	{
    		return false;
    	}
    	
    	else
    	{
    		return true;
    	}

    }
    
    // Gets health of astronaut
    public int getHealth()
    {
    	return health;
    }
    
    // Sets health of astronaut
    public void setHealth(int health) 
    {
    	this.health = health;
    }
    
    // Decrease health in case of fight
    public void decreaseHealth() 
    {
        if (health > 0) 
        {
            health--;
            updateSpeed(); // Update speed based on new health value
            fadeColor();   // Update color based on new health value
        }
    }

    // Update speed based on the astronaut's health
    private void updateSpeed() 
    {
        setSpeed(health*1); // Speed = health * constant (constant = 1 for now)
    }

    // Fade the color as health decreases
 // Fade the color as health decreases
    private void fadeColor() {
        // Lighten the color by increasing the green and blue components proportionally
        int lightRed = 255; // Keep red component at full
        int lightGreen = (255 - health * 255 / MAX_HEALTH); // Increase green component
        int lightBlue = lightGreen; // Make blue component equal to green for consistent lightening

        setColor(ColorUtil.rgb(lightRed, lightGreen, lightBlue));
    }


    @Override
    // Implemented move method, movement based on speed and direction
    public void move() 
    {
        // Calculate deltaX and deltaY based on direction and speed
        float deltaX = (float) Math.cos(Math.toRadians(90 - getDirection())) * getSpeed();
        float deltaY = (float) Math.sin(Math.toRadians(90 - getDirection())) * getSpeed();
        Point currentLocation = getLocation();
        float newX = currentLocation.getX() + deltaX;
        float newY = currentLocation.getY() + deltaY;
        setLocation(new Point(newX, newY));
    }

    // Get random size
    private static int getRandomSize() 
    {
        Random rand = new Random();
        return rand.nextInt(81) + 40; // Generates size between 40 and 150
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
    // toString for Astronaut
	public String toString()
	{
		Point location = getLocation();
	    String colorDesc = colorToString(); 
	    
	    return "Astronaut: " +
	            "loc=" + location.getX() + "," + location.getY() +
	            colorDesc +
	            " size=" + getSize() +
	            " speed=" + getSpeed() +
	            " dir=" + getDirection() +
	            " health=" + health;
	}
	
	public void setSelected(boolean y)
	{
		selected = y;
	}
	
	public boolean isSelected()
	{
		return selected;
	}

}
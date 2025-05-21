package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
// TODO SPACESHIP NEEDS A SETCOLOR()
// Spaceship inherits Rescuer
public class Spaceship extends Rescuer 
{
    private static final int INITIAL_SIZE = 100; // Initial size of the spaceship door
    private static final int MIN_SIZE = 50;      // Minimum size of the spaceship door
    private static final int MAX_SIZE = 1000;    // Maximum size of the spaceship door
    private static final int COLOR = ColorUtil.YELLOW; // Spaceship color, it is set to yellow

    // Spaceship constructor
    public Spaceship(GameWorld gw) 
    {
        super(INITIAL_SIZE, COLOR, gw);
    }
    @Override
    public void draw(Graphics g, Point pCmpRelPrnt)
    {
    	int objSize = this.getSize();
    	int x = (int) (pCmpRelPrnt.getX() + getLocation().getX());
    	int y = (int) (pCmpRelPrnt.getY() + getLocation().getY());
    	
    	g.setColor(getColor());
    	
    	g.fillArc(x - objSize/2, y - objSize/2, objSize, objSize, 0, 360);
    	
    	
    }
    // Expands size of spaceship door 
    public void expandSize() 
    {
        int newSize = getSize() + 50; // Increase size by 50 units
        if (newSize > MAX_SIZE) newSize = MAX_SIZE; // Cap size at MAX_SIZE
        setSize(newSize);
    }
    
    // Decreases size of spaceship door
    public void decreaseSize() 
    {
        int newSize = getSize() - 50; // Decrease size by 50 units
        if (newSize < MIN_SIZE) newSize = MIN_SIZE; // Ensure size doesn't go below MIN_SIZE
        setSize(newSize);
    }

    @Override
    // Move left
    public void moveLeft() 
    {
        Point loc = getLocation();
        loc.setX(loc.getX() - 10); // Move left by 10 units
        setLocation(loc);
    }

    @Override
    // Move right
    public void moveRight() 
    {
        Point loc = getLocation();
        loc.setX(loc.getX() + 10); // Move right by 10 units
        setLocation(loc);
    }

    @Override
    // Move up
    public void moveUp() 
    {
        Point loc = getLocation();
        loc.setY(loc.getY() - 10); // Move up by 10 units
        setLocation(loc);
    }

    @Override
    // Move down
    public void moveDown() 
    {
        Point loc = getLocation();
        loc.setY(loc.getY() + 10); // Move down by 10 units
        setLocation(loc);
    }
      
	@Override
	// Sets location of spaceship to a new location 
	public void jumpToLocation(Point newLocation)
	{
		 setLocation(newLocation);
	        System.out.println("Spaceship has jumped to location: (" + newLocation.getX() + ", " + newLocation.getY() + ")");	
	}
	
    // get color
    @Override
    public int getColor() {
        return super.getColor(); // This calls GameObject's getColor if available
    }

    // set color
    @Override
    public void setColor(int color) {
        super.setColor(color); 
    }
	
	// toString for Spaceship
	public String toString()
	{
		Point location = getLocation();
	    String colorDesc = colorToString(); 
	    
	    return "Spaceship: " +
	            "loc=" + location.getX() + "," + location.getY() +
	            colorDesc +
	            " size=" + getSize();
	}
}


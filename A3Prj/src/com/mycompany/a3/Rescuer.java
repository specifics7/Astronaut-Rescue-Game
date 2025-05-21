package com.mycompany.a3;

import com.codename1.charts.models.Point;

// Rescuer inherits GameObject, implements IGuided
public abstract class Rescuer extends GameObject implements IGuided 
{
	// Rescuer constructor 
    public Rescuer(int size, int color, GameWorld gw) 
    {
        super(size, color, gw);
    }

    // Abstract movement methods that must be implemented by concrete subclasses
    public abstract void moveLeft();

    
    public abstract void moveRight();

    
    public abstract void moveUp();

    
    public abstract void moveDown();


	public abstract void jumpToLocation(Point newLocation);
		
	
}

package com.mycompany.a3;

import java.util.Random;
import java.util.ArrayList;
// Opponent inherits GameObject, implements IMoving
public abstract class Opponent extends GameObject implements IMoving, ICollider 
{
    private int direction;
    private int speed;
    private ArrayList<GameObject> collisionList = new ArrayList<GameObject>();

    // Constructor for Opponent gets size and color
    public Opponent(int size, int color, GameWorld gw) 
    {
        super(size, color, gw);
        this.direction = new Random().nextInt(360); // Random direction between 0 and 359 degrees
    }
    
    @Override
    public boolean collidesWith(GameObject otherObject) 
    {
        if (otherObject instanceof ICollider) 
        {
            float L1 = this.getLocation().getX() - this.getSize()/2;
            float R1 = this.getLocation().getX() + this.getSize()/2;
            float T1 = this.getLocation().getY() + this.getSize()/2;
            float B1 = this.getLocation().getY() - this.getSize()/2;
            
            float L2 = otherObject.getLocation().getX() - otherObject.getSize()/2;
            float R2 = otherObject.getLocation().getX() + otherObject.getSize()/2;
            float T2 = otherObject.getLocation().getY() + otherObject.getSize()/2;
            float B2 = otherObject.getLocation().getY() - otherObject.getSize()/2;
            
            if (R1 < L2 || L1 > R2 || T2 < B1 || T1 < B2) 
            {
                return false;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void handleCollision(GameObject otherObject) 
    {
        if (!collisionList.contains(otherObject)) 
        {
            collisionList.add(otherObject);
            if (otherObject instanceof Astronaut) 
            {
                this.getGameWorld().handleAlienAstronautCollision(this, (Astronaut) otherObject);
            } 
            else if (otherObject instanceof Alien) 
            {
                this.getGameWorld().handleAlienCollision(this, (Alien) otherObject);
            }
        }
    }
    // Gets direction
    public int getDirection() 
    {
        return direction;
    }
    
    // Sets direction
    public void setDirection(int direction) 
    {
        this.direction = direction;
    }
    
    // Gets speed
    public int getSpeed() 
    {
        return speed;
    }
    
    // Sets speed
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    // Sets size
    @Override
    public void setSize(int size) 
    {
        // Assuming opponents have a size range of 20 to 50
        if (size >= 20 && size <= 50) 
        {
            super.setSize(size);
        }
        else 
        {
            System.out.println("Size must be between 20 and 50.");
        }
    }

    // Gets size
    @Override
    public int getSize()
    {
    	return super.getSize();
    }
    // Abstract move() method 
    public abstract void move();
}

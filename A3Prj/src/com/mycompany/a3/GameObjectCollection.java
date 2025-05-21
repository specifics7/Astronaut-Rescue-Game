package com.mycompany.a3;


import java.util.ArrayList;

public class GameObjectCollection implements ICollection
{

	private ArrayList<GameObject> gameObjects;
	
	// Constructor
	public GameObjectCollection()
	{
		
		gameObjects = new ArrayList<GameObject>();
	}
	
	// Adds objects to list
	public void add(GameObject object)
	{
		gameObjects.add(object);
	}
	
	public IIterator getIterator()
	{
		// Create new GameObjectCollectionIterator
		return new GameObjectCollectionIterator();
	}
	
	private class GameObjectCollectionIterator implements IIterator
	{
		// Keeps track of index we are on in the iterator
	    private int currIndex;
	    private boolean validState;  // To ensure remove is called after getNext
		// Constructor
		public GameObjectCollectionIterator()
		{
	        this.currIndex = -1;  // Initialize index to -1
	        this.validState = false;  // Initially, remove should not be callable
		}
		// Check if more elements to be processed in collection
	    public boolean hasNext() 
	    {
	        return currIndex + 1 < gameObjects.size();
	    }
	    
	    // Return next elements to be processed from collection
	    public GameObject getNext() 
	    {
	        if (hasNext()) 
	        {
	            currIndex++;
	            validState = true;
	            return gameObjects.get(currIndex);
	        }
	        return null;
	    }
	    
	    // Remove GameObject from collection
	    public void remove() 
	    {
	        if (validState) 
	        {
	            gameObjects.remove(currIndex);
	            currIndex--;
	            validState = false;
	        } 
	        
	        else 
	        {
	            System.out.println("Error in removing GameObject....\n");
	        }
	    }
	    

	}
}
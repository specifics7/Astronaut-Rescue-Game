package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.List;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class GameWorld extends Observable
{
	// Creating game objects that will be set at start of game
	private Spaceship sp;
	private Astronaut ast1;
	private Astronaut ast2;
	private Astronaut ast3;
	private Astronaut ast4;
	private Alien ali1;
	private Alien ali2;
	private Alien ali3;
	
	
	// Array hold all game world objects
	private GameObjectCollection allGameObjects;
	
	
	private int clockTime; // Keeps track of game ticks
	private int totalScore; // Keeps track of total score
	private int astroRescued; // # of astronauts rescued, game ends when all are rescued
	private int alienSneak; // # of aliens sneaked into ship
	private int astroRemaining; // # of astronauts remaining
	private int alienRemaining; // # of aliens remaining
	private Random rand = new Random();
	
	// a2
	private boolean sound; // Checks if sound on or off
	private int mapHeight; // Height of map
	private int mapWidth; // Width of map
	
	//a3
	private boolean isPaused = false; // check if paused
	// sound effects
	private BGSound bgSound;
	private Sound openSound;
	private Sound aliensCSound;
	private Sound aliensAstroCSound;
		// Initializes game
	public void init()
	{
		// code here to create the initial game objects/setup
        // Resetting counters
        clockTime = 0;
        totalScore = 0;
        astroRescued = 0;
        alienSneak = 0;
        astroRemaining = 0;
        alienRemaining = 0;
        this.sound = false;
        
        allGameObjects = new GameObjectCollection();
		
		sp = new Spaceship(this);

        ast1 = new Astronaut(this);
        ast2 = new Astronaut(this);
        ast3 = new Astronaut(this);
        ast4 = new Astronaut(this);
        
        ali1 = new Alien(this);
        ali2 = new Alien(this);
        ali3 = new Alien(this);

        // Adding game objects into array list
        allGameObjects.add(sp);
        allGameObjects.add(ast1);
        allGameObjects.add(ast2);
        allGameObjects.add(ast3);
        allGameObjects.add(ast4);
        allGameObjects.add(ali1);
        allGameObjects.add(ali2);
        allGameObjects.add(ali3);
        
        // Counts number of objects set up so far
        reCountObjects();
        
		this.setChanged();
		this.notifyObservers(this);
    }
	
	// Opens door of spaceship, by being near or on an opponent it can collect astronaut
	// or have an alien sneak in
	public void openDoor() 
	{
	    int doorSize = sp.getSize(); // Get the door size from the spaceship
	    IIterator it = allGameObjects.getIterator(); // Iterator for all game objects

	    while (it.hasNext()) 
	    {
	        GameObject obj = it.getNext();
	        // Check every object that is instance of IMoving (is moving)
	        if (obj instanceof IMoving) 
	        {
	            Point objLocation = obj.getLocation();
	            Point spLocation = sp.getLocation();
	            // Check if the opponent is within the boundaries of spaceship door
	            if (Math.abs(objLocation.getX() - spLocation.getX()) <= doorSize / 2 &&
	                Math.abs(objLocation.getY() - spLocation.getY()) <= doorSize / 2) 
	            {
	            	// Check if an astronaut has been rescued
	                if (obj instanceof Astronaut) 
	                {
	                    Astronaut astro = (Astronaut) obj;
	                    int health = astro.getHealth();
	                    totalScore += 10 * health / 5; // Adjust score based on health
	                    astroRescued++;
	                    System.out.println("Astronout has been rescued!\n");
	                    it.remove();
	                } 
	                // Check if an alien has sneaked into ship
	                else if (obj instanceof Alien) 
	                {
	                    totalScore -= 10; // Deduct points for each alien
	                    alienSneak++;
	                    System.out.println("Ouch! Alien sneaked aboard...\n");
	                    it.remove();
	                }
	            }
	        }
	    }
	    // Count again all game objects
	    reCountObjects();
	    // Well check if game is able to end
	    checkGameOver();
	    // Score progress
	    System.out.println("Door was opened..., Score: " + totalScore + "\n");
	   
	    if (getSound())
	    {
	    	openSound.play();
	    }	    
	    
		this.setChanged();
		this.notifyObservers(this);
	}
	
	// Check if game is able to end
	private void checkGameOver() 
	{
		// If all astronauts have been rescued, then end and exit the game
	    if (astroRemaining == 0) 
	    {
	        System.out.println("Mission complete, all astronauts rescued. Now leaving the galaxy...\n");
	        System.out.println("Final Score: " + totalScore + "\n");
	        exit();
	    }
	}
	// Will count game objects and update remaining counts of both aliens and astronauts
    private void reCountObjects() 
    {
    	// Reseting
	    astroRemaining = 0;  
	    alienRemaining = 0; 
	    IIterator it = allGameObjects.getIterator();
	    
	    while (it.hasNext()) 
	    {
	        GameObject obj = it.getNext();
	        
	        if (obj instanceof Astronaut) 
	        {
	            astroRemaining++;
	        } 
	        
	        else if (obj instanceof Alien) 
	        {
	            alienRemaining++;
	        }
	    }
    }
    // Heal method to heal astronaut
    public void heal() 
    {
        // Ensure the game is in pause mode
        if (!isPaused) 
        {
            System.out.println("Cannot heal astronauts while the game is running.");
            return;
        }

        boolean healed = false; // Flag to track if a selected astronaut was healed

        IIterator iterator = allGameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.getNext();
            
            // Check if the object is an Astronaut and is selected
            if (obj instanceof Astronaut) 
            {
                Astronaut astronaut = (Astronaut) obj;
                if (astronaut.isSelected()) 
                {
                    // Restore astronaut's health, speed, and color
                    astronaut.setHealth(5); // Restore full health
                    astronaut.setSpeed(5); // Full speed based on health
                    astronaut.setColor(ColorUtil.rgb(155, 0, 0)); // Reset to default red color
                    
                    System.out.println("Astronaut healed to full health and original color.");
                    healed = true;
                }
            }
        }

        // If no astronaut was selected, notify the user
        if (!healed) 
        {
            System.out.println("No selected astronaut to heal.");
        }

        // Notify observers to update views
        this.setChanged();
        this.notifyObservers(this);
    }

    
    // Transfers spaceship to random alien
    public void transferToAlien() 
    {
        List<Alien> aliens = new ArrayList<>();
        IIterator it = allGameObjects.getIterator();

        // Check for aliens
        while (it.hasNext()) 
        {
            GameObject obj = it.getNext();
            if (obj instanceof Alien) 
            {
                aliens.add((Alien) obj);
            }
        }
        
        
        // Check if there are aliens to transfer to
        if (aliens.isEmpty()) 
        {
            System.out.println("Error, no aliens to tranfer to\n");
        } 
        else 
        {
            // Randomly select an alien and transfer the spaceship to its location
            Alien selectedAlien = aliens.get(rand.nextInt(aliens.size()));
            sp.jumpToLocation(selectedAlien.getLocation());
            System.out.println("Spaceship has been transfered to randomly selected alien, good luck!\n");
        }
		this.setChanged();
		this.notifyObservers(this);
    }
	// Transfers spaceship to random astronaut
	public void transferToAstro()
	{
        List<Astronaut> astros = new ArrayList<>();
        IIterator it = allGameObjects.getIterator();

        while (it.hasNext()) 
        {
            GameObject obj = it.getNext();
            if (obj instanceof Astronaut) 
            {
                astros.add((Astronaut) obj);
            }
        }
        
        // Check if there are astronauts to transfer to
        if (astros.isEmpty())
        {
            System.out.println("Error, no astronauts to transfer to\n");
        } 
        
        else 
        {
            // Randomly select an astronaut and transfer the spaceship to its location
            Astronaut selectedAstro = astros.get(rand.nextInt(astros.size()));
            sp.jumpToLocation(selectedAstro.getLocation());
            System.out.println("Spaceship has been transferred to randomly selcted astronaut, get them on board!\n");
        }
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void tick() 
	{
	    IIterator it = allGameObjects.getIterator();
	    
	    // First move all movable objects
	    while (it.hasNext()) 
	    {
	        GameObject obj = it.getNext();
	        if (obj instanceof IMoving && !(obj instanceof Spaceship)) 
	        {
	            IMoving movingObj = (IMoving) obj;
	            movingObj.move();
	            checkBounds(movingObj);
	        }
	    }
	    
	    // Check for collisions
	    ArrayList<GameObject> gameObjects = new ArrayList<>();
	    it = allGameObjects.getIterator();
	    while (it.hasNext()) 
	    {
	        gameObjects.add(it.getNext());
	    }
	    
	    for (int i = 0; i < gameObjects.size(); i++) 
	    {
	        GameObject obj1 = gameObjects.get(i);
	        if (obj1 instanceof ICollider) 
	        {
	            ICollider collider1 = (ICollider) obj1;
	            for (int j = i + 1; j < gameObjects.size(); j++) 
	            {
	                GameObject obj2 = gameObjects.get(j);
	                if (obj2 instanceof ICollider && collider1.collidesWith(obj2)) 
	                {
	                    collider1.handleCollision(obj2);
	                    ((ICollider) obj2).handleCollision(obj1);
	                }
	            }
	        }
	    }
	    
	    // Sound and clock updates
	    if (getSound()) 
	    {
	        bgSound.play();
	    }
	    updateClock();
	    this.setChanged();
	    this.notifyObservers(this);
	}


	// Handle collisions between Opponent (Alien) and Astronaut
	public void handleAlienAstronautCollision(Opponent opponent, Astronaut astronaut) 
	{
	    if (opponent instanceof Alien) 
	    {
	        astronaut.decreaseHealth();
	        if (getSound()) 
	        {
	            aliensAstroCSound.play();
	        }
	    }
	}

	public void handleAlienCollision(Opponent opponent1, Opponent opponent2) 
	{
	    // Check if both opponents are aliens
	    if (!(opponent1 instanceof Alien && opponent2 instanceof Alien)) 
	    {
	        return; // If either is not an alien, exit method
	    }

	    // Create a new alien only if opponent1's hashcode is less than opponent2's hashcode
	    if (opponent1.hashCode() > opponent2.hashCode()) 
	    {
	        return; // ensures that only one of the aliens handles the collision creation logic
	    }

	    // Check current number of aliens
	    int maxAliens = 25; // Maximum number of aliens allowed
	    int currentAlienCount = 0;
	    IIterator it = allGameObjects.getIterator();
	    while (it.hasNext()) 
	    {
	        if (it.getNext() instanceof Alien) 
	        {
	            currentAlienCount++;
	        }
	    }
	    if (currentAlienCount >= maxAliens) 
	    {
	        System.out.println("Maximum alien limit reached. No new aliens created.");
	        return;
	    }

	    // Create a new alien at a random valid location
	    Alien newAlien = null;
	    boolean validLocationFound = false;
	    while (!validLocationFound && currentAlienCount < maxAliens) 
	    {
	        float newX = rand.nextFloat() * mapWidth;
	        float newY = rand.nextFloat() * mapHeight;
	        newAlien = new Alien(this);
	        newAlien.setLocation(new Point(newX, newY));

	        // Check if the new location is sufficiently far from other objects
	        validLocationFound = true;
	        it = allGameObjects.getIterator();
	        while (it.hasNext()) {
	            GameObject obj = it.getNext();
	            if (obj instanceof ICollider) {
	                Point loc = obj.getLocation();
	                float dx = loc.getX() - newX;
	                float dy = loc.getY() - newY;
	                float distanceSquared = dx * dx + dy * dy;
	                float requiredDistance = newAlien.getSize() + obj.getSize();
	                if (distanceSquared < requiredDistance * requiredDistance) {
	                    validLocationFound = false;
	                    break;
	                }
	            }
	        }
	    }
	    
	    // Add the new alien if a valid location is found
	    if (validLocationFound) 
	    {
	        allGameObjects.add(newAlien);
	        currentAlienCount++;  // Update the count of aliens as we add a new one
	        alienRemaining++;  // Ensure the global count is updated
	        System.out.println("A new alien has spawned at location [" + newAlien.getLocation().getX() + ", " + newAlien.getLocation().getY() + "].");
	        if (sound) 
	        {
	            aliensCSound.play();
	        }
	    }
	}

	// Increments and notifies when game clock has ticked 
	public void updateClock()
	{
		clockTime += 1;
		System.out.print("Clock has been ticked.......\n\n");
	}
	// Prints current game state values
	public void stateValues()
	{
		System.out.println("Printed state values: ");
		System.out.println("\nCurrent clock time: " + clockTime + 
				"\nCurrent score: " + totalScore + 
				"\nAstronauts rescued: " + astroRescued +
				"\nAliens sneaked in Spaceship: " + alienSneak +
				"\nNumber of astronauts left: " + astroRemaining +
				"\nNumber of aleins left: " + alienRemaining + "\n");
		
	}
	// Moves right
	public void mRight()
	{
		sp.moveRight();
		System.out.println("Spaceship has moved right\n");
		this.setChanged();
		this.notifyObservers(this);
	}
	// Moves left
	public void mLeft()
	{
		sp.moveLeft();
		System.out.println("Spaceship has moved left\n");
		this.setChanged();
		this.notifyObservers(this);

	}
	// Moves up
	public void mUp()
	{
		sp.moveUp();
		System.out.println("Spaceship has moved up\n");
		this.setChanged();
		this.notifyObservers(this);

	}
	
	// Moves down
	public void mDown()
	{
		sp.moveDown();
		System.out.println("Spaceship has moved down\n");
		this.setChanged();
		this.notifyObservers(this);

	}
	
	// Increase size of spaceship door
	public void expand()
	{
		sp.expandSize();
		System.out.println("Door has expanded\n");
		this.setChanged();
		this.notifyObservers(this);

	}
	// Decrease size of spaceship door
	public void contract()
	{
		sp.decreaseSize();
		System.out.println("Door has contracted\n");
		this.setChanged();
		this.notifyObservers(this);

	}
	// Exits game. Confirmed yes to exit
	public void exit()
	{
		System.out.println("Quitting the game.......\n");
		System.exit(0);
	}
	// Confirm exit yes or no
	public void xExit()
	{
		System.out.println("Quit the game? Please confirm 'y' or 'n'.\n");
	}
	// Confirmed no to exit
	public void nExit()
	{
		System.out.println("Continuing the game.......\n");

	}
	
	// Prints map, which shows info of all game objects

	public void map() 
	{
	    IIterator iterator = allGameObjects.getIterator();
	    System.out.println("|-----------------------------------------------------------------|");
	    System.out.println("|------------------------Printed Map Below------------------------|");
	    System.out.println("|-----------------------------------------------------------------|");

	    while (iterator.hasNext()) 
	    {
	        GameObject obj = iterator.getNext();
	        System.out.println(obj.toString());
	    }
	    System.out.println("\n");
	}
	
    // Method to set dimensions from MapView
    public void setMapDimensions(int width, int height) 
    {
        this.mapWidth = width;
        this.mapHeight = height;
    }

    // Check if a object of Opponent type has gone out of bounds
    public void checkBounds(IMoving movingObj) 
    {
        GameObject obj = (GameObject) movingObj;
        Point location = obj.getLocation();
        boolean outOfBounds = false;

        // Check horizontal boundaries
        if (location.getX() < 0 || location.getX() > this.mapWidth) 
        {
            location.setX(Math.max(0, Math.min(location.getX(), this.mapWidth)));
            outOfBounds = true;
        }

        // Check vertical boundaries
        if (location.getY() < 0 || location.getY() > this.mapHeight) 
        {
            location.setY(Math.max(0, Math.min(location.getY(), this.mapHeight)));
            outOfBounds = true;
        }

        // Adjust direction if the object is an Opponent and it's out of bounds
        if (outOfBounds && obj instanceof Opponent) 
        {
            Opponent opp = (Opponent) obj;
            opp.setDirection((opp.getDirection() + 180) % 360);
            System.out.println("CLINK! An opponent hit the bounds and was readjusted\n");
        }
    }
	// additional methods here to manipulate world objects and related game state data
	public void setSound(boolean sound)
	{
		this.sound = sound;
		this.setChanged();
		this.notifyObservers(this);
		
	}
	// get sound
	public boolean getSound()
	{
		return sound;
	}
	// Set map height
	public void setMapHeight(int mapHeight)
	{
		this.mapHeight = mapHeight;
	}
	// Set map width
	public void setMapWidth(int mapWidth)
	{
		this.mapWidth = mapWidth;
	}
	// get map height
	public int getMapHeight()
	{
		return mapHeight;
	}
	// get map width
	public int getMapWidth()
	{
		return mapWidth;
	}
	// get time
	public int getTime()
	{
		return clockTime;
	}
	// get score
	public int getScore()
	{
		return totalScore;
	}
	// get astronauts rescued
	public int getAstrosResc()
	{
		return astroRescued;
	}
	// get aliens sneaked in
	public int getAliensSneaked()
	{
		return alienSneak;
	}
	// get astronauts remaining
	public int getAstrosRem()
	{
		return astroRemaining; 
	}
	// get aliens remaining
	public int getAliensRem()
	{
		return alienRemaining;
	}
	
	// gets iterator
	public IIterator getIterator()
	{
		IIterator iterator = allGameObjects.getIterator();
		return iterator;
		
	}
	// get boolean to check if game paused or not
	public boolean getIsPaused()
	{
		return isPaused;
	}
	// set if paused or not
	public void setIsPaused(boolean isPaused)
	{
		this.isPaused = isPaused;
	}
	// Create sounds for game
	public void createSounds()
	{
		bgSound = new BGSound("bgtrack.mp3");
		openSound = new Sound("door_open.wav");
		aliensCSound = new Sound("aliensCollide.wav");
		aliensAstroCSound = new Sound("aliensAstroCollide.wav");
	}
	// play sound
	public void playSound()
	{
		if (sound)
		{
			bgSound.play();
		}
	}
	// pause sound
	public void pauseSound()
	{
		if (sound)
		{
			bgSound.pause();
		}
	}
}

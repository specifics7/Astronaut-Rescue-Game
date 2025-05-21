package com.mycompany.a3;

import com.codename1.charts.models.Point;

// Interface IGuided (all rescuers are guided)
public interface IGuided 
{
	// Move object left
	void moveLeft();
	
	// Move object right
	void moveRight();
	
	// Move object up
	void moveUp();
	
	// Move object down
	void moveDown();
	
	// Jump to location of a randomly selected astronaut or alien
	void jumpToLocation(Point newLocation);

}

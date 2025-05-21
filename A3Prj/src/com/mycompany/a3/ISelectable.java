package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;


public interface ISelectable 
{
	// A way to mark an object as selected or not
	public void setSelected(boolean b);
	
	// A way to test whether an object is selected
	public boolean isSelected();
	
	// A way to determine if a pointer position is on an object
	// pPtrRelPrnt is pointer position relative to the parent origin
	// pCmpRelPrnt is the component position relative to the parent origin
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	// A way to draw the object that knows about drawing
	// different ways depending on isSelected
	public void draw(Graphics g, Point pCmpRelPrnt);
	
	

}

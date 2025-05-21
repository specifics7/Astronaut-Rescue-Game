package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;

import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;


public class MapView extends Container implements Observer
{

	private GameWorld gw;

	
	// Constructor
	public MapView(GameWorld gw)
	{
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255,  0,  0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(50);
		
		
		this.gw = gw;
		
	}
	
	public void update(Observable observable, Object data)
	{
		// Display map method from GameWorld
		gw.map();		
		this.repaint();
		
		
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator iter = gw.getIterator();
		
		while(iter.hasNext())
		{
			GameObject currObj = iter.getNext();
			
			if (currObj instanceof IDrawable)
			{
				((IDrawable) currObj).draw(g,  pCmpRelPrnt);
			}
			
			
			
			
		}
		
	}
	
	public void pointerPressed(int xPoint, int yPoint)
	{
		if (gw.getIsPaused())
		{
			int x = xPoint - getParent().getAbsoluteX();
			int y = yPoint - getParent().getAbsoluteY();
			
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			
			IIterator iter = gw.getIterator();
			
			while (iter.hasNext())
			{
				GameObject currObj = iter.getNext();
				
				if (currObj instanceof ISelectable)
				{
					ISelectable selectedObj = (ISelectable)currObj;
					
					if (selectedObj.contains(pPtrRelPrnt, pCmpRelPrnt))
					{
						selectedObj.setSelected(true);
					}
					
					else
					{
						selectedObj.setSelected(false);
					}
				}
			}
			
			repaint();
			System.out.println("Pressed");	
		}
	}
	/**
	public void update(Observable observable, Object data)
	{
		// Display map method from GameWorld
		gw.map();
		this.revalidate();
		
		
	}
	**/
}

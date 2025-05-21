package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public interface IDrawable 
{
	// draws object using received Graphics object g and Point 
	void draw(Graphics g, Point pCmpRelPrnt);
}

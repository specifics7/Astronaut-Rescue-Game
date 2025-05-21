package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExpandDoorCmd extends Command

{
	private GameWorld gw;
	
	// Constructor
	public ExpandDoorCmd(GameWorld gw)
	{
		super("Expand");
		this.gw = gw;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		// Call expand method from GameWorld class
		gw.expand();
	}

}

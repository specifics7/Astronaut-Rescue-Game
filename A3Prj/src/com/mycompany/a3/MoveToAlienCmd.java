package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MoveToAlienCmd extends Command
{

	private GameWorld gw;
	
	// Constructor
	public MoveToAlienCmd(GameWorld gw)
	{
		super("MoveToAlien");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call transferToAlien method from GameWorld class
		gw.transferToAlien();
	}
	
	
	
}

package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class MoveUpCmd extends Command
{

	private GameWorld gw;
	
	// Constructor
	
	public MoveUpCmd(GameWorld gw)
	{
		super("Up");
		this.gw = gw;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call moveUp method from GameWorld class
		gw.mUp();
	}
	
	
	
	
	
	
	
	
	
	
	
}

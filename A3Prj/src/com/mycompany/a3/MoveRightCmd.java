package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MoveRightCmd extends Command
{

	private GameWorld gw;
	
	// Constructor
	public MoveRightCmd(GameWorld gw)
	{
		super("Right");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call mRight method from GameWorld class
		gw.mRight();
	}
	
	
	
}

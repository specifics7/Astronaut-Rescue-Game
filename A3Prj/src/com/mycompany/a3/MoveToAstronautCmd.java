package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MoveToAstronautCmd extends Command
{
	private GameWorld gw;
	
	// Constructor
	public MoveToAstronautCmd(GameWorld gw)
	{
		super("MoveToAstronaut");
		this.gw = gw;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		// Call transferToAstro method from GameWorld class
		gw.transferToAstro();
	}

}

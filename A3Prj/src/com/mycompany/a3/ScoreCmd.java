package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ScoreCmd extends Command
{
	private GameWorld gw;
	
	// Constructor
	public ScoreCmd(GameWorld gw)
	{
		super("Score");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call openDoor method from GameWorld class
		gw.openDoor();
	}
}

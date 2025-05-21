package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MoveDownCmd extends Command
{

	private GameWorld gw;
	
	// Constructor
	public MoveDownCmd(GameWorld gw)
	{
		super("Down");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call mDown method from GameWorld class
		gw.mDown();
	}
}

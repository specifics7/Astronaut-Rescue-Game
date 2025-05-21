package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class MoveLeftCmd extends Command
{
	private GameWorld gw;
	
	public MoveLeftCmd(GameWorld gw)
	{
		super("Left");
		this.gw = gw;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call mLeft method from GameWorld class
		gw.mLeft();
	}
}

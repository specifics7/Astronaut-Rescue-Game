package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCmd extends Command
{
	private Game gameResc;
	
	public PauseCmd(Game gameResc)
	{
		super("Pause");
		
		this.gameResc = gameResc;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gameResc.gamePaused();
	}
	
	
	
	
	
}

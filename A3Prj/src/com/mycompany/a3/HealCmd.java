package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
// Heal command
public class HealCmd extends Command
{

	private GameWorld gw;
	
	public HealCmd(GameWorld gw)
	{
		super("Heal");
		this.gw = gw;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (gw.getIsPaused())
		{
			gw.heal();
		}
	}
	
	
	
	
}

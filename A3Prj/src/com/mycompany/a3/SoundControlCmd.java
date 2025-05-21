package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.CheckBox;
//import com.codename1.ui.Toolbar;

public class SoundControlCmd extends Command
{
	
	private GameWorld gw;
	
	//Constructor
	public SoundControlCmd(GameWorld gw)
	{
		super("Sound ON/OFF");
		this.gw = gw;
	}
	
	// Determine if sound on or off
	public void actionPerformed(ActionEvent e)
	{
		if(((CheckBox) e.getComponent()).isSelected())
		{
			gw.setSound(true);
			System.out.println("Sound turned on\n");
			
		}
		
		else
		{
			gw.pauseSound();
			gw.setSound(false);
			System.out.println("Sound turned off\n");
		}
		
	}
	
}

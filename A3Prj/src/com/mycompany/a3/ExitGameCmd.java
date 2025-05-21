package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;



public class ExitGameCmd extends Command
{

	private GameWorld gw;
	
	// Constructor
	public ExitGameCmd(GameWorld gw)
	{
		super("Exit");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Call exit method from GameWorld class
		Command yesOpt = new Command("Yes");
		Command noOpt = new Command("No");
		
		Label lab = new Label("");
		
		Command c = Dialog.show("Would like to exit the game?", lab, yesOpt, noOpt);
		
		if(c == yesOpt)
		{
			gw.exit();

		}
		
		else if(c == noOpt)
		{
			return;
		}
		
		
	}
}

package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpInfoCmd extends Command

{
	// Constructor
	public HelpInfoCmd()
	{
		super("Help");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Display key bindings of commands
		Dialog.show("Help", "E: Expand door\n"
				+ "C: Contract door\n"
				+ "S: Open door and score\n"
				+ "R: Move spaceship right\n"
				+ "L: Move spaceship left\n"
				+ "U: Move spaceship up\n"
				+ "D: Move spaceship down\n"
				+ "O: Move spaceship to an astronaut location\n"
				+ "A: Move spaceship to an alien location\n"
				+ "W: Create a new alien\n"
				+ "F: Initiate alien-astronaut fight\n"
				+ "T: Ticks clock\n"
				, "Ok", null);

	}
}

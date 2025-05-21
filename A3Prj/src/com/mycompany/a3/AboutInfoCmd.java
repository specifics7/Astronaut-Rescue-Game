package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;

public class AboutInfoCmd extends Command
{
	// Constructor
	public AboutInfoCmd()
	{
		super("About");
		
	}
	// Gives about info about program
	public void actionPerformed(ActionEvent e)
	{
		Dialog.show("About", "The Rescue\n "
				+ "Author Name: Yahir Ocegueda\n "
				+ "Professor: Pinar Muyan-Ozcelik\n "
				+ "Course: CSC133-02\n "
				+ "Version: 2.0", "Ok", null);
	}

	
	
}

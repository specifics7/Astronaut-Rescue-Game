package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ContractDoorCmd extends Command
{
	private GameWorld gw;
	
	// Constructor
	public ContractDoorCmd(GameWorld gw)
	{
		super("Contract");
		this.gw = gw;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		// Call contract method from GameWorld class
		gw.contract();
	}
	
	
	
	

}

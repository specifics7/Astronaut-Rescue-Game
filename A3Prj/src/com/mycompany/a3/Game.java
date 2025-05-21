package com.mycompany.a3;
// import packages
import com.codename1.ui.Form;
// imported for play method
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

import com.codename1.ui.Container;
import com.codename1.ui.Component;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable
{
	// Initialize variables
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	
    private ExpandDoorCmd expandCommand;
    private MoveUpCmd upCommand;
    private MoveLeftCmd leftCommand;
    private MoveToAstronautCmd moveToAstroCommand;
    private ContractDoorCmd contractCommand;
    private MoveDownCmd downCommand;
    private MoveRightCmd rightCommand;
    private MoveToAlienCmd moveToAlienCommand;
    private PauseCmd pauseCommand;
    private SoundControlCmd soundControlCommand;
	
    private Button bExpandCommand;
    private Button bUpCommand;
    private Button bLeftCommand;
    private Button bMoveToAstroCommand;
    private Button bContractCommand;
    private Button bDownCommand;
    private Button bRightCommand;
    private Button bMoveToAlienCommand;
    private Button bScoreCommand;
    private Button bNewAlienCommand;
    private Button bSpaceFightCommand;
    private Button bPauseCommand;
    private Button bHealCommand;
    
    private HealCmd healCommand; 
    
    private ScoreCmd scoreCommand;
    private HelpInfoCmd helpCommand;
    private AboutInfoCmd aboutCommand;
    private ExitGameCmd exitGameCommand;
    
    private CheckBox soundCheckBox = new CheckBox();
	Toolbar toolb = new Toolbar();

	public Game()
	{
	    this.setLayout(new BorderLayout());

	    gw = new GameWorld();
	    mv = new MapView(gw);
	    sv = new ScoreView(gw);

	    gw.addObserver(mv);
	    gw.addObserver(sv);

	    // Create Toolbar
	    this.setToolbar(toolb);
	    toolb.setTitle("The Rescue Game");

	    // Initialize Commands
	    expandCommand = new ExpandDoorCmd(gw);
	    upCommand = new MoveUpCmd(gw);
	    leftCommand = new MoveLeftCmd(gw);
	    moveToAstroCommand = new MoveToAstronautCmd(gw);
	    contractCommand = new ContractDoorCmd(gw);
	    downCommand = new MoveDownCmd(gw);
	    rightCommand = new MoveRightCmd(gw);
	    moveToAlienCommand = new MoveToAlienCmd(gw);
	    pauseCommand = new PauseCmd(this);
	    soundControlCommand = new SoundControlCmd(gw);
	    scoreCommand = new ScoreCmd(gw);
	    helpCommand = new HelpInfoCmd();
	    aboutCommand = new AboutInfoCmd();
	    exitGameCommand = new ExitGameCmd(gw);
	    healCommand = new HealCmd(gw);
	    

	    

	    // Add Commands and Components to Toolbar
	    setupToolbar();

	    // Initialize Buttons
	    bExpandCommand = new Button();
	    bUpCommand = new Button();
	    bLeftCommand = new Button();
	    bMoveToAstroCommand = new Button();
	    bContractCommand = new Button();
	    bDownCommand = new Button();
	    bRightCommand = new Button();
	    bMoveToAlienCommand = new Button();
	    bScoreCommand = new Button();
	    bNewAlienCommand = new Button();
	    bSpaceFightCommand = new Button();
	    bPauseCommand = new Button();
	    bHealCommand = new Button();


	    // Set Commands and Add Buttons
	    setupButtons();

	    // Add MapView and ScoreView
	    this.add(BorderLayout.CENTER, mv);
	    this.add(BorderLayout.NORTH, sv);

	    gw.init();
	    this.show();

	    gw.setMapDimensions(mv.getWidth(), mv.getHeight());

	    this.revalidate();

	    gw.createSounds();
	    timer = new UITimer(this);
	    timer.schedule(120, true, this);
	    
	}
	// Start gameworld tick
	public void run()
	{
		gw.tick();
	}
	
	// Handle pause or unpaused state of game
	public void gamePaused()
	{
		if (gw.getIsPaused() == false)
		{
			bPauseCommand.setText("Play");
			gw.pauseSound();
			
			gw.setIsPaused(true);
			
			timer.cancel();
			
			bHealCommand.setEnabled(true);
			
			bExpandCommand.setEnabled(false);
		    bUpCommand.setEnabled(false);
		    bLeftCommand.setEnabled(false);
		    bMoveToAstroCommand.setEnabled(false);
		    bContractCommand.setEnabled(false);
		    bDownCommand.setEnabled(false);
		    bRightCommand.setEnabled(false);
		    bMoveToAlienCommand.setEnabled(false);
		    bScoreCommand.setEnabled(false);
		    bNewAlienCommand.setEnabled(false);
		    bSpaceFightCommand.setEnabled(false);
		    soundCheckBox.setEnabled(false);
		    
		    toolb.removeCommand(helpCommand);
		    toolb.removeCommand(scoreCommand);
		    toolb.removeCommand(aboutCommand);
		    toolb.removeCommand(exitGameCommand);
		    
		    removeKeyListener('e', expandCommand);
		    removeKeyListener('u', upCommand);
		    removeKeyListener('l', leftCommand);
		    removeKeyListener('o', moveToAstroCommand);
		    removeKeyListener('c', contractCommand);
		    removeKeyListener('d', downCommand);
		    removeKeyListener('r', rightCommand);
		    removeKeyListener('a', moveToAlienCommand);
		    removeKeyListener('s', scoreCommand);			
		}
		
		else
		{
			bPauseCommand.setText("Pause");
						
			gw.playSound();
			
			gw.setIsPaused(false);
			
			timer.schedule(150, true, this);
			
			bHealCommand.setEnabled(false);
			
			bExpandCommand.setEnabled(true);
			bUpCommand.setEnabled(true);
			bLeftCommand.setEnabled(true);
			bMoveToAstroCommand.setEnabled(true);
			bContractCommand.setEnabled(true);
			bDownCommand.setEnabled(true);
			bRightCommand.setEnabled(true);
			bMoveToAlienCommand.setEnabled(true);
			bScoreCommand.setEnabled(true);
			bNewAlienCommand.setEnabled(true);
			bSpaceFightCommand.setEnabled(true);
			soundCheckBox.setEnabled(true);
			
		    toolb.addCommandToRightBar(helpCommand);
			toolb.addCommandToSideMenu(scoreCommand);
		    toolb.addCommandToSideMenu(aboutCommand);
		    toolb.addCommandToSideMenu(exitGameCommand);
			
		    addKeyListener('e', expandCommand);
		    addKeyListener('u', upCommand);
		    addKeyListener('l', leftCommand);
		    addKeyListener('o', moveToAstroCommand);
		    addKeyListener('c', contractCommand);
		    addKeyListener('d', downCommand);
		    addKeyListener('r', rightCommand);
		    addKeyListener('a', moveToAlienCommand);
		    addKeyListener('s', scoreCommand);

		}
		
	}
	// Set up all buttons
	private void setupButtons() {
	    // West Container
	    Container westCont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	    westCont.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
	    bExpandCommand.getAllStyles().setMarginTop(100);
	    bExpandCommand.setCommand(expandCommand);
	    bExpandCommand = buttonStyle(bExpandCommand);
	    westCont.add(bExpandCommand);
	    this.addKeyListener('e', expandCommand);

	    bUpCommand.setCommand(upCommand);
	    bUpCommand = buttonStyle(bUpCommand);
	    westCont.add(bUpCommand);
	    this.addKeyListener('u', upCommand);

	    bLeftCommand.setCommand(leftCommand);
	    bLeftCommand = buttonStyle(bLeftCommand);
	    westCont.add(bLeftCommand);
	    this.addKeyListener('l', leftCommand);

	    bMoveToAstroCommand.setCommand(moveToAstroCommand);
	    bMoveToAstroCommand = buttonStyle(bMoveToAstroCommand);
	    westCont.add(bMoveToAstroCommand);
	    this.addKeyListener('o', moveToAstroCommand);

	    this.add(BorderLayout.WEST, westCont);

	    // East Container
	    Container eastCont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	    eastCont.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
	    bContractCommand.getAllStyles().setMarginTop(100);
	    bContractCommand.setCommand(contractCommand);
	    bContractCommand = buttonStyle(bContractCommand);
	    eastCont.add(bContractCommand);
	    this.addKeyListener('c', contractCommand);

	    bDownCommand.setCommand(downCommand);
	    bDownCommand = buttonStyle(bDownCommand);
	    eastCont.add(bDownCommand);
	    this.addKeyListener('d', downCommand);

	    bRightCommand.setCommand(rightCommand);
	    bRightCommand = buttonStyle(bRightCommand);
	    eastCont.add(bRightCommand);
	    this.addKeyListener('r', rightCommand);

	    bMoveToAlienCommand.setCommand(moveToAlienCommand);
	    bMoveToAlienCommand = buttonStyle(bMoveToAlienCommand);
	    eastCont.add(bMoveToAlienCommand);
	    this.addKeyListener('a', moveToAlienCommand);

	    // Add the missing Score Button to East Container
	    bScoreCommand.setCommand(scoreCommand);
	    bScoreCommand = buttonStyle(bScoreCommand);
	    eastCont.add(bScoreCommand);  // Adding to the container
	    this.addKeyListener('s', scoreCommand); // Adding key listener for score command

	    this.add(BorderLayout.EAST, eastCont);

	    // South Container
	    Container southCont = new Container(new FlowLayout(Component.CENTER));
	    southCont.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));

	    bHealCommand.setCommand(healCommand);
	    bHealCommand = buttonStyle(bHealCommand);
	    bHealCommand.setEnabled(false);
	    southCont.add(bHealCommand);
	    
	    bPauseCommand.setCommand(pauseCommand);
	    bPauseCommand = buttonStyle(bPauseCommand);
	    southCont.add(bPauseCommand);

	    this.add(BorderLayout.SOUTH, southCont);
	}
	
	private void setupToolbar() {
	    // Add Sound CheckBox
	    soundCheckBox.getAllStyles().setBgTransparency(255);
	    soundCheckBox.getAllStyles().setPadding(TOP, 2);
	    soundCheckBox.getAllStyles().setPadding(BOTTOM, 2);
	    soundCheckBox.getAllStyles().setBgColor(ColorUtil.rgb(124, 145, 174));
	    soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
	    soundCheckBox.setCommand(soundControlCommand);
	    toolb.addComponentToSideMenu(soundCheckBox);

	    // Add Commands to Toolbar
	    toolb.addCommandToSideMenu(scoreCommand);
	    toolb.addCommandToSideMenu(aboutCommand);
	    toolb.addCommandToRightBar(helpCommand);
	    toolb.addCommandToSideMenu(exitGameCommand);
	}
	// Method for styling buttons of container
	// Method for styling buttons of container
	private Button buttonStyle(Button obj) {
	    // Padding for button
	    obj.getAllStyles().setPadding(TOP, 5);
	    obj.getAllStyles().setPadding(BOTTOM, 5);
	    
	    // Determine border around the button
	    obj.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.rgb(0, 0, 0)));
	    
	    // Determine opacity for button
	    obj.getAllStyles().setBgTransparency(255);
	    
	    // Set the default background color
	    obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
	    obj.getDisabledStyle().setBgColor(ColorUtil.WHITE);  // White background when disabled

	    // Set the text color
	    obj.getAllStyles().setFgColor(ColorUtil.WHITE);
	    obj.getDisabledStyle().setFgColor(ColorUtil.rgb(0, 0, 255));  // Blue text when disabled

	    return obj;
	}

}	
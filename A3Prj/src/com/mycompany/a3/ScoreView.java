package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.charts.util.ColorUtil;


public class ScoreView extends Container implements Observer
{

	private GameWorld gw;
	
	private Label time;
	private Label score;
	private Label astrosRescued;
	private Label aliensSneaked;
	private Label astrosRemaining;
	private Label aliensRemaining;
	private Label sound;
	
	// Constructor
	public ScoreView(GameWorld gw)
	{
		
		this.gw = gw;
		this.setLayout(new FlowLayout(Component.CENTER));
		
		
		time = createStyledLabel();
		score = createStyledLabel();
		astrosRescued = createStyledLabel();
		aliensSneaked = createStyledLabel();
		astrosRemaining = createStyledLabel();
		aliensRemaining = createStyledLabel();
		sound = createStyledLabel();
		
		this.add(createStyledLabel("Time: "));
		this.add(time);
		this.add(createStyledLabel("Score: "));
		this.add(score);
		this.add(createStyledLabel("Astronauts Rescued: "));
		this.add(astrosRescued);
		this.add(createStyledLabel("Aliens Sneaked In:"));
		this.add(aliensSneaked);
		this.add(createStyledLabel("Astronauts Remaining: "));
		this.add(astrosRemaining);
		this.add(createStyledLabel("Aliens Remaining: "));
		this.add(aliensRemaining);
		this.add(createStyledLabel("Sound: "));
		this.add(sound);
		
	}
	// Label style
	private Label createStyledLabel(String text)
	{
		Label label = new Label(text);
		label.getAllStyles().setFgColor(ColorUtil.rgb(0,  0,  255));
		return label;
	}
	// Overloading label method
	private Label createStyledLabel()
	{
		return createStyledLabel("");
	}
	// Display score view
	public void update(Observable observer, Object data)
	{
		time.setText(" " + gw.getTime());
		score.setText(" " + gw.getScore());
		astrosRescued.setText(" " + gw.getAstrosResc());
		aliensSneaked.setText(" " + gw.getAliensSneaked());
		astrosRemaining.setText(" " + gw.getAstrosRem());
		aliensRemaining.setText(" " + gw.getAliensRem());
		sound.setText(gw.getSound() ? "ON" : "OFF");
		
		this.revalidate();
		
	
	}
}

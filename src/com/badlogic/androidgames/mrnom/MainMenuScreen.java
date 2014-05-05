package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen{
	//The constructor must take an instance of the game
	public MainMenuScreen(Game game){
		super(game);
	}
	
	//This will update our touch event checking
	public void update(float deltaTime){
		Graphics g = game.getGraphics();
		
		//We fetch the touch and key events of the game
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size(); //Create a variable called len that will equal the length of the list
		
		/*
		 * This will iterate through the entire touch list and respond based on the conditions we set below
		 * */
		for(int i=0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			
			//If we lift our finger up in the bound specified below...
			//In most UIs, TOUCH_UP will indicate whether a button has been pressed or not
			if(event.type == TouchEvent.TOUCH_UP){
				//If we lift on the sound button
				if(inBounds(event,0,g.getHeight()-64,64,64)){
					Settings.soundEnabled = !Settings.soundEnabled; //Invert sound setting value
					
					//If sound is enabled when we lift finger, play click sound
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				
				//If we lift on the Highscore button
				if(inBounds(event,64,220+42,192,42)){
					game.setScreen(new HighscoreScreen(game)); //Transition to highscore screen
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				//If we lift on the help screen
				if(inBounds(event,64,220+84,192,42)){
					game.setScreen(new HelpScreen(game)); //Transition to help screen
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}	//Although we don't use key instances we fetch them any to clear the internal buffer
	}
	
	//This will if a certain Touch Event falls within a certain set a bounds
	public boolean inBounds(TouchEvent event, int x, int y, int width, int height){
		if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height -1)
			return true;
		else
			return false;
	}
	
	public void present(float deltaTime){
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0); //Render background at (0,0) We don't need to clear the screen in this case.
		
		//Draw logo and mainmenu assets at the proper place
		g.drawPixmap(Assets.logo, 32,20);
		g.drawPixmap(Assets.mainMenu, 64, 220);
		
		//Draw proper button depending on sound setting
		if(Settings.soundEnabled)
			g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
		else
			g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
	}
	
	//On Game pause, settings will be saved to external drive
	public void pause(){
		Settings.save(game.getFileIO());
	}
	
	public void resume(){
		//Nothing to do on the Main Menu Screen
	}
	
	public void dispose(){
		//Nothing to do on the Main Menu Screen
	}
	
}

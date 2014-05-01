package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen{
	public MainMenuScreen(Game game){
		super(game);
	}
	
	public void update(float deltaTime){
		Graphics g = game.getGraphics();
		List <TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput();
		
		int len = touchEvents.size();
		for(int i=0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(inBounds(event,0,g.getHeight()-64,64,64)){
					Settings.soundEnabled = !Settings.soundEnabled;
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if(inBounds(event,64,220+42,192,42)){
					game.setScreen(new HighscoreScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if(inBounds(event,64,220+84,192,42)){
					game.setScreen(new HelpScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}
	}
}

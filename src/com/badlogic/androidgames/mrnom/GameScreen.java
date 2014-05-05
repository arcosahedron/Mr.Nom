package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen{
	//Define an enumeration that cnodes our 4 states
	enum GameState{
		Ready,
		Running,
		Paused,
		GameOver
	}
	
	GameState state = GameState.Ready; //Define member that holds current state
	World world; //Define World Instance
	int oldScore = 0;
	String score = "0";
	
	public GameScreen(Game game){
		super(game);
		world = new World(); //Creates the world instance
	}
	
	@Override
	public void update(float deltaTime){
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if(state==GameState.Ready)
			updateReady(touchEvents);
		if(state==GameState.Running)
			updateRunning(touchEvents,deltaTime);
		if(state==GameState.Paused)
			updatePaused(touchEvents);
		if(state==GameState.GameOver)
			updateGameOver(touchEvents);
	}
	
	private void updateReady(List < TouchEvent > touchEvents){
		if(touchEvents.size() > 0)
			state = GameState.Running;
	}
	
	private void updateRunning( List < TouchEvent > touchEvents, float deltaTime){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.x < 64 && event.y < 64){
					if(Settings.soundEnabled)
						Assets.click.play(1);
					state = GameState.Paused;
					return;
				}
			}
			
			if(event.type == TouchEvent.TOUCH_DOWN){
				if(event.x < 64 && event.y > 416){
					world.snake.turnLeft();
				}
			}
			
			if(event.type == TouchEvent.TOUCH_DOWN){
				if(event.x > 256 && event.y > 416){
					world.snake.turnRight();
				}
			}
		}
		world.update(deltaTime);
		if(world.gameOver){
			if(Settings.soundEnabled)
				Assets.bitten.play(1);
			state = GameState.GameOver;
		}
		
		if(oldScore != world.score){
			oldScore = world.score;
			score = "" + oldScore;
			if(Settings.soundEnabled)
				Assets.eat.play(1);
		}
	}
	
	//Checks whether one of the menu options was touched and changes state accordingly
	private void updatePaused(List < TouchEvent > touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type==TouchEvent.TOUCH_UP){
				if(event.x > 80 && event.y <= 240){
					if(event.y > 100 && event.y <= 148){
						if(Settings.soundEnabled)
							Assets.click.play(1);
						state = GameState.Running;
						return;
					}
					if(event.y > 148 && event.y < 196){
						if(Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));
						return;
					}
				}
			}
		}
	}
	
	//Check if middle button has been pressed to brig up back to main menu
	private void updateGameOver(List < TouchEvent > touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type ==TouchEvent.TOUCH_UP){
				if(event.x >= 128 && event.x <=192 && event.y >= 200 && event.y <=264){
					if(Settings.soundEnabled)
						Assets.click.play(1);
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}
	
	@Override
	public void present(float deltaTime){
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		
		if(state == GameState.Ready)
			drawReadyUI();
		if(state == GameState.Running)
			drawRunningUI();
		if(state == GameState.Paused)
			drawPausedUI();
		if(state== GameState.GameOver)
			drawGameOverUI();
		
		drawText(g, score, g.getWidth()/2 - score.length()*20, g.getHeight()-42);
	}
	
	public void drawWorld(World world){
		
	}
	
	private void drawReadyUI(){
		
	}
	
	private void drawRunningUI(){
		
	}
	
	private void drawPausedUI(){
		
	}
	
	private void drawGameOverUI(){
		
	}
	
	public void drawText(Graphics g, String line, int x, int y){
		
	}
}

package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game){
		super(game);
	}
	
	//We load the assets and settings
	//For Images we load Pixmaps
	
	public void update(float deltaTime){
		//Load Imagses
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565); //This is the only one that has a format of RGB565
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
		Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
		Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
		Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
		Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
		Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
		Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
		Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
		Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
		Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
		
		//Load Sounds
		Assets.click = game.getAudio().newSound("click.ogg");
		Assets.eat = game.getAudio().newSound("eat.ogg");
		Assets.bitten = game.getAudio().newSound("bitten.ogg");
		
		//Load Settings
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game)); //Set a transition to the main menu screen
	}
	
	
	//The following can be left black since update() will immediately trigger a screen transition
	public void present(float deltaTime){
		
	}
	
	public void pause(){
		
	}
	
	public void resume(){
		
	}
	
	public void dispose(){
		
	}
}

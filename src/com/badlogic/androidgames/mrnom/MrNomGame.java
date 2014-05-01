//This will be the main entry point of our android game
//Also known as the default activity
package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
	//Retrieve from AndroidGame and implement the getStartScreen()
	public Screen getStartScreen(){
		return new LoadingScreen(this); //Will return instance of LoadingScreen Class
	}

}

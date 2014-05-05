package com.badlogic.androidgames.mrnom;

public class Stain {
	//These public static constants encode each stain
	public static final int TYPE_1 = 0;
	public static final int TYPE_2 = 1;
	public static final int TYPE_3 = 2;
	public int x, y;
	public int type;
	
	/*
	 * Each stain has three members:
	 * an x coordinate, a y coordinate, and a type
	 * */
	public Stain(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
}

//NOTICE HOW THE STAIN DOES NOT TOUCH ANYTHING RELATED TO GRAPHICS OR SOUND!
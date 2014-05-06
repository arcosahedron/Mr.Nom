package com.badlogic.androidgames.mrnom;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	/*
	 * Introduce concepts that encode the direction of Mr. Nom
	 * It is done in this manner specifically so that it is easy to turn the snake
	 * Remember, each turn is 90 degrees, which can be encoded to a +-1 formula
	*/
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	public List < SnakePart > parts = new ArrayList < SnakePart > ();
	public int direction;
	
	/*
	 * We Set up mrnom to be composed of his head and two additional tail Parts
	 * We set his default direction to up as well
	 * */
	public Snake(){
		direction = UP;
		parts.add(new SnakePart(5,6));
		parts.add(new SnakePart(5,7));
		parts.add(new SnakePart(5,8));
	}
	
	//Ensure that the right orientation is called
	public void turnLeft(){
		direction +=1;
		if(direction > RIGHT)
			direction = UP;
	}
	
	public void turnRight(){
		direction -=1;
		if(direction < UP)
			direction = RIGHT;
	}
	
	//At eat, a new part will be added in the position of the current end part
	public void eat(){
		SnakePart end = parts.get(parts.size()-1);//Get last part of snake
		parts.add(new SnakePart(end.x, end.y));
	}
	
	public void advance(){
		SnakePart head = parts.get(0);
		
		int len = parts.size() - 1;
		for (int i = len; i > 0 ; i--){
			SnakePart before = parts.get(i-1);
			SnakePart part = parts.get(i);
			part.x = before.x;
			part.y = before.y;
		}
		
		if(direction == UP)
			head.y -=1;
		if(direction == LEFT)
			head.x -=1;
		if(direction == DOWN)
			head.y +=1;
		if(direction == RIGHT)
			head.x +=1;
		
		if(head.x < 0)
			head.x = 9;
		if(head.x > 9)
			head.x = 0;
		if(head.y < 0)
			head.y = 12;
		if(head.y > 12)
			head.y = 0;
	}
	
	//This will determine whether or not the head part and tail part are in the same coordinate.
	public boolean checkBitten(){
		int len = parts.size();
		SnakePart head = parts.get(0);
		for(int i =1; i < len; i++){
			SnakePart part = parts.get(i);
			if(part.x == head.x && part.y == head.y)
				return true;
		}
		return false;
	}
}

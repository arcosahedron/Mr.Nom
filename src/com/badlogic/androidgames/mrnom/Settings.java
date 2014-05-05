package com.badlogic.androidgames.mrnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.androidgames.framework.FileIO;

public class Settings {
	//Default settings will be defined below
	
	//Sound is enabled by default
	public static boolean soundEnabled = true; //This will determine whether sound effects are played back or not
	public static int[] highscores = new int[]{100,80,50,30,10}; //A high scores list is already predefined

	//Algorithm for loading file
	//We will load a settings from a file called .mrnom from external storage
	//If anything goes wrong...we refer to our defaults
	public static void load(FileIO files){
		BufferedReader in = null;
		//If something goes wrong, we revert back to our defaults
		try{
			//We will read through lines in the settings file to look up settings that have been saved
			/*
			 * To parse a boolean means to check through a series of string (text file in this instance)
			 * and check whether ANYTHING in the file will read either as True or False which are the values of a boolean
			 * Since we have only 1 boolean that will be written in our text file, we can rest assured that the True or False
			 * value the program locates will always refer to the soundEnabled variable
			 * */
			in = new BufferedReader(new InputStreamReader(files.readFile(".mrnom")));
			
			soundEnabled = Boolean.parseBoolean(in.readLine());
			
			/*
			 * Now this will do the same thing, except populate highscores array with the first 5 int it returns
			 * */
			
			for (int i=0; i < 5 ; i++){
				highscores[i] = Integer.parseInt(in.readLine());
			}
		} catch(IOException e){
			//:(It's ok we have defaults
		} catch(NumberFormatException e){
			// :/ It's ok, defaults save our day
		} finally{
			try{
				if (in != null)
					in.close();
			} catch (IOException e){
			}
		}
		
		}
	
	/*
	 * This takes current settings and seriealized them to the .mrnom file on external storage
	 * The path of this file is /sdcard/.mrnom
	 * Each setting will be written out on a separate line so that loading can be done on separate lines as well
	*/
	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			//The try statement will only work if loading is successful
			
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrnom")));
			
			/*
			 * This will write the value of the boolean (True or False) to a string 
			 * This will be read later by our load algorithm
			 * */
			out.write(Boolean.toString(soundEnabled)); //Write out the soundenabled setting state to ".mrnom"
			
			/*
			 * This will write the values of the highscores array
			 * Does this overwrite the previous values
			 * ???Does this create a new file EVERY time we make updates to sound or highscore???
			 * */
			for(int i=0; i < 5; i++){
				out.write(Integer.toString(highscores[i]));
			}
		} catch(IOException e){ //If loading fails, revert back to defaults
		} finally{
			try{
				if (out != null)
					out.close();
			} catch(IOException e){
				
			}
		}
		
	}
	
	//This adds a new score and automatically resorts it
	//This method will be called every time a game ends...
	public static void addScore (int score){
		for (int i=0; i < 5; i++){
			if (highscores[i] < score){
				for (int j =4; j > 1; j--)
					highscores[j] = highscores[j-1];
				highscores[i] = score;
				break;
			}
		}
	}
}

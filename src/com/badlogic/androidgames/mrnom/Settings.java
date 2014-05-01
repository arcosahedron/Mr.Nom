package com.badlogic.androidgames.mrnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.androidgames.framework.FileIO;

public class Settings {
	//Default settings will be defined below
	public static boolean soundEnabled = true; //This will determine whether sound effects are played back or not
	public static int[] highscores = new int[]{100,80,50,30,10};

	public static void load(FileIO files){
		BufferedReader in = null;
		//If something goes wrong, we revert back to our defaults
		try{
			in = new BufferedReader(new InputStreamReader(files.readFile(".mrnom")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
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
	
	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrnom")));
			out.write(Boolean.toString(soundEnabled));
			for(int i=0; i < 5; i++){
				out.write(Integer.toString(highscores[i]));
			}
		} catch(IOException e){
		} finally{
			try{
				if (out != null)
					out.close();
			} catch(IOException e){
				
			}
		}
		
	}
	
	//This adds a new score and automatically resorts it
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

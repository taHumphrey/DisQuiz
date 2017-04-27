package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserController {
	ViewController viewControl = new ViewController();
	BufferedReader br;
	FileReader fr;
	BufferedWriter bw;
	FileWriter fw;
	String[] parts;
	String[] scoreParts;
	String userName;
	HashMap<Integer, ArrayList<String>> scoreMap = new HashMap<Integer, ArrayList<String>>();
	
	public UserController(String user){
		saveUser(user);

	}
	public UserController(File f){
		openUser(f);
	}

	public void openUser(File f){
	try{
		fr = new FileReader(f.getAbsolutePath());
		br = new BufferedReader(fr);
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			parseUser(sCurrentLine);
		}
	} 
	catch (IOException e) {
		e.printStackTrace();
	} 
	finally {
		try {
			if (br != null)
				br.close();
			if (fr != null)
				fr.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
			}
		}
	}
	
	public void parseUser(String line){
		parts = line.split(" ");
		if(parts[0].hashCode() == "User:".hashCode()){
			userName = parts[1];
			System.out.println(userName);
		}
		if(parts[0].hashCode() == "MarvelScore:".hashCode()){
			scoreParts = parts[1].split("/");
			setScore("Marvel", scoreParts[0], scoreParts[1]);
		}
		if(parts[0].hashCode() == "MoviesScore:".hashCode()){
			scoreParts = parts[1].split("/");
			setScore("Movies", scoreParts[0], scoreParts[1]);
		}
		if(parts[0].hashCode() == "ParksScore:".hashCode()){
			scoreParts = parts[1].split("/");
			setScore("Parks", scoreParts[0], scoreParts[1]);;
		}
		if(parts[0].hashCode() == "SongsScore:".hashCode()){
			scoreParts = parts[1].split("/");
			setScore("Songs", scoreParts[0], scoreParts[1]);
		}
		if(parts[0].hashCode() == "StarWarsScore:".hashCode()){
			scoreParts = parts[1].split("/");
			setScore("StarWars", scoreParts[0], scoreParts[1]);
		}
	}
	
	public HashMap<Integer, ArrayList<String>> getScore(){
		return scoreMap;
	}
	
	public void setScore(String key, String top, String bottom){
		scoreMap.put(key.hashCode(), new ArrayList<String>(Arrays.asList(top, bottom)));
	}
	
	public void saveUser(String user){
		try {
			fw = new FileWriter(new File("Users/" + user.toLowerCase()).getAbsolutePath());
			bw = new BufferedWriter(fw);
			bw.write("User: " + user 
					+ "\nMarvelScore: " 
					+ viewControl.getScore("Marvel").get(0) + "/" + viewControl.getScore("Marvel").get(1)
					+ "\nMoviesScore: " 
					+ viewControl.getScore("Movies").get(0) + "/" + viewControl.getScore("Movies").get(1)
					+ "\nParksScore: "
					+ viewControl.getScore("Parks").get(0) + "/" + viewControl.getScore("Parks").get(1)
					+ "\nSongsScore: "
					+ viewControl.getScore("Songs").get(0) + "/" + viewControl.getScore("Songs").get(1)
					+ "\nStarWarsScore: "
					+ viewControl.getScore("StarWars").get(0) + "/" + viewControl.getScore("StarWars").get(1));
			} 
		catch (IOException e) {
			e.printStackTrace();
			} 
		finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
				} 
			catch (IOException ex) {
				ex.printStackTrace();
				}
			}
		}
	}

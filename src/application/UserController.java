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
	String userName = "";
	HashMap<Integer, ArrayList<String>> scoreMap = new HashMap<Integer, ArrayList<String>>();
	ArrayList<String> StarWarsScore;
	ArrayList<String> MarvelScore;
	ArrayList<String> MoviesScore;
	ArrayList<String> ParksScore;
	ArrayList<String> SongsScore;
	
	
	
	public UserController(ArrayList<String> StarWarsScore, 
			ArrayList<String> MarvelScore,
			ArrayList<String> MoviesScore,
			ArrayList<String> ParksScore,
			ArrayList<String> SongsScore, String user){
		
		this.StarWarsScore = StarWarsScore;
		this.MarvelScore = MarvelScore;
		this.MoviesScore = MoviesScore;
		this.ParksScore = ParksScore;
		this.SongsScore = SongsScore;
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
			fw = new FileWriter(new File("src/Resources/" + user.toLowerCase()).getAbsolutePath());
			bw = new BufferedWriter(fw);
			bw.write("User: " + user 
					+ "\nMarvelScore: " 
					+ MarvelScore.get(0) + "/" + MarvelScore.get(1)
					+ "\nMoviesScore: " 
					+ MoviesScore.get(0) + "/" + MoviesScore.get(1)
					+ "\nParksScore: "
					+ ParksScore.get(0) + "/" + ParksScore.get(1)
					+ "\nSongsScore: "
					+ SongsScore.get(0) + "/" + SongsScore.get(1)
					+ "\nStarWarsScore: "
					+ StarWarsScore.get(0) + "/" + StarWarsScore.get(1));
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

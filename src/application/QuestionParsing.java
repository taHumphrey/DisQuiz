package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class QuestionParsing {
	
	BufferedReader br;
	FileReader fr;
	HashMap<Integer, ArrayList<String>> qaMap = new HashMap<Integer, ArrayList<String>>();
	String one;
	String two;
	int x = 1;
	
	public QuestionParsing(){
		
	}
	public QuestionParsing(String choice){
		loadFile(choice);
	}
	
	public void parseFile(String line, int n){
		if(n%2 == 0){
			one = line;
		}
		else{
			two = line;
			qaMap.put(x, new ArrayList<String>(Arrays.asList(one, two)));
			System.out.println(qaMap.get(x));
			x+=1;
		}
		
	}
	
	public void loadFile(String choice){
		try {
			fr = new FileReader(choice + ".txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(fr);
			
			int n = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				parseFile(sCurrentLine, n);
				n+=1;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();

			}

		}
	}
}


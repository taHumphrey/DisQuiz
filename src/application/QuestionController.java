package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class QuestionController {
	QuestionParsing qaParse;
	HashMap<Integer, ArrayList<String>> qaMap;
	Random rand = new Random();
	ViewController control;
	ArrayList<Integer> keyNum = new ArrayList<Integer>();
	int j;
	public QuestionController(String choice){
		qaParse = new QuestionParsing(choice);
		qaMap = qaParse.getQuestion();
		for(int i = 1; i < qaMap.size()+1; ++i){
			keyNum.add(i);
		}
		Collections.shuffle(keyNum);
		for(int i = 10; i < qaMap.size(); ++i){
			keyNum.remove(10);
		}
	}
	
	public String setQuestions(){
		try{
			j = keyNum.get(0);
			keyNum.remove(0);
			return qaMap.get(j).get(0);
		}
		catch(IndexOutOfBoundsException e){
			control = new ViewController();
			control.setDone();
			return "Done";
		}
		
	}
	
	public boolean readAnswer(String answer){
		if(answer.toLowerCase().hashCode() == qaMap.get(j).get(1).toLowerCase().hashCode()){
			return true;
		}
		else{
			return false;
		}
	}

}

package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ViewController {
	
	String currentUser = "";
	File f;
	ArrayList<String> userData = new ArrayList<>();
	QuestionController questControl;
	UserController userControl;
	int top = 0;
	int bottom = 0;
	String choice;
	String question;
	String score;
	ArrayList<String> StarWarsScore = new ArrayList<String>();
	ArrayList<String> MarvelScore = new ArrayList<String>();
	ArrayList<String> MoviesScore = new ArrayList<String>();
	ArrayList<String> ParksScore = new ArrayList<String>();
	ArrayList<String> SongsScore = new ArrayList<String>();
	
	@FXML
	Pane startScreen;
	@FXML
	Pane loginPanel;
	@FXML
	Pane createPanel;
	@FXML
	Button loginButton;
	@FXML
	Button newUserButton;	
	@FXML
	Button loginExitButton;
	@FXML
	Button createExitButton;
	@FXML
	Button createSubmit;
	@FXML
	Button loginSubmit;
	@FXML
	TextField createField;
	@FXML
	TextField loginField;


	@FXML
	Pane categoryScreen;
	@FXML
	Pane categoryPanel;
	@FXML
	Button settingButton;	
	@FXML
	Pane settingPanel;
	@FXML
	Button settingExitButton;
	@FXML
	Text helloUserText;
	@FXML
	Button resetDataButton;
	@FXML
	Button saveButton;
	@FXML
	Button logoutButton;
	
	@FXML
	Pane darkTransparent;
	@FXML
	Text errorMessage;
	@FXML
	Pane errorPanel;
	
	@FXML
	Button StarWars;
	@FXML
	Button Marvel;
	@FXML
	Button Parks;
	@FXML
	Button Songs;
	@FXML
	Button Movies;
	
	@FXML
	Text marvelScore;
	@FXML
	Text moviesScore;
	@FXML
	Text parksScore;
	@FXML
	Text songsScore;
	@FXML
	Text starwarsScore;
	
	@FXML
	Pane QAPane;
	@FXML
	Text QAQuestion;
	@FXML
	Text QATitle;
	@FXML
	TextField QAanswerHere;
	@FXML
	TextField QAAnswerField;
	@FXML
	Text QAScore;
	@FXML
	Button submitButton;
	@FXML
	Button doneButton;
	@FXML
	ImageView starWarsQA;
	@FXML
	ImageView marvelQA;
	@FXML
	ImageView moviesQA;
	@FXML
	ImageView parksQA;
	@FXML
	ImageView songsQA;
	
	@FXML
	public void openLogin(){
		loginPanel.setVisible(true);
		createPanel.setVisible(false);
	}
	
	@FXML
	public void openNewLogin(){
		createPanel.setVisible(true);
		loginPanel.setVisible(false);
	}
	
	@FXML
	public void loginExit(){
		loginPanel.setVisible(false);
	}
	
	@FXML
	public void createExit(){
		createPanel.setVisible(false);
	}
	
	@FXML
	public void categoryOpen(){

		if(loginPanel.isVisible()){
			currentUser = loginField.getText();
			loginField.setText("");
			
			if(checkFile(currentUser, 1)){
				categoryScreen.setVisible(true);
				startScreen.setVisible(false);
				loginPanel.setVisible(false);
				createPanel.setVisible(false);
			}
			else{
				showErrorMessage("User was not found. Please enter a new user name.");
			}
			
		}
		else if(createPanel.isVisible()){
			currentUser = createField.getText();
			createField.setText("");
			
			if(checkFile(currentUser, 2)){
				showErrorMessage("User is already created. Please enter a new user name.");
			}
			else{
				categoryScreen.setVisible(true);
				startScreen.setVisible(false);
				loginPanel.setVisible(false);
				createPanel.setVisible(false);
			}
		}
				
	}
	
	@FXML
	public void openSetting(){
		categoryPanel.setVisible(false);
		settingPanel.setVisible(true);
		helloUserText.setText("Hello " + currentUser + "!");
	}
	
	@FXML
	public void settingExit(){
		categoryPanel.setVisible(true);
		settingPanel.setVisible(false);
	}
	
	public void resetData(){
		
	}
	
	@FXML
	public void logOut(){
		currentUser = "";
		settingPanel.setVisible(false);
		categoryPanel.setVisible(true);
		categoryScreen.setVisible(false);
		startScreen.setVisible(true);
		
	}
	
	@FXML
	public void exitErrorMessage(){
		darkTransparent.setVisible(false);
		errorPanel.setVisible(false);
	}
	
	public void showErrorMessage(String newErrorMessage){
		darkTransparent.setVisible(true);
		errorPanel.setVisible(true);
		errorMessage.setText(newErrorMessage);
	}
	
	public void setScoreField(HashMap<Integer, ArrayList<String>> scoreMap){
			marvelScore.setText(scoreMap.get("Marvel".hashCode()).get(0) + "/" + scoreMap.get("Marvel".hashCode()).get(1));
			moviesScore.setText(scoreMap.get("Movies".hashCode()).get(0) + "/" + scoreMap.get("Movies".hashCode()).get(1));
			parksScore.setText(scoreMap.get("Parks".hashCode()).get(0) + "/" + scoreMap.get("Parks".hashCode()).get(1));
			songsScore.setText(scoreMap.get("Songs".hashCode()).get(0) + "/" + scoreMap.get("Songs".hashCode()).get(1));
			starwarsScore.setText(scoreMap.get("StarWars".hashCode()).get(0) + "/" + scoreMap.get("StarWars".hashCode()).get(1));
	}
	
	public boolean checkFile(String userName, int i){
		f = new File("Users/" + userName.toLowerCase());
		if(f.exists() && !f.isDirectory()) { 
			userControl = new UserController(f);
			if(i == 1){
				setScoreField(userControl.getScore());	
			}
		    return true;
		}
		else{
			return false;
		}
	}
	
	@FXML
	public void saveData(){
		userControl = new UserController(currentUser);
	}
	
	@FXML
	public void openStarWars(){
		choice = "Star Wars";
		categoryPanel.setVisible(false);
		categoryScreen.setVisible(false);
		starWarsQA.setVisible(true);
		QAPane.setVisible(true);
		QATitle.setText("DISQUIZ: Star Wars");
		questControl = new QuestionController("StarWars");
		setQuestions();
	}
	@FXML
	public void openMarvel(){
		choice = "Marvel";
		categoryPanel.setVisible(false);
		categoryScreen.setVisible(false);
		marvelQA.setVisible(true);
		QAPane.setVisible(true);
		QATitle.setText("DISQUIZ: Marvel");
		questControl = new QuestionController("Marvel");
		setQuestions();
	}
	@FXML
	public void openParks(){
		choice = "Parks";
		categoryPanel.setVisible(false);
		categoryScreen.setVisible(false);
		parksQA.setVisible(true);
		QAPane.setVisible(true);
		QATitle.setText("DISQUIZ: Parks");
		questControl = new QuestionController("Parks");
		setQuestions();
	}
	@FXML
	public void openSongs(){
		choice = "Songs";
		categoryPanel.setVisible(false);
		categoryScreen.setVisible(false);
		songsQA.setVisible(true);
		QAPane.setVisible(true);
		QATitle.setText("DISQUIZ: Songs");
		questControl = new QuestionController("Songs");
		setQuestions();
	}
	@FXML
	public void openMovies(){
		choice = "Movies";
		categoryPanel.setVisible(false);
		categoryScreen.setVisible(false);
		moviesQA.setVisible(true);
		QAPane.setVisible(true);
		QATitle.setText("DISQUIZ: Movies");
		questControl = new QuestionController("Movies");
		setQuestions();
	}
	
	@FXML
	public void submitAnswer(){
		scoring(questControl.readAnswer(QAAnswerField.getText()));
		QAAnswerField.clear();
		setQuestions();
	}
	
	public void setQuestions(){
		question = questControl.setQuestions();
		QAQuestion.setText(question);
	}
	
	public void setScore(int top, int bottom){
		if(choice != "answer"){
			switch (choice){
			case "Star Wars":
				StarWarsScore.add(Integer.toString(top));
				StarWarsScore.add(Integer.toString(bottom));
				break;
			case "Marvel":
				MarvelScore.add(Integer.toString(top));
				MarvelScore.add(Integer.toString(bottom));
				break;
			case "Movies":
				MoviesScore.add(Integer.toString(top));
				MoviesScore.add(Integer.toString(bottom));
				break;
			case "Parks":
				ParksScore.add(Integer.toString(top));
				ParksScore.add(Integer.toString(bottom));
				break;
			case "Songs":
				SongsScore.add(Integer.toString(top));
				SongsScore.add(Integer.toString(bottom));
				break;
			}
		}
	}
	
	public ArrayList<String> getScore(String choice){
		switch (choice){
		case "Star Wars":
			return StarWarsScore;
		case "Marvel":
			return MarvelScore;
		case "Movies":
			return MoviesScore;
		case "Parks":
			return ParksScore;
		case "Songs":
			return SongsScore;
		default:
			return null;
		}
	}
	
	public void setDone(){
		System.out.println("Done");
		doneButton.setVisible(true);
	}
	
	@FXML
	public void quizDone(){
		categoryScreen.setVisible(true);
		QAPane.setVisible(false);
	}
	
	public void scoring(boolean type){
		if (type){
			top+=1;
			bottom+=1;
			QAScore.setText("  Score:  " + top + "/" + bottom);
		}
		else{
			bottom+=1;
			QAScore.setText("  Score:  " + top + "/" + bottom);
		}
		if(bottom == 10){
			setScore(top, bottom);
		}
	}
}

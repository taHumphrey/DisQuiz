package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
	int x = 0;
	int top = 0;
	int bottom = 0;
	String choice;
	String question;
	String score;
	ArrayList<String> empty = new ArrayList<String>(Arrays.asList("0", "0"));
	ArrayList<String> StarWarsScore = new ArrayList<String>(Arrays.asList("0", "0"));
	ArrayList<String> MarvelScore = new ArrayList<String>(Arrays.asList("0", "0"));
	ArrayList<String> ParksScore  = new ArrayList<String>(Arrays.asList("0", "0"));
	ArrayList<String> SongsScore  = new ArrayList<String>(Arrays.asList("0", "0"));
	ArrayList<String> MoviesScore  = new ArrayList<String>(Arrays.asList("0", "0"));
	
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
	Pane savePane;
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
	Pane quitPane;
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
		marvelScore.setText("0/0");
		MarvelScore.set(0, "0");
		MarvelScore.set(1, "0");
		moviesScore.setText("0/0");
		MoviesScore.set(0, "0");
		MoviesScore.set(1, "0");
		parksScore.setText("0/0");
		ParksScore.set(0, "0");
		ParksScore.set(1, "0");
		songsScore.setText("0/0");
		SongsScore.set(0, "0");
		SongsScore.set(1, "0");
		starwarsScore.setText("0/0");
		StarWarsScore.set(0, "0");
		StarWarsScore.set(1, "0");
	}
	
	@FXML
	public void logOut(){
		savePane.setVisible(true);
	}
	
	@FXML
	public void logoutExit(){
		savePane.setVisible(false);
	}
	
	@FXML
	public void noClick(){
		currentUser = "";
		savePane.setVisible(false);
		settingPanel.setVisible(false);
		categoryPanel.setVisible(true);
		categoryScreen.setVisible(false);
		startScreen.setVisible(true);
	}
	@FXML
	public void yesClick(){
		saveData();
		currentUser = "";
		savePane.setVisible(false);
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
			MarvelScore.set(0, scoreMap.get("Marvel".hashCode()).get(0));
			MarvelScore.set(1, scoreMap.get("Marvel".hashCode()).get(1));
			moviesScore.setText(scoreMap.get("Movies".hashCode()).get(0) + "/" + scoreMap.get("Movies".hashCode()).get(1));
			MoviesScore.set(0, scoreMap.get("Movies".hashCode()).get(0));
			MoviesScore.set(1, scoreMap.get("Movies".hashCode()).get(1));
			parksScore.setText(scoreMap.get("Parks".hashCode()).get(0) + "/" + scoreMap.get("Parks".hashCode()).get(1));
			ParksScore.set(0, scoreMap.get("Parks".hashCode()).get(0));
			ParksScore.set(1, scoreMap.get("Parks".hashCode()).get(1));
			songsScore.setText(scoreMap.get("Songs".hashCode()).get(0) + "/" + scoreMap.get("Songs".hashCode()).get(1));
			SongsScore.set(0, scoreMap.get("Songs".hashCode()).get(0));
			SongsScore.set(1, scoreMap.get("Songs".hashCode()).get(1));
			starwarsScore.setText(scoreMap.get("StarWars".hashCode()).get(0) + "/" + scoreMap.get("StarWars".hashCode()).get(1));
			StarWarsScore.set(0, scoreMap.get("StarWars".hashCode()).get(0));
			StarWarsScore.set(1, scoreMap.get("StarWars".hashCode()).get(1));
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
	public void quitQuiz(){
		quitPane.setVisible(true);
	}
	
	@FXML
	public void yesQuitClick(){
		categoryScreen.setVisible(true);
		categoryPanel.setVisible(true);
		QAPane.setVisible(false);
		quitPane.setVisible(false);
		top = 0;
		bottom = 0;
		x = 0;
	}
	
	@FXML
	public void noQuitClick(){
		quitPane.setVisible(false);
	}
	
	@FXML
	public void saveData(){
		userControl = new UserController(StarWarsScore, MarvelScore, 
				MoviesScore, ParksScore, SongsScore, currentUser);
	}
	
	public void resetQuiz(){
		starWarsQA.setVisible(false);
		marvelQA.setVisible(false);
		parksQA.setVisible(false);
		songsQA.setVisible(false);
		moviesQA.setVisible(false);
		doneButton.setVisible(false);
		submitButton.setVisible(true);
		QAAnswerField.clear();
		QAScore.setText("  Score:  0/0");
	}
	
	@FXML
	public void openStarWars(){
		resetQuiz();
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
		resetQuiz();
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
		resetQuiz();
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
		resetQuiz();
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
		resetQuiz();
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
		if(x == 9){
			doneButton.setVisible(true);
			submitButton.setVisible(false);
		}
		scoring(questControl.readAnswer(QAAnswerField.getText()));
		QAAnswerField.clear();
		setQuestions();
		x+=1;
	}
	
	public void setQuestions(){
		question = questControl.setQuestions();
		QAQuestion.setText(question);
	}
	
	public void setScore(int top, int bottom){
		if(choice != "answer"){
			switch (choice){
			case "Star Wars":
				StarWarsScore.set(0, Integer.toString(top));
				StarWarsScore.set(1, Integer.toString(bottom));
				break;
			case "Marvel":
				MarvelScore.set(0, Integer.toString(top));
				MarvelScore.set(1, Integer.toString(bottom));
				break;
			case "Movies":
				MoviesScore.set(0, Integer.toString(top));
				MoviesScore.set(1, Integer.toString(bottom));
				break;
			case "Parks":
				ParksScore.set(0, Integer.toString(top));
				ParksScore.set(1, Integer.toString(bottom));
				break;
			case "Songs":
				SongsScore.set(0, Integer.toString(top));
				SongsScore.set(1, Integer.toString(bottom));
				break;
			}
		}
	}
	
	public ArrayList<String> getScore(String choice){
		switch (choice){
		case "Star Wars":
			if(StarWarsScore == null){
				return empty;
			}
			return StarWarsScore;
		case "Movies":
			if(MoviesScore == null){
				return empty;
			}
			return MoviesScore;
		case "Parks":
			if(ParksScore == null){
				return empty;
			}
			return ParksScore;
		case "Marvel":
			if(MarvelScore == null){
				return empty;
			}
			return MarvelScore;
		case "Songs":
			if(SongsScore == null){
				return empty;
			}
			return SongsScore;
		default:
			return null;
		}
	}
		
	@FXML
	public void quizDone(){
		categoryScreen.setVisible(true);
		categoryPanel.setVisible(true);
		QAPane.setVisible(false);
		updateScores();
		x = 0;
	}
	
	public void updateScores(){
		switch (choice){
		case "Star Wars":
			starwarsScore.setText(StarWarsScore.get(0) + "/" + StarWarsScore.get(1));
			break;
		case "Marvel":
			marvelScore.setText(MarvelScore.get(0) + "/" + MarvelScore.get(1));
			break;
		case "Movies":
			moviesScore.setText(MoviesScore.get(0) + "/" + MarvelScore.get(1));
			break;
		case "Parks":
			parksScore.setText(ParksScore.get(0) + "/" + ParksScore.get(1));
			break;
		case "Songs":
			songsScore.setText(SongsScore.get(0) + "/" + SongsScore.get(1));
			break;
		}
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
			top = 0;
			bottom = 0;
		}
	}
}

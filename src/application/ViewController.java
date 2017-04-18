package application;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ViewController {
	
	String currentUser = "";
	ArrayList<String> userData = new ArrayList<>();
	
	
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
	Button logoutButton;
	
	@FXML
	Pane darkTransparent;
	@FXML
	Text errorMessage;
	@FXML
	Pane errorPanel;
	
	@FXML
	Button category1;
	@FXML
	Button category2;
	@FXML
	Button category3;
	@FXML
	Button category4;
	@FXML
	Button category5;
	@FXML
	Button category6;
	@FXML
	Button category7;
	


	public void openLogin(){
		loginPanel.setVisible(true);
		createPanel.setVisible(false);
	}
	
	public void openNewLogin(){
		createPanel.setVisible(true);
		loginPanel.setVisible(false);
	}
	
	public void loginExit(){
		loginPanel.setVisible(false);
	}
	public void createExit(){
		createPanel.setVisible(false);
	}
	
	public void categoryOpen(){

		if(loginPanel.isVisible()){
			currentUser = loginField.getText();
			loginField.setText("");
			
			if(checkFile(currentUser)){
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
			
			if(checkFile(currentUser)){
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
	
	public void openSetting(){
		categoryPanel.setVisible(false);
		settingPanel.setVisible(true);
		helloUserText.setText("Hello " + currentUser + "!");
	}
	
	public void settingExit(){
		categoryPanel.setVisible(true);
		settingPanel.setVisible(false);
	}
	
	public void resetData(){
		
	}
	
	public void logOut(){
		currentUser = "";
		settingPanel.setVisible(false);
		categoryPanel.setVisible(true);
		categoryScreen.setVisible(false);
		startScreen.setVisible(true);
		
	}
	
	public void exitErrorMessage(){
		darkTransparent.setVisible(false);
		errorPanel.setVisible(false);
	}
	
	public void showErrorMessage(String newErrorMessage){
		darkTransparent.setVisible(true);
		errorPanel.setVisible(true);
		errorMessage.setText(newErrorMessage);
	}
	
	
	
	
	public boolean checkFile(String userName){
		File f = new File(userName.toLowerCase());
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else{
			return false;
		}
	}
	
	
	
	
	public void openCategory1(){
		System.out.println(currentUser);
	}
	public void openCategory2(){
		
	}
	public void openCategory3(){
		
	}
	public void openCategory4(){
		
	}
	public void openCategory5(){
		
	}
	public void openCategory6(){
		
	}
	public void openCategory7(){
		
	}
}

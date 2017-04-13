package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ViewController {
	
	String currentUser = "";
	
	@FXML
	Pane loginPanel;
	@FXML
	Pane createPanel;
	@FXML
	Button loginExitButton;
	@FXML
	Button newUserButton;
	@FXML
	Pane categoryPane;
	@FXML
	Pane startScreen;
	@FXML
	TextField createField;
	@FXML
	TextField loginField;
	@FXML
	Button settingButton;
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
		categoryPane.setVisible(true);
		startScreen.setVisible(false);

		
		if(loginPanel.isVisible()){
			currentUser = loginField.getText();
		}
		else if(createPanel.isVisible()){
			currentUser = createField.getText();
		}
	}
	
	public void openSetting(){
		
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

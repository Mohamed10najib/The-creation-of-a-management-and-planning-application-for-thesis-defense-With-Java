package application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.*;
public class Student_SpaceController implements Initializable {
	@FXML
    private Label LableNom;
  @FXML
    private Label LableRole;
	

    @FXML
    private Button btnEmploi;

    @FXML
    private Button btnResu;

    @FXML
    private Button btnRapport;

    @FXML
    private Button btnExit;
    @FXML
    private Pane content;

    @FXML
    private Label filePathLabel;
    @FXML
    private Button importButton;


    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TableColumn<?, ?> filiereCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> passCol;

    @FXML
    private TableColumn<?, ?> prenomCol;

    @FXML
    private TableView<?> studentsTable;
    

    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MainsceneController mm=new MainsceneController();
        LableNom.setText(mm.NomU);
        LableRole.setText(mm.RoleU);
		// TODO Auto-generated method stub
		
	}
	public void handle(ActionEvent event) {
		if(event.getSource()==btnEmploi) {
			 Pane menu;
				try {
					menu = FXMLLoader.load(getClass().getResource("Student.fxml"));
					menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

					 content.getChildren().removeAll();
				     content.getChildren().setAll(menu);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}else if(event.getSource()==btnRapport) {
			 Pane menu;
				try {
					menu = FXMLLoader.load(getClass().getResource("ImporterR.fxml"));
					menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

					 content.getChildren().removeAll();
				     content.getChildren().setAll(menu);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}else if(event.getSource()==btnResu) {
		 Pane menu;
			try {
				menu = FXMLLoader.load(getClass().getResource("ajouter_etud.fxml"));
				menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				 content.getChildren().removeAll();
			     content.getChildren().setAll(menu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }else if(event.getSource()==btnExit) {
				 Pane menu;
					try {
						menu = FXMLLoader.load(getClass().getResource("simo.fxml"));
						menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

						 content.getChildren().removeAll();
					     content.getChildren().setAll(menu);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
			}
    	}
	
	    

	    @FXML
	    void importButtonClicked(ActionEvent event) {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Select Report File");



	        Stage stage = (Stage) importButton.getScene().getWindow();
	        java.io.File file = fileChooser.showOpenDialog(stage);

	        if (file != null) {

	            filePathLabel.setText(file.getAbsolutePath());

	            
	        }
	    }
	

   
}

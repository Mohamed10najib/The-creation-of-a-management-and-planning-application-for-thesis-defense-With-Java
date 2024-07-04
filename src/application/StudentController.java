package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentController implements Initializable{
	@FXML
    private Label LableNom;
    @FXML
    private Label LableRole;
	

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private JFXButton btnEmploi;

    @FXML
    private JFXButton btnImporter;

    @FXML
    private AnchorPane content;

    @FXML
    private AnchorPane slider;
    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());

                String url = "jdbc:mysql://localhost:3306/soutenance";
    	        String username = "root";
    	        String pass = "";
                try (Connection connection = DriverManager.getConnection(url, username, "")) {
                   
                    String sql = "INSERT INTO  test_pdf (id,nom,pdf) VALUES (?, ?,?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    	 preparedStatement.setInt(1, 1);
                        preparedStatement.setString(2, selectedFile.getName());
                        preparedStatement.setBytes(3, fileBytes);
                        preparedStatement.executeUpdate();

                        System.out.println("File uploaded successfully!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error uploading file to the database: " + e.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

    @FXML
        public void handle(ActionEvent event) {
    		if(event.getSource()==btnEmploi) {
    			 Pane menu;
    				try {
    					menu = FXMLLoader.load(getClass().getResource("student.fxml"));
    					menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

    					 content.getChildren().removeAll();
    				     content.getChildren().setAll(menu);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			
    		}else if(event.getSource()==btnImporter) {
    			 Pane menu;
    				try {
    					menu = FXMLLoader.load(getClass().getResource("importerRapport.fxml"));
    					menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

    					 content.getChildren().removeAll();
    				     content.getChildren().setAll(menu);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		
    	         }
        	}
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	MainsceneController mm=new MainsceneController();
        LableNom.setText(mm.NomU);
        LableRole.setText(mm.RoleU);

        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        /*slider.setTranslateX(-176);*/
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }

    

}

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class encController implements Initializable {
	@FXML
    private Label LableNom;
  @FXML
    private Label LableRole;
    @FXML
    private TableColumn<Etudiant_enc, String> E_EMAIL;

    @FXML
    private TableColumn<Etudiant_enc, String> E_FiliereEnc;

    @FXML
    private TableColumn<Etudiant_enc, String> E_Nom;

    @FXML
    private TableColumn<Etudiant_enc, String > E_PRENOM;

    @FXML
    private TableColumn<Etudiant_enc, String> E_theme;

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private JFXButton btnexit;

    @FXML
    private AnchorPane content;

    @FXML
    private JFXButton empliobtn;

    @FXML
    private TableView<Etudiant_enc> enc_etu;

    @FXML
    private Label error_label;

    @FXML
    private JFXButton listebtn;

    @FXML
    private AnchorPane slider;
    @FXML
    public void handle(ActionEvent event) {
        if (event.getSource() == empliobtn) {
            Pane menu;
            try {
                menu = FXMLLoader.load(getClass().getResource("emploiProf.fxml"));
                menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

                content.getChildren().removeAll();
                content.getChildren().setAll(menu);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (event.getSource() == btnexit) {
            Pane menu;
            try {
                menu = FXMLLoader.load(getClass().getResource("Mainscene.fxml"));
                menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

                content.getChildren().removeAll();
                content.getChildren().setAll(menu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (event.getSource() == listebtn) {
            Pane menu;
            try {
                menu = FXMLLoader.load(getClass().getResource("emploiProfenc.fxml"));
                menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

                content.getChildren().removeAll();
                content.getChildren().setAll(menu);
            } catch (IOException e) {
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
       

        String sql = "SELECT * FROM  etudiant inner join utilisateur on etudiant.id_etud=utilisateur.id_user inner join soutnance on soutnance.id_etud=etudiant.id_etud  where etudiant.encadreur= ?  ";
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";

        try {
        	Connection con = DriverManager.getConnection(url, username, pass);
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setInt(1, afficherEmploiC.idme );
        	ResultSet resultSet = preparedStatement.executeQuery();
        	 
        	while(resultSet.next()) {
        		E_EMAIL.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        		E_Nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        		E_PRENOM.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        		E_FiliereEnc.setCellValueFactory(cellData -> cellData.getValue().filiereEncProperty());
        		E_theme.setCellValueFactory(cellData -> cellData.getValue().themeProperty());
                
                
               
                
                 String nom = resultSet.getString("nom");
                 String prenom = resultSet.getString("prenom");
                 String email = resultSet.getString("email");
                 String filiere = resultSet.getString("filiere");
                 String theme=resultSet.getString("Theme");
               
                
               
                 
                 Etudiant_enc newStudent = new Etudiant_enc(email, filiere, nom, prenom,theme);
                

               

                
              
                 
                 
                                
               
                 enc_etu.getItems().addAll(newStudent);
                 
        	 }
        }
        catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error adding student: " + e.getMessage());
            }
    }

   

}

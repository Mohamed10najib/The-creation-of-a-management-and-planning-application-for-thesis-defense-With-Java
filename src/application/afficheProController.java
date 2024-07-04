package application;
import javafx.util.Callback;

import java.awt.TextField;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.fxml.Initializable;
public class afficheProController implements Initializable {
	private SharedModelidUpdate sharedModel = new SharedModelidUpdate();
	@FXML
	private Label warning;
	  @FXML
	    private TextField F_name_update;

	    @FXML
	    private TextField L_name_update;
	    @FXML
	    private Label LableNom;
	  @FXML
	    private Label LableRole;
    @FXML
    private TextField departement_update;

    @FXML
    private TextField email_update;

    @FXML
    private TextField experiences_update;

    @FXML
    private TextField password_update;
    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnProffessors;
    @FXML
    private JFXButton btnexit;

    @FXML
    private JFXButton btnStudents;
    @FXML
    private JFXButton BtnSoutenance;

    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane contentUpdate;
    

    @FXML
    private AnchorPane slider;
    
    @FXML
    private TableColumn<CProf, String> EMAIL;
    @FXML
    private TableColumn<CProf, HBox> ACTIONS;
    @FXML
    private TableColumn<CProf, String> NOM;
    @FXML
    private TableColumn<CProf, String> PRENOM;
    @FXML
    private TableColumn<CProf, String> DEPARTEMENT;
    @FXML
    private TableColumn<CProf, String> ANNESEX;

    @FXML
    private TableView<CProf> tableProf;

  
    
    @FXML
    public void handle(ActionEvent event) {
		if(event.getSource()==btnDashboard) {
			 Pane menu;
				try {
					menu = FXMLLoader.load(getClass().getResource("sidebar.fxml"));
					menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

					 content.getChildren().removeAll();
				     content.getChildren().setAll(menu);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}else if(event.getSource()==btnProffessors) {
			 Pane menu;
				try {
					menu = FXMLLoader.load(getClass().getResource("AfficherProfs.fxml"));
					menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

					 content.getChildren().removeAll();
				     content.getChildren().setAll(menu);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}else if(event.getSource()==btnStudents) {
		 Pane menu;
			try {
				menu = FXMLLoader.load(getClass().getResource("AfficherEtudiants.fxml"));
				menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

				 content.getChildren().removeAll();
			     content.getChildren().setAll(menu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }else if(event.getSource()==BtnSoutenance) {
	 		 Pane menu;
				try {
					menu = FXMLLoader.load(getClass().getResource("generateS.fxml"));
					menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

					 content.getChildren().removeAll();
				     content.getChildren().setAll(menu);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }else if(event.getSource()==btnexit) {
			 		 Pane menu;
						try {
							menu = FXMLLoader.load(getClass().getResource("Mainscene.fxml"));
							menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

							 content.getChildren().removeAll();
						     content.getChildren().setAll(menu);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      }
    	}
    public void addTeacher(ActionEvent event) {
   	 Pane menu;
			try {
				menu = FXMLLoader.load(getClass().getResource("AjoutP.fxml"));
				menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

				 content.getChildren().removeAll();
			     content.getChildren().setAll(menu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
        String sql1 = "DELETE FROM utilisateur WHERE id_user = ?";
        String sql2 = "DELETE FROM profs  WHERE id_p = ?";
        String sqljurudelete1 = "DELETE FROM soutnance WHERE id_jury in (SELECT ID FROM jury_membres WHERE prof1=? OR prof2=? OR prof3=? OR encadreur=?)";
        String sqljurudeleteRapport = "DELETE FROM rapport WHERE id_sout in (SELECT id_sout FROM soutnance WHERE id_jury in (SELECT ID FROM jury_membres WHERE prof1=? OR prof2=? OR prof3=? OR encadreur=?))";
        String deletejuryprofs = "DELETE FROM jury_membres WHERE prof1=? OR prof2=? OR prof3=? OR encadreur=?";
        String sql = "SELECT * FROM  profs inner join utilisateur on profs.id_p=utilisateur.id_user ";
        String isexistInsout="SELECT ID FROM jury_membres WHERE prof1=? OR prof2=? OR prof3=? OR encadreur=?";
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";

        try {
        	Connection con = DriverManager.getConnection(url, username, pass);
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	ResultSet resultSet = preparedStatement.executeQuery();
        	 
        	while(resultSet.next()) {
        		EMAIL.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
                NOM.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
                PRENOM.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
                DEPARTEMENT.setCellValueFactory(cellData -> cellData.getValue().departementProperty());
                ANNESEX.setCellValueFactory(cellData -> cellData.getValue().annesProperty());
                
                
               
                
                 String nom = resultSet.getString("nom");
                 String prenom = resultSet.getString("prenom");
                 String email = resultSet.getString("email");
                 String departement = resultSet.getString("departement");
                 String annes=resultSet.getString("exeprience");
                int user_id=resultSet.getInt("id_user");
                

                 
                 CProf newStudent = new CProf(email, nom, prenom, departement,annes);
                

                 ACTIONS.setCellValueFactory(cellData -> {
                     CProf cProf = cellData.getValue();
                     Button updateButton = cProf.getUpdate();
                     Button deleteButton = cProf.getBtn();
                     
                     HBox buttons = new HBox(new Label("       "),updateButton , new Label("       "), deleteButton);
                     return new SimpleObjectProperty<>(buttons);
                 });

                 
              
                 newStudent.getUpdate().setMinSize(50, 10); 
                 newStudent.getUpdate().setMaxSize(120, 60);
               
                 newStudent.getBtn().setMinSize(50, 10); 
                 newStudent.getBtn().setMaxSize(120, 60);
                 
                 newStudent.getBtn().setOnAction(event -> {
                	 try { 
                		 PreparedStatement preparedStatementisexistInsout = con.prepareStatement(isexistInsout);
                		 preparedStatementisexistInsout.setInt(1,user_id);
                		 preparedStatementisexistInsout.setInt(2,user_id);
                		 preparedStatementisexistInsout.setInt(3,user_id);
                		 preparedStatementisexistInsout.setInt(4,user_id);
                		 ResultSet a= preparedStatementisexistInsout.executeQuery();
                		
                		 if(a.next()) {warning.setText("Warning: You are about to delete a teacher who is associated with the thesis defense.");
                		 warning.setTextFill(Color.YELLOW);
                		 }
                		 
                		 
                		 PreparedStatement preparedStatementrapport = con.prepareStatement(sqljurudeleteRapport);
                		 preparedStatementrapport.setInt(1,user_id);
                		 preparedStatementrapport.setInt(2,user_id);
                		 preparedStatementrapport.setInt(3,user_id);
                		 preparedStatementrapport.setInt(4,user_id);
                		 preparedStatementrapport.executeUpdate();
                	 PreparedStatement preparedStatementjury = con.prepareStatement(sqljurudelete1);
                	 preparedStatementjury.setInt(1,user_id);
                	 preparedStatementjury.setInt(2,user_id);
                	 preparedStatementjury.setInt(3,user_id);
                	 preparedStatementjury.setInt(4,user_id);
                	 preparedStatementjury.executeUpdate();
                	 System.out.println("Button clicked for row: 1"+user_id);
                	 PreparedStatement preparedStatementdeletejuryprofs = con.prepareStatement(deletejuryprofs);
                	 preparedStatementdeletejuryprofs.setInt(1,user_id);
                	 preparedStatementdeletejuryprofs.setInt(2,user_id);
                	 preparedStatementdeletejuryprofs.setInt(3,user_id);
                	 preparedStatementdeletejuryprofs.setInt(4,user_id);
                	 preparedStatementdeletejuryprofs.executeUpdate();
                	 System.out.println("Button clicked for row: 2"+user_id);
                	 PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                	 preparedStatement1.setInt(1,user_id);
                	 
                	 preparedStatement1.executeUpdate();
                	 System.out.println("Button clicked for row:3 "+user_id);
                	 PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                	 preparedStatement2.setInt(1,user_id);
                	 preparedStatement2.executeUpdate();
                	 System.out.println("Button clicked for row:4 "+user_id);
                	 
                	 Pane menu;
         			try {
         				menu = FXMLLoader.load(getClass().getResource("AfficherProfs.fxml"));
         				menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

         				 content.getChildren().removeAll();
         			     content.getChildren().setAll(menu);
         			} catch (IOException e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
                	 System.out.println("Button clicked for row: "+user_id);
                	 
                	 }catch(Exception e) {  System.out.println("An exception occurred while processing the button click for row: " + user_id);
                	    e.printStackTrace(); 
                	 
                	 
                	 }
                	
                    
                    
                   
                 });
                 
               
                
                 newStudent.getUpdate().setOnAction(event -> {
                	 
                	 try {
                	 
         			try {
         				
         			
         	            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateP2.fxml"));
         	            Parent menu = loader.load();

         	          
                       sharedModel.setSelectedUserId(user_id);
         	           updatecontroller updateP2Controller = loader.getController();
         	          System.out.println( sharedModel.getSelectedUserId());
         	         System.out.println( "here"+ sharedModel);
         	           updateP2Controller.setSharedModel(sharedModel);

         	           
         	            content.getChildren().removeAll();
         	            content.getChildren().setAll(menu);
         				
         			} catch (IOException e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
                	 System.out.println("Button fff clicked for row: "+user_id);
                	 
                	 }catch(Exception e) { System.out.println("Button clicked for row: "+user_id);}
                	
                    
                    
                     
                 });
                 tableProf.getItems().addAll(newStudent);
                 
        	 }
        }
        catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error adding student: " + e.getMessage());
            }
    }

   

}

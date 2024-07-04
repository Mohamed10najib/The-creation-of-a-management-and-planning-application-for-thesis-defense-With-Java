package application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.fxml.Initializable;

public class afficheEController implements Initializable {
	private SharedModelidUpdate sharedModel = new SharedModelidUpdate();
    @FXML
    private ImageView Exit;
    @FXML
private Label downloadcorrectorno;
    @FXML
    private Label Menu;
    @FXML
    private Label numberroom;
  @FXML
    private Label LableNom;
  @FXML
  private Label LableRole;
    @FXML
    private Label MenuClose;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnProffessors;

    @FXML
    private JFXButton btnStudents;
    @FXML
    private JFXButton BtnSoutenance;
    @FXML
    private JFXButton btnexit;

    @FXML
    private AnchorPane content;

    @FXML
    private AnchorPane slider;
    @FXML
    private TableColumn<Student1, String> EMAIL;
    @FXML
    private TableColumn<Student1, String> NOM;
    @FXML
    private TableColumn<Student1, String> PRENOM;
    @FXML
    private TableColumn<Student1, String> FILIERE;
    @FXML
    private TableColumn<Student1, HBox> ACTIONS;
    @FXML
    private TableColumn<Student1, HBox> ACTIONS1;

    @FXML
    private TableView<Student1> tableEtudaint;
    
    
    
    
    
    
    public void addStudent(ActionEvent event) {
    	 Pane menu;
			try {
				menu = FXMLLoader.load(getClass().getResource("AjoutE.fxml"));
				menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

				 content.getChildren().removeAll();
			     content.getChildren().setAll(menu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    public void Cancel(ActionEvent event) {
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
      }

    
    
    
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
	      }
	else if(event.getSource()==BtnSoutenance) {
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
        String sql2 = "DELETE FROM etudiant  WHERE id_etud = ?";
        String sql77 = "DELETE FROM soutnance  WHERE id_etud = ?";
        String sql = "SELECT * FROM  etudiant inner join utilisateur on etudiant.id_etud=utilisateur.id_user ";
        String sqlpdf = "SELECT * FROM test_pdf where id =1";
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";

        try {
        	Connection con = DriverManager.getConnection(url, username, pass);
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	ResultSet resultSet = preparedStatement.executeQuery();
        	
        	
        	while(resultSet.next()) {
        		
        		int user_id =resultSet.getInt("id_user");
                 String nom = resultSet.getString("nom");
                 String prenom = resultSet.getString("prenom");
                 String email = resultSet.getString("email");
                 String filiere = resultSet.getString("filiere");
                 EMAIL.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
                 NOM.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
                 PRENOM.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
                 FILIERE.setCellValueFactory(cellData -> cellData.getValue().filiereProperty());
              
                 Student1 newStudent = new Student1(email, nom, prenom, filiere);
                 
                 ACTIONS.setCellValueFactory(cellData -> {
                	 Student1 student = cellData.getValue();
                     Button updateButton = student.getUpdate();
                     Button deleteButton = student.getBtn();
                     
                     HBox buttons = new HBox(new Label("     "),updateButton , new Label("     "), deleteButton);
                     return new SimpleObjectProperty<>(buttons);
                 });
                 ACTIONS1.setCellValueFactory(cellData -> {
                	 Student1 student = cellData.getValue();
                     Button updateButton = student.getdownloadpdf();
                    
                     
                     HBox buttons = new HBox(new Label("        "),updateButton , new Label("     "));
                     return new SimpleObjectProperty<>(buttons);
                 });
                 newStudent.getBtn().setOnAction(event -> {
                	 try { PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                	 preparedStatement1.setInt(1,user_id);
                	 PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                	 preparedStatement2.setInt(1,user_id);
                	 PreparedStatement preparedStatement77 = con.prepareStatement(sql77);
                	 preparedStatement77.setInt(1,user_id);
                	 preparedStatement1.executeUpdate();
                	 preparedStatement77.executeUpdate();
                	 preparedStatement2.executeUpdate();
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
                	 System.out.println("Button clicked for row: "+user_id);
                	 
                	 }catch(Exception e) { System.out.println("Button clicked for row: "+user_id);}
                	
                    
                    
                     // Add your specific action code here
                 });
                 newStudent.getdownloadpdf().setOnAction(event -> {
                	    try {
                	        // Replace 'your_table' with the actual table name where PDFs are stored
                	        String query = "SELECT * FROM rapport WHERE id_etud = ?";
                	        
                	        // Replace with the actual ID of the PDF you want to download
                	        int pdfId = user_id;

                	        String DB_URL = "jdbc:mysql://localhost:3306/soutenance";
                	        String DB_USER = "root";
                	        String DB_PASSWORD = "";

                	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                	             PreparedStatement preparedStatement11 = connection.prepareStatement(query)) {

                	            preparedStatement11.setInt(1, pdfId);

                	            try (ResultSet resultSet11 = preparedStatement11.executeQuery()) {
                	                if (resultSet11.next()) {
                	                    FileChooser fileChooser = new FileChooser();
                	                    fileChooser.setTitle("Save PDF File");
                	                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

                	                    File selectedFile = fileChooser.showSaveDialog(null);
                	                    if (selectedFile != null) {
                	                        ResultSetMetaData metaData = (ResultSetMetaData) resultSet11.getMetaData();
                	                        int columnCount = metaData.getColumnCount();

                	                        for (int i = 1; i <= columnCount; i++) {
                	                            System.out.println("Column " + i + ": " + metaData.getColumnName(i));
                	                        }

                	                        byte[] pdfData = resultSet11.getBytes("rapport_pdf");

                	                        try (FileOutputStream fileOutputStream = new FileOutputStream(selectedFile)) {
                	                            fileOutputStream.write(pdfData);
                	                        }
                	                        downloadcorrectorno.setText("PDF Downloaded successfully!");
                	                        downloadcorrectorno.setTextFill(Color.GREEN);
                	                        System.out.println("PDF Downloaded successfully!");
                	                    }
                	                } else {
                	                	  downloadcorrectorno.setText("PDF not found in the database!");
              	                        downloadcorrectorno.setTextFill(Color.RED);
                	                    System.out.println("PDF not found in the database.");
                	                }
                	            }
                	        }

                	    } catch (SQLException | IOException e) {
                	        e.printStackTrace();
                	    }
                	});

                 
                 
   newStudent.getUpdate().setOnAction(event -> {
                	 
                	 try {
                	 
         			try {
         				
         			
         	            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateE2.fxml"));
         	            Parent menu = loader.load();

         	          
                       sharedModel.setSelectedUserId(user_id);
                       updateF2Controller updateF2Controller = loader.getController();
         	          System.out.println( sharedModel.getSelectedUserId());
         	         System.out.println( "here"+ sharedModel);
         	           updateF2Controller.setSharedModel(sharedModel);

         	            // Switch to the new scene
         	            content.getChildren().removeAll();
         	            content.getChildren().setAll(menu);
         				
         			} catch (IOException e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
                	 System.out.println("Button fff clicked for row: "+user_id);
                	 
                	 }catch(Exception e) { System.out.println("Button clicked for row: "+user_id);}
                	
                    
                    
                     // Add your specific action code here
                 });
                 // Set the column to your TableView
              
                 newStudent.getUpdate().setMinSize(10, 10); // Set the minimum size
                 newStudent.getUpdate().setMaxSize(120, 60);
               
                 newStudent.getBtn().setMinSize(10, 10); // Set the minimum size
                 newStudent.getBtn().setMaxSize(120, 60);
                 tableEtudaint.getItems().addAll(newStudent);
                 
        	 }
        }
        catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error adding student: " + e.getMessage());
            }
   	 
    

    }
   
}

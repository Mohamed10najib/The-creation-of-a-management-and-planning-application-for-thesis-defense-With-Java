package application;



import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

public class updatecontroller implements Initializable {
	@FXML
    private Label LableNom;
    @FXML
    private Label LableRole;
	
	 private SharedModelidUpdate sharedModel;

	    @FXML
	    private TextField F_name_update;

	    @FXML
	    private TextField L_name_update;
    @FXML
    private ImageView Exit;

    @FXML
    private ComboBox<String> departement_update;
    @FXML
    private Label label_error;
    @FXML
    private TextField email_update;

    @FXML
    private TextField experiences_update;

    @FXML
    private TextField password_update;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;
private String  checkemail;
    @FXML
    private AnchorPane slider;
    @FXML
    private Button buttonadd;
    @FXML
    private TextField F_name;
    @FXML
    private TextField L_name;
    @FXML
    private TextField experiences;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField filiere;
    @FXML
    private TextField departement;
    
    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnProffessors;

    @FXML
    private JFXButton btnStudents;
    
    @FXML
    private AnchorPane content;

    
    
    
    
    
    
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
    
    public void setSharedModel(SharedModelidUpdate sharedModel) {
        this.sharedModel = sharedModel;
        System.out.println("Selected User ID in UpdateP2Controller: " + this.sharedModel.getSelectedUserId() );
        String sql = "SELECT * FROM profs INNER JOIN utilisateur ON profs.id_p = utilisateur.id_user WHERE utilisateur.id_user = ?";
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";
        try (Connection con = DriverManager.getConnection(url, username, pass);
        	     PreparedStatement preparedStatement = con.prepareStatement(sql)) {

        	    preparedStatement.setInt(1, this.sharedModel.getSelectedUserId());
        	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
        	        while (resultSet.next()) {
        	        	
        	        	departement_update.getItems().addAll("Département Mathématiques et Informatique", "Département Génie Civil Energétique et Environnement (GCEE)");
        	          
        	           
        	        	departement_update.setOnAction(event -> {
        	                String selectedValue = departement_update.getSelectionModel().getSelectedItem();
        	                System.out.println("Selected: " + selectedValue);
        	            });
        	            L_name_update.setText(resultSet.getString("nom"));
        	            F_name_update.setText(resultSet.getString("prenom"));
        	            email_update.setText(resultSet.getString("email"));
        	            checkemail=resultSet.getString("email");
        	            departement_update.setValue(resultSet.getString("departement"));
        	            experiences_update.setText(""+resultSet.getInt("exeprience"));
        	            password_update.setText(resultSet.getString("password"));
        	        }
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	        System.out.println("Error: " + e.getMessage());
        	    }
        	} catch (SQLException e) {
        	    e.printStackTrace();
        	    System.out.println("Error executing SQL query: " + e.getMessage());
        	}

    }

    public void Cancel1(ActionEvent event) {
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
    }
    public void printmethod(ActionEvent event) {
    	
       }
   public void updatedata() {
	   
	   String countsql = "SELECT count(*) FROM utilisateur WHERE email = ?";
	    String sqluser = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, password = ? WHERE id_user = ?";
	    String sqlprof ="UPDATE profs SET  departement = ?, exeprience = ? WHERE id_p = ?";
       String url = "jdbc:mysql://localhost:3306/soutenance";
       String username = "root";
       String pass = "";
       try (Connection con = DriverManager.getConnection(url, username, pass);
    	        PreparedStatement preparedStatement = con.prepareStatement(sqluser);
    	        PreparedStatement preparedStatement1 = con.prepareStatement(sqlprof);
    		   PreparedStatement preparedStatementcount = con.prepareStatement(countsql);) {
    	
    	   if( areFieldsEmpty()) { label_error.setText("Please, one of the inputs is empty");}
    	   else {  preparedStatementcount.setString(1,  email_update.getText());
			ResultSet resultcount = preparedStatementcount.executeQuery();
    		   if (resultcount.next()) {
			        int rowCount = resultcount.getInt(1); 
			       

			       
			        if (rowCount > 0 &&  !email_update.getText().equals(checkemail) ) {
			        	label_error.setText("The email is duplicated !");
			            
			        } else {
    		  
    preparedStatement.setString(1, L_name_update.getText()); 
    preparedStatement.setString(2, F_name_update.getText()); 
    preparedStatement.setString(3, email_update.getText()); 
    preparedStatement.setString(4, password_update.getText()); 

    preparedStatement.setInt(5, this.sharedModel.getSelectedUserId());

    preparedStatement1.setString(1, departement_update.getValue()); 
    preparedStatement1.setString(2, experiences_update.getText()); 

    preparedStatement1.setInt(3, this.sharedModel.getSelectedUserId());

    	    int rowsAffected = preparedStatement.executeUpdate();
    	    int rowsAffected1 = preparedStatement1.executeUpdate();
    	    

    	    if (rowsAffected > 0 || rowsAffected1 > 0) {


    	    	
    	    	
    	    	Pane menu;
     			try {
     				menu = FXMLLoader.load(getClass().getResource("AfficherProfs.fxml"));
     				menu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

     				 content.getChildren().removeAll();
     			     content.getChildren().setAll(menu);
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     				label_error.setText("error");
     			}
            	 
            	
    	    	
    	    	
    	    	
    	    	
    	    } else {
    	        System.out.println("No record updated.");
    	        label_error.setText("error");
    	    }}

    	}}} catch (SQLException e) {

    		 label_error.setText("Error:  experience must be integer");
    	  
    	 

    	}
    	
   }
    	
   private boolean areFieldsEmpty() {
	    return departement_update.getValue().isEmpty()
	            | L_name_update.getText().isEmpty()
	            | F_name_update.getText().isEmpty()
	            | email_update.getText().isEmpty()
	            ||password_update.getText().isEmpty()
	            |experiences_update.getText().isEmpty();
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
    	}
}

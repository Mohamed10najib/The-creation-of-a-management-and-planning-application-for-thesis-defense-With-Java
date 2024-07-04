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

public class updateF2Controller implements Initializable {
	
    @FXML
    private Label label_error;
    @FXML
    private Label LableNom;
    @FXML
    private Label LableRole;
    @FXML
    private ImageView Exit;

    @FXML
    private TextField F_name_update;

    @FXML
    private TextField L_name_update;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnProffessors;

    @FXML
    private JFXButton btnStudents;

    @FXML
    private Button buttonadd;

    @FXML
    private AnchorPane content;

    @FXML
    private TextField email_update;

    @FXML
    private ComboBox<String> filiere_update;

    @FXML
    private TextField password_update;
private String  checkemail ;
    @FXML
    private AnchorPane slider;
    private SharedModelidUpdate sharedModel;
    
 
   
    
    public void setSharedModel(SharedModelidUpdate sharedModel) {
        this.sharedModel = sharedModel;
        
        String sql = "SELECT * FROM  etudiant INNER JOIN utilisateur ON  etudiant.id_etud = utilisateur.id_user WHERE utilisateur.id_user = ?";
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";
        try (Connection con = DriverManager.getConnection(url, username, pass);
        	     PreparedStatement preparedStatement = con.prepareStatement(sql)) {

        	    preparedStatement.setInt(1, this.sharedModel.getSelectedUserId());
        	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
        	        while (resultSet.next()) {
        	        	
              	        	
        	        	filiere_update.getItems().addAll("Info","data","Ai","energie","eau","mecanique","Civile");
              	          
              	            
        	        	filiere_update.setOnAction(event -> {
              	                String selectedValue = filiere_update.getSelectionModel().getSelectedItem();
              	                System.out.println("Selected: " + selectedValue);
        	        	});
        	        	
        	            L_name_update.setText(resultSet.getString("nom"));
        	            F_name_update.setText(resultSet.getString("prenom"));
        	            email_update.setText(resultSet.getString("email"));
        	           filiere_update.setValue(resultSet.getString("filiere"));
        	            password_update.setText(resultSet.getString("password"));
        	            checkemail=resultSet.getString("email");
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
    public void updatedata() {
 	   
    	 String countsql = "SELECT count(*) FROM utilisateur WHERE email = ?";
	    String sqluser = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, password = ? WHERE id_user = ?";
	    String sqlprof ="UPDATE etudiant SET filiere = ? WHERE id_etud = ?";
       String url = "jdbc:mysql://localhost:3306/soutenance";
       String username = "root";
       String pass = "";
       try (Connection con = DriverManager.getConnection(url, username, pass);
    	        PreparedStatement preparedStatement = con.prepareStatement(sqluser);
    	        PreparedStatement preparedStatement1 = con.prepareStatement(sqlprof);
    		   PreparedStatement preparedStatementcount = con.prepareStatement(countsql);) {
    	
    	   if( areFieldsEmpty()) { label_error.setText("Please, one of the inputs is empty");}
    	   else { preparedStatementcount.setString(1,  email_update.getText());
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

    preparedStatement1.setString(1, filiere_update.getValue());

    preparedStatement1.setInt(2, this.sharedModel.getSelectedUserId());

    	    int rowsAffected = preparedStatement.executeUpdate();
    	    int rowsAffected1 = preparedStatement1.executeUpdate();
    	    

    	    if (rowsAffected > 0 || rowsAffected1 > 0) {


    	    	
    	    	
    	    	Pane menu;
     			try {
     				menu = FXMLLoader.load(getClass().getResource("AfficherEtudiants.fxml"));
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
    	    }}}}

    	} catch (SQLException e) {

    		 label_error.setText("Error:  experience must be integer");
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
      	}
    	
   private boolean areFieldsEmpty() {
	    return filiere_update.getValue().isEmpty()
	            | L_name_update.getText().isEmpty()
	            | F_name_update.getText().isEmpty()
	            | email_update.getText().isEmpty()
	            |password_update.getText().isEmpty();
	            
	}
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MainsceneController mm=new MainsceneController();
	    LableNom.setText(mm.NomU);
	    LableRole.setText(mm.RoleU);
		// TODO Auto-generated method stub
		
	}

}

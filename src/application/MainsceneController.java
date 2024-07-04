package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainsceneController {
	private SharedModelID sharedModel1 = new SharedModelID();
	@FXML
	private AnchorPane parent;
	@FXML
	private Pane content;
	@FXML
	private PasswordField password;
	@FXML
	private TextField email;
	@FXML
	private Label incorrect;
	private String role  ;
	public static int id_user;
    public static String NomU;
    public static String RoleU;


	

	
	
	private boolean isValidCredentials(String email, String password) {
		    String sql = "SELECT * from utilisateur ";
		    String url = "jdbc:mysql://localhost:3306/soutenance";
	        String username = "root";
	        String pass = "";
	        try {
	            Connection con = DriverManager.getConnection(url, username, pass);
	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            ResultSet resultSet = preparedStatement.executeQuery();
		        while (resultSet.next()) {
	                
	                String emailcheck = resultSet.getString("email");
	                String passwordcheck = resultSet.getString("password");
	                id_user = resultSet.getInt("id_user");
	                NomU=resultSet.getString("nom")+" "+resultSet.getString("Prenom");
	                RoleU=resultSet.getString("Role");
	                afficherEmploiC.idme=id_user;
	               if(emailcheck.equals(email)&&passwordcheck.equals(password)) { role=resultSet.getString("role");
	               return true;}
	                
	            }
	            
		        
	        } catch (SQLException e) {
	           
	            e.printStackTrace();
	            System.out.println("Error adding student: " + e.getMessage());
	        }
	        
	       
	      
       

	        return false ;

    }
	@FXML
	 public void handel_login(ActionEvent event) {
        String enteredEmail = email.getText();
        String enteredPassword = password.getText();
        
       
       
        
        if (isValidCredentials(enteredEmail, enteredPassword)) {
        	if(role.equals("Admin")){
        		Pane menu;
         		try {
         			menu = FXMLLoader.load(getClass().getResource("sidebar.fxml"));
         			menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         			 parent.getChildren().removeAll();
         		     parent.getChildren().setAll(menu);
         		     
         		} catch (IOException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
                
               }else if(role.equals("Professor")){
        		Pane menu;
         		try {
         			menu = FXMLLoader.load(getClass().getResource("emploiProf.fxml"));
         			menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         			 parent.getChildren().removeAll();
         		     parent.getChildren().setAll(menu);
         		     
         		} catch (IOException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
                
            }else if(role.equals("Student")){

            
        
         		try {
         			
        	          
         			 FXMLLoader loader = new FXMLLoader(getClass().getResource("emploiEtudiant.fxml"));
         	        Parent menu = loader.load();
         	        
         	      
        	
         	        menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         	        parent.getChildren().removeAll();
         	        parent.getChildren().setAll(menu);

         	    

         	       
         			
         		     
         		} catch (IOException e) {
         			
         			e.printStackTrace();
         		}
         	}
        }
         		else{
               
            	incorrect.setText( "Invalid email or password. Please try again."); 
            

        }
        		
       }

    
    
}

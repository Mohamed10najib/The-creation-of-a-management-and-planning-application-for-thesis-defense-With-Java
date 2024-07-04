package application;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.Loader;
import com.itextpdf.text.Image;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Controller implements Initializable {
	private String mytopic=null;
	  @FXML
	    private Label numberroom;
	  @FXML
	    private Label LableNom;
	  @FXML
	    private Label LableRole;

	    @FXML
	    private Label numbersoutenance;

	    @FXML
	    private Label numberstudent;

	    @FXML
	    private Label numberteacher;
	private int  id_soutenance;
	  @FXML
	    private TableColumn<Soutenance1, String> C_Date;

	  @FXML
	    private TableColumn<Soutenance1, String> C_Num;
	   @FXML
	    private TableColumn<Soutenance1, String> C_Theme;
	    @FXML
	    private TableColumn<Soutenance1, String> C_Heure;
	    
	    @FXML
	    private Label labelDownload;
	    
	    @FXML
	    private TableColumn<Soutenance1, String> C_Salle;
	    @FXML
	    private TableColumn<Soutenance1, String> filiereS;
	    
	    @FXML
	    private TableColumn<Soutenance1, String> C_Prof1;

	    @FXML
	    private TableColumn<Soutenance1, String> C_Prof2;

	    @FXML
	    private TableColumn<Soutenance1, String> C_Prof3;

	    @FXML
	    private TableColumn<Soutenance1, String> C_etudiant;
	    @FXML
	    private TableColumn<Soutenance1, String> Colenc;
	    @FXML
	    private TableColumn<Soutenance1, HBox>  D_rapport;
	    @FXML
	    private TableColumn<Soutenance1, HBox>  final_rapport;
	    
	   
	    @FXML
	    private TableView<Soutenance1> soutnences;

	 private SharedModelidUpdate sharedModel;
	 @FXML
		private Button btnGenerate;
	 @FXML
		private Button btnGeneratepdfS;
	    @FXML
	    private TextField F_name_update;

	    @FXML
	    private TextField L_name_update;
	   

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

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
    private ComboBox<String> filiere;
    @FXML
    private ComboBox<String> departement;
    
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
private Button genertepdf;

    @FXML 
    private Label erorrinput;
    private String enc;
    public void showConfirmationDialog() {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            
            System.out.println("User clicked Yes");
            
        } else {
           
            System.out.println("User clicked No or closed the dialog");
            
        }
    }
    public void populateTable() {
    	if(soutnences!=null) {soutnences.getItems().clear();}
    	

        try {
            String url = "jdbc:mysql://localhost:3306/soutenance";
            String utilisateur = "root";
            String motDePasse = "";

            try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            	String query = "SELECT  s.Num_sout,s.salle, s.Theme, s.Date, s.heure,s.id_etud as numetud, " +
                        " CONCAT(utilisateur.Nom, ' ', utilisateur.prenom) AS etudiantName, " +
                        "CONCAT(up1.nom, ' ', up1.prenom) AS prof1Name, " +
                        "CONCAT(up2.nom, ' ', up2.prenom) AS prof2Name, " +
                        "CONCAT(up3.nom, ' ', up3.prenom) AS prof3Name " +
                        "FROM soutnance s " +
                        "JOIN utilisateur ON s.id_etud = utilisateur.id_user " +
                        "JOIN jury_membres j ON s.id_jury = j.ID " +
                        "JOIN utilisateur up1 ON j.prof1 = up1.id_user " +
                        "JOIN utilisateur up2 ON j.prof2 = up2.id_user " +
                        "JOIN utilisateur up3 ON j.prof3 = up3.id_user ";
                String sqlenc = "select CONCAT(nom, ' ', prenom) AS Name from utilisateur where utilisateur.id_user=(select encadreur from etudiant where id_etud= ?) ";
                
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                    	String selectFiliere = "SELECT * FROM etudiant WHERE id_etud = ?";
String filiere="";
                    	try (PreparedStatement preparedStatement1 = connection.prepareStatement(selectFiliere)) {
                    	  
                    	    preparedStatement1.setInt(1, Integer.parseInt(resultSet.getString("numetud")));

                    	    try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
                    	         
                    	        while (resultSet1.next()) {
                    	            filiere = resultSet1.getString("filiere");
                    	        }
                    	    }
                    	} catch (SQLException e) {
                    	    e.printStackTrace();
                    	   
                    	}
                        String juryMembers = resultSet.getString("prof1Name") + "," +
                                resultSet.getString("prof2Name") + "," +
                                resultSet.getString("prof3Name");
                       
                        PreparedStatement preparedStatement1 = connection.prepareStatement(sqlenc);
                        System.out.print(resultSet.getInt("numetud"));
                        preparedStatement1.setInt(1,resultSet.getInt("numetud"));
                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                     
                        while (resultSet1.next()) {  enc=resultSet1.getString("Name");}
                       
                        id_soutenance=Integer.parseInt(resultSet.getString("Num_sout"));
                        Soutenance1 soutenance = new Soutenance1(
                                resultSet.getString("Num_sout"),
                                resultSet.getString("Theme"),
                                resultSet.getString("salle"),
                                resultSet.getString("Date"),
                                resultSet.getString("heure"),
                              
                                resultSet.getString("etudiantName"),
                                juryMembers,
                               enc,"",filiere
                                
                        ); 
if(soutnences!=null) { soutnences.getItems().add(soutenance);}
                       
                    }


                   
                    
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean areFieldsEmpty() {
	    return    email.getText().isEmpty()
	            | password.getText().isEmpty()
	            |F_name.getText().isEmpty()
	            | L_name.getText().isEmpty()
	            |filiere.getValue().isEmpty();
	
    }
    private boolean areFieldsEmpty1() {
	    return    email.getText().isEmpty()
	            | password.getText().isEmpty()
	            |F_name.getText().isEmpty()
	            | L_name.getText().isEmpty()
	            |departement.getValue().isEmpty()|experiences.getText().isEmpty();
	
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
    
    public void setSharedModel(SharedModelidUpdate sharedModel) {
        this.sharedModel = sharedModel;
    }
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
           
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
           
            return false;
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
    	 System.out.println("failed to add profu. No rows affected.");
       }
    @FXML
    public void ajouterPro(ActionEvent event) {
    	
    	 String emailc = email.getText();
         String passwordc = password.getText();
         String Fname = F_name.getText();
         String Lname = L_name.getText();
         String Departement = departement.getValue();
         String experience = experiences.getText();
         String sql = "INSERT INTO utilisateur (nom, prenom, email, password, Role) VALUES (?, ?, ?, ?, ?)";
         String sql1 = "INSERT INTO profs (id_p, departement,exeprience) VALUES (?, ?, ?)";
         String sql2 = "SELECT id_user FROM utilisateur WHERE email = ?";
         String countsql = "SELECT count(*) FROM utilisateur WHERE email = ?";
         String url = "jdbc:mysql://localhost:3306/soutenance";
         String username = "root";
         String pass = "";

         try (Connection con = DriverManager.getConnection(url, username, pass);
              PreparedStatement preparedStatement = con.prepareStatement(sql);
              PreparedStatement preparedStatement1 = con.prepareStatement(sql2);
        		 PreparedStatement preparedStatementcount = con.prepareStatement(countsql);) {
        	 
        	 if(areFieldsEmpty1()) {erorrinput.setText("Please, one of the inputs is empty");
        	 erorrinput.setTextFill(Color.RED);}
        	 else {
        		 preparedStatementcount.setString(1, emailc);
        			ResultSet resultcount = preparedStatementcount.executeQuery();
        			  if (resultcount.next()) {
        			        int rowCount = resultcount.getInt(1); 
        			       

        			       
        			        if (rowCount > 0) {
        			        	erorrinput.setText("The email is duplicated !");
        			        	erorrinput.setTextFill(Color.RED);
        			        } else {
        			        	if(!isNumeric(experience)) {erorrinput.setText("Experience is not a numerical value.");
        			        	erorrinput.setTextFill(Color.RED);}
        			        	else {
             preparedStatement.setString(1, Fname);
             preparedStatement.setString(2, Lname);
             preparedStatement.setString(3, emailc);
             preparedStatement.setString(4, passwordc);
             preparedStatement.setString(5, "Professor");

             int rowsAffected = preparedStatement.executeUpdate();
             if (rowsAffected > 0) {
                 System.out.println("Profu added successfully!");
             } else {
                 System.out.println("failed to add profu. No rows affected.");
                 return;  
             }

            
             preparedStatement1.setString(1, emailc);
             try (ResultSet resultSet = preparedStatement1.executeQuery()) {
                 if (resultSet.next()) {
                     int userId = resultSet.getInt("id_user");

                    
                     try (PreparedStatement preparedStatement2 = con.prepareStatement(sql1)) {
                         preparedStatement2.setInt(1, userId);
                         preparedStatement2.setString(2, Departement);
                         preparedStatement2.setString(3, experience);


                         int rowsAffected1 = preparedStatement2.executeUpdate();
                         if (rowsAffected1 > 0) {
                             System.out.println("prof details added successfully!");
                             Cancel1(event);
                         } else {
                             System.out.println("Failed to add prof details. No rows affected.");
                         }
                     }
                 }
             }
         } }   }}}catch (SQLException e) {
             e.printStackTrace();
             System.out.println("Error adding prof: " + e.getMessage());
         }
     
         }
   
    @FXML
    public void ajouterEtu(ActionEvent event) {
        String emailc = email.getText();
        String passwordc = password.getText();
        String Fname = F_name.getText();
        String Lname = L_name.getText();
        String filiere1 = filiere.getValue();
        String sql = "INSERT INTO utilisateur (nom, prenom, email, password, Role) VALUES (?, ?, ?, ?, ?)";
        String sql1 = "INSERT INTO etudiant (id_etud, filiere) VALUES (?, ?)";
       
        String sql2 = "SELECT id_user FROM utilisateur WHERE email = ?";
        String countsql = "SELECT count(*) FROM utilisateur WHERE email = ?";
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";

        try (Connection con = DriverManager.getConnection(url, username, pass);
             PreparedStatement preparedStatement = con.prepareStatement(sql);
        		PreparedStatement preparedStatementcount = con.prepareStatement(countsql);
             PreparedStatement preparedStatement1 = con.prepareStatement(sql2)) {
if(areFieldsEmpty()) {erorrinput.setText("Please, one of the inputs is empty");
erorrinput.setTextFill(Color.RED);}
else {
	
	preparedStatementcount.setString(1, emailc);
	ResultSet resultcount = preparedStatementcount.executeQuery();
	  if (resultcount.next()) {
	        int rowCount = resultcount.getInt(1); 
	       

	        
	        if (rowCount > 0) {
	        	erorrinput.setText("The email is duplicated");
	        	erorrinput.setTextFill(Color.RED);
	        } else {
	           
	       
            preparedStatement.setString(1, Fname);
            preparedStatement.setString(2, Lname);
            preparedStatement.setString(3, emailc);
            preparedStatement.setString(4, passwordc);
            preparedStatement.setString(5, "Student");

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student. No rows affected.");
                return;  
            }

           
            preparedStatement1.setString(1, emailc);
            try (ResultSet resultSet = preparedStatement1.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id_user");

                   
                    try (PreparedStatement preparedStatement2 = con.prepareStatement(sql1)) {
                        preparedStatement2.setInt(1, userId);
                        preparedStatement2.setString(2, filiere1);

                        int rowsAffected1 = preparedStatement2.executeUpdate();
                        if (rowsAffected1 > 0) {
                            System.out.println("Student details added successfully!");
                            Cancel( event);
                        } else {
                            System.out.println("Failed to add student details. No rows affected.");
                        }
                    }
                }
            } }
	    }}
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    private static int countRows(Connection connection, String tableName) throws SQLException {
        String countQuery = "SELECT COUNT(*) FROM " + tableName;

        try (PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }

        return 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	 String url = "jdbc:mysql://localhost:3306/soutenance";
         String username = "root";
         String pass = "";
         MainsceneController mm=new MainsceneController();
         LableNom.setText(mm.NomU);
         LableRole.setText(mm.RoleU);
        

 if(numberstudent!=null&&numbersoutenance!=null&&numberteacher!=null) {
         try (Connection connection = DriverManager.getConnection(url, username, pass)) {
             int soutenanceCount = countRows(connection, "soutnance");
             int studentCount = countRows(connection, "etudiant");
             int teacherCount = countRows(connection, "profs");

             System.out.println("Number of Soutenances: " + soutenanceCount);
             System.out.println("Number of Students: " + studentCount);
             System.out.println("Number of Teachers: " + teacherCount);
             numbersoutenance.setText(soutenanceCount+"");
             numberstudent.setText(studentCount+"");
             numberteacher.setText(teacherCount+"");
         } catch (SQLException e) {
             e.printStackTrace(); 
         }
     

 }

    	 populateTable();
    	  
    	if(C_Theme!=null) {   C_Theme.setCellValueFactory(cellData -> cellData.getValue().themeProperty());
    	
    	C_Date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        C_Heure.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        C_Salle.setCellValueFactory(cellData -> cellData.getValue().salleProperty());
        C_Prof1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().juryMembersProperty().get().split(",")[0]));
        C_Prof2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().juryMembersProperty().get().split(",")[1]));
        C_Prof3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().juryMembersProperty().get().split(",")[2]));
        C_etudiant.setCellValueFactory(cellData -> cellData.getValue().etudiantNameProperty());
        Colenc.setCellValueFactory(cellData -> cellData.getValue().NomencProperty());
        filiereS.setCellValueFactory(cellData -> cellData.getValue().filiereProperty());
        D_rapport.setCellValueFactory(cellData -> {
       	 
         Button updateButton = new Button("Download Rapport") ;
        
         updateButton.setMinWidth(100);  
         updateButton.setPrefWidth(80); 
         updateButton.setMaxWidth(80); 

         updateButton.setOnAction(event -> {
            
        	 try {
     	        
     	        String query = "SELECT * FROM rapport WHERE id_sout = ?";
     	        
     	       
     	        int pdfId = Integer.parseInt(cellData.getValue().idProperty().get());

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
     	                       labelDownload.setTextFill(Color.GREEN);
               		        labelDownload.setText( "PDF Downloaded successfully!");
     	                       
     	                    }
     	                } else {
     	                	labelDownload.setTextFill(Color.RED);
               		        labelDownload.setText( "The student did not submit his report; please try again later.");
     	                    System.out.println("PDF not found in the database.");
     	                }
     	            }
     	        }

     	    } catch (SQLException | IOException e) {
     	        e.printStackTrace();
     	    }
         });
         HBox buttons = new HBox(new Label("    "),updateButton , new Label("  "));
         return new SimpleObjectProperty<>(buttons);
        
     });
        final_rapport.setCellValueFactory(cellData -> {
        	String sqlnotes = "SELECT * FROM notes WHERE id_sout = ?" ;
        	 
            Button updateButton = new Button("Download Final") ;
           
            updateButton.setMinWidth(100); 
            updateButton.setPrefWidth(80); 
            updateButton.setMaxWidth(80);  
            String idValue = cellData.getValue().idProperty().get();

            
            int id = Integer.parseInt(idValue);

            
           
            updateButton.setOnAction(event -> {
            	try (Connection con = DriverManager.getConnection(url, username, pass);) {
            		 PreparedStatement preparedStatement = con.prepareStatement(sqlnotes);
       			  preparedStatement.setInt(1, id);
       			 ResultSet resultSet = preparedStatement.executeQuery();
            		    int rowCount = 0;
            		    Map<Integer, Double> professorNotes = new HashMap<>();
double notec=0;
double noteM=0;
double noteS=0;
            		    while (resultSet.next()) {
            		        
            		    	int column1Value = Integer.parseInt(resultSet.getString("id_prof")) ;
            		    	Double column1Value1 =Double.parseDouble(resultSet.getString("id_prof"));
            		        professorNotes.put(column1Value, column1Value1);
            		        
            		        System.out.println(column1Value );
notec=notec+Double.parseDouble(resultSet.getString("note"));
noteM=noteM+Double.parseDouble(resultSet.getString("noteM"));
noteS=noteS+Double.parseDouble(resultSet.getString("noteS"));

            		        rowCount++;
            		    }

            		   
            		    if (rowCount == 3) {
            		    	cellData.getValue().etudiantNameProperty();
            		        System.out.println("Number of rows: " + rowCount);
            		        notec=notec/3;
            		        noteM=noteM/3;
            		        noteS=noteS/3;
            		        Random random = new Random();
            		     
            		       

            		        labelDownload.setTextFill(Color.GREEN);
            		        labelDownload.setText( "Download successful");
                	        
                	        int randomNumber = random.nextInt();
                		    modifyPdf(randomNumber+""+id, cellData.getValue().etudiantNameProperty(),cellData.getValue().NomencProperty(),cellData.getValue().juryMembersProperty().get().split(",")[0],cellData.getValue().juryMembersProperty().get().split(",")[1],cellData.getValue().juryMembersProperty().get().split(",")[2],notec,noteM,noteS,cellData.getValue().themeProperty().get());
            		    }
            		    else {

            		    	  labelDownload.setTextFill(Color.RED);
            		 labelDownload.setText("You don't have all notes; please wait for some time");
            		    	System.out.println("Number of rows: " + rowCount+"id"+id);
            		    }
            		} catch (Exception e) {
            		    e.printStackTrace();
            		}
               
                System.out.println("Button clicked 2!"+cellData.getValue().idProperty());
            });
            HBox buttons = new HBox(new Label("    "),updateButton , new Label("  "));
            return new SimpleObjectProperty<>(buttons);
           
        });
    
       
        
}
    	if(departement!=null) {departement.getItems().addAll("Département Mathématiques et Informatique", "Département Génie Civil Energétique et Environnement (GCEE)");
    	departement.setValue("Département Mathématiques et Informatique");}
    	
      	if(filiere!=null) {filiere.getItems().addAll("Info","data","Ai","energie","eau","mecanique","Civile");
      	filiere.setValue("Info");}
    	if (sharedModel!=null) {int  selectedUserId = sharedModel.getSelectedUserId();
        System.out.println("Selected User ID in UpdateP2Controller: " + selectedUserId);
} 
    	
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
       
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
    
    public void genererpdfS(ActionEvent event) {

    	        try {
    	            String url = "jdbc:mysql://localhost:3306/soutenance";
    	            String username = "root";
    	            String pass = "";
    	            String sql ="SELECT  s.Num_sout,s.salle, s.Theme, s.Date, s.heure,s.id_etud as numetud, " +
                            " CONCAT(utilisateur.Nom, ' ', utilisateur.prenom) AS etudiantName, " +
                            "CONCAT(up1.nom, ' ', up1.prenom) AS prof1Name, " +
                            "CONCAT(up2.nom, ' ', up2.prenom) AS prof2Name, " +
                            "CONCAT(up3.nom, ' ', up3.prenom) AS prof3Name " +
                            "FROM soutnance s " +
                            "JOIN utilisateur ON s.id_etud = utilisateur.id_user " +
                            "JOIN jury_membres j ON s.id_jury = j.ID " +
                            "JOIN utilisateur up1 ON j.prof1 = up1.id_user " +
                            "JOIN utilisateur up2 ON j.prof2 = up2.id_user " +
                            "JOIN utilisateur up3 ON j.prof3 = up3.id_user ";
    	            Connection con = DriverManager.getConnection(url, username, pass);
    	            PreparedStatement preparedStatement = con.prepareStatement(sql);
    	            ResultSet resultSet = preparedStatement.executeQuery(sql);
    	            Random random = new Random();
    	            int randomNumber = random.nextInt();
    	            String namefile="soutenance_"+randomNumber;
    	            String file_name = "C:\\Users\\dell\\Desktop\\pfdGenere\\"+namefile+".pdf";
    	            Document doc = new Document();
    	            doc.setPageSize(PageSize.A4);
    	            
    	            
    	            PdfWriter.getInstance(doc, new FileOutputStream(file_name));
    	            doc.open();
    	            Image logo = Image.getInstance("C:\\Users\\dell\\Documents\\login\\src\\application\\images\\Univlogo.png"); 
    	            logo.scaleToFit(90, 90); 
    	            logo.setAlignment(Image.ALIGN_LEFT);
    	            doc.add(logo);
    	            doc.add(new Paragraph(" "));

    	            Image logoe = Image.getInstance("C:\\Users\\dell\\Documents\\login\\src\\application\\images\\logoecole1.png");
    	            logoe.scaleToFit(50, 50);
    	            logoe.setAbsolutePosition(doc.right() - logoe.getScaledWidth(), doc.top() - logoe.getScaledHeight());

    	            logo.setAlignment(Image.ALIGN_RIGHT);
    	            doc.add(logoe);
    	            
    	            
    	            Paragraph p = new Paragraph("Le planing des soutenances de l'année 2024/2023 ");
    	            p.setAlignment(Element.ALIGN_CENTER);
    	            p.setSpacingAfter(20f); 
    	            doc.add(p);
    	            PdfPTable table = new PdfPTable(7);
    	            table.setTotalWidth(500); 
    	            table.setLockedWidth(true);
    	            PdfPCell c1 = new PdfPCell(new Paragraph("Soutenance"));
    	            table.addCell(c1);
    	            PdfPCell c2 = new PdfPCell(new Paragraph("Theme"));
    	            table.addCell(c2);
    	            PdfPCell c3 = new PdfPCell(new Paragraph("salle"));
    	            table.addCell(c3);
    	            PdfPCell c4 = new PdfPCell(new Paragraph("heure"));
    	            table.addCell(c4);
    	            PdfPCell c5 = new PdfPCell(new Paragraph("Date"));
    	            table.addCell(c5);
    	            PdfPCell c6 = new PdfPCell(new Paragraph("Etudiant"));
    	            table.addCell(c6);
    	            PdfPCell c7 = new PdfPCell(new Paragraph("Jury"));
    	            table.addCell(c7);
    	            table.setHeaderRows(1);

    	           
    	            while (resultSet.next()) {
    	            	String juryNames = resultSet.getString("prof1Name") + ", " +
    	                         resultSet.getString("prof2Name") + ", " +
    	                         resultSet.getString("prof3Name");
    	                table.addCell(resultSet.getString("Num_sout"));
    	                table.addCell(resultSet.getString("Theme"));
    	                table.addCell(resultSet.getString("salle"));
    	                table.addCell(resultSet.getString("heure"));
    	                table.addCell(resultSet.getString("Date"));
    	                table.addCell(resultSet.getString("etudiantName"));
    	                PdfPCell juryCellData = new PdfPCell(new Paragraph(juryNames));
    	               
    	                juryCellData.setColspan(3); 
    	                table.addCell(juryCellData);
    	                juryCellData.setMinimumHeight(20f); 


    	                
    	            }

    	            doc.add(table);
    	            doc.close();
    	            System.out.println("PDF created successfully");
    	        } catch (FileNotFoundException e) {
    	            e.printStackTrace();
    	        } catch (DocumentException e) {
    	            e.printStackTrace();
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        } catch (MalformedURLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}

    	    
    
    	
    }
    public  void modifyPdf(String namefile,StringProperty Nameetud,StringProperty stringProperty,String stringPropertyprof1,String stringPropertyprof2,String stringPropertyprof3,double notec ,double noteM,double noteS,String theme) throws IOException {
        File inputFile = new File("C:\\Users\\dell\\Downloads\\Fiche_Evaluation_PFE.pdf");
        File outputFile = new File("C:\\Users\\dell\\Documents\\"+namefile+".pdf");

        try (PDDocument document = Loader.loadPDF(inputFile)) {

           
            PDPage firstPage = document.getPage(0);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, firstPage, PDPageContentStream.AppendMode.APPEND, true, true)) {
              
            	contentStream.beginText();
            	contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
            	contentStream.newLineAtOffset(100, 652);
            	contentStream.showText(Nameetud.get());
            
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(100, 608);
            	
            	
            	contentStream.showText(theme);
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(100, 560);
            	
            	
            	contentStream.showText(stringProperty.get());
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(150, 515);
            	
            	
            	contentStream.showText(stringPropertyprof3);
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(150, 493);
            	
            	
            	contentStream.showText(stringPropertyprof2);
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(150, 473);
            	
            	
            	contentStream.showText(stringPropertyprof1);
            	contentStream.endText();
            	
            	
            	
            	contentStream.beginText();
            	contentStream.newLineAtOffset(150, 279);
            	DecimalFormat decimalFormat = new DecimalFormat("#.##");

                
            	String notecSt = decimalFormat.format(notec);
            	
            	contentStream.showText(""+notecSt);
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(150, 233);
            	String noteMSt = decimalFormat.format(noteM);
            	
            	contentStream.showText(""+noteMSt);
            	contentStream.endText();
            	contentStream.beginText();
            	contentStream.newLineAtOffset(150, 182);
            	
            	String noteSSt = decimalFormat.format(noteS);
            	contentStream.showText(""+noteSSt);
            	contentStream.endText();
            	
            	contentStream.beginText();
            	contentStream.newLineAtOffset(110, 78);
            	
            	double moyenne =notec*0.5+noteM*0.2+noteS*0.3;
            	String moyenneSt = decimalFormat.format(moyenne);
            	contentStream.showText(""+moyenneSt);
            	contentStream.endText();


            	contentStream.close();
            }
            PDPage SecondPage = document.getPage(1);

            try (PDPageContentStream contentStream1 = new PDPageContentStream(document, SecondPage, PDPageContentStream.AppendMode.APPEND, true, true)) {
            	contentStream1.beginText();
            	contentStream1.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
            	contentStream1.newLineAtOffset(210, 712);
            	
            	
            	contentStream1.showText(stringPropertyprof3);
            	contentStream1.endText();

            	contentStream1.beginText();
            	contentStream1.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            	contentStream1.newLineAtOffset(350, 712);
            	
            	
            	contentStream1.showText(stringPropertyprof2);
            	contentStream1.endText();

            	contentStream1.beginText();
            	contentStream1.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            	contentStream1.newLineAtOffset(280, 700);
            	
            	
            	contentStream1.showText(stringPropertyprof1);
            	contentStream1.endText();

            	contentStream1.close();
            }
            
            document.save(outputFile);
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
    
	

	
	
	public void genereS(ActionEvent event) {
		
		 int result = JOptionPane.showConfirmDialog(
	                null,
	                "Are you sure?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION
	        );

	        if (result == JOptionPane.YES_OPTION) {
	           
	            System.out.println("User clicked Yes");
	            
	        

	        String url = "jdbc:mysql://localhost:3306/soutenance";
	        String username = "root";
	        String pass = "";
	        String sqldeletenote="delete from notes ";
	        String sqldeleterapport="delete from rapport ";
	        String sqldeletesoutnance="delete from soutnance ";
	        String sql = "SELECT id_etud FROM etudiant where filiere='Info' or filiere='data' or filiere='Ai' order by filiere";
	        String sql2 = "SELECT id_etud FROM etudiant where filiere='Civile' or filiere='mecanique' or filiere='eau'  or filiere='energie' order by filiere";
	        String sql3 = "SELECT ID FROM jury_membres where departement='Département Mathématiques et Informatique'";
	        String sql4 = "SELECT ID FROM jury_membres where departement='Département Génie Civil Energétique et Environnement (GCEE)'";
	        String sqlInsertSoutenance = "INSERT INTO soutnance (Theme, salle, heure, Date, id_jury, id_etud) VALUES (?,?, ?, ?, ?, ?)";


	        
	        try {
	        	combin coo=new combin();
	        	coo.main11(new String[]{});
	            Connection con = DriverManager.getConnection(url, username, pass);
	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
	            PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
	            PreparedStatement preparedStatement4 = con.prepareStatement(sql4);
	            PreparedStatement preparedStatementNotes = con.prepareStatement(sqldeletenote);
	            PreparedStatement sqldeletesoutnanceprapre = con.prepareStatement(sqldeletesoutnance);
	            PreparedStatement sqldeleterapportprapre = con.prepareStatement(sqldeleterapport);
	            PreparedStatement preparedStatementInsertSoutenance = con.prepareStatement(sqlInsertSoutenance);

	            
	            ResultSet resultSet = preparedStatement.executeQuery(sql);
	            ResultSet resultSet2 = preparedStatement2.executeQuery(sql2);
	            ResultSet resultSet3 = preparedStatement3.executeQuery(sql3);
	            ResultSet resultSet4 = preparedStatement4.executeQuery(sql4);
	            int rowsAffected = preparedStatementNotes.executeUpdate();
	            int rowsAffectedrapport = sqldeleterapportprapre.executeUpdate();
	            int rowsAffectedsout = sqldeletesoutnanceprapre.executeUpdate();
	         
	            ArrayList<Integer> idList = new ArrayList<>();
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id_etud");
	                idList.add(id);
	            }
	            ArrayList<Integer> idList2 = new ArrayList<>();
	            while (resultSet2.next()) {
	                int id = resultSet2.getInt("id_etud");
	                idList2.add(id);
	            }
	            ArrayList<Integer> idList3 = new ArrayList<>();
	            while (resultSet3.next()) {
	                int id = resultSet3.getInt("ID");
	                idList3.add(id);
	            }
	            ArrayList<Integer> idList4 = new ArrayList<>();
	            while (resultSet4.next()) {                                                                                                                                                                                                                                                                                       
	                int id = resultSet4.getInt("ID");
	                idList4.add(id);
	            }
	            
	            String selectSQL6 = "SELECT count(*) FROM profs WHERE departement='Département Génie Civil Energétique et Environnement (GCEE)'";
	            String selectSQL5 = "SELECT count(*) FROM profs WHERE departement='Département Mathématiques et Informatique'";
	            String selectSQL8 = "UPDATE etudiant SET encadreur = ? WHERE id_etud = ?";
	            String selectSQL9 = "SELECT encadreur FROM `jury_membres` WHERE ID = ?";
	            PreparedStatement selectStatement6 = con.prepareStatement(selectSQL6);
	            PreparedStatement selectStatement5 = con.prepareStatement(selectSQL5);
	            ResultSet resultSet6 = selectStatement6.executeQuery();
	            ResultSet resultSet5 = selectStatement5.executeQuery();
	            int countCivile = 0;
	            int countMaths = 0;
	            int nb=0,sb=0,jb=0,pb=0;

	            if (resultSet6.next()) {
	                countCivile = resultSet6.getInt(1);
	                nb=(2*countCivile/3);
	                jb=countCivile-nb;
	            }

	            if (resultSet5.next()) {
	                countMaths = resultSet5.getInt(1);
	                sb=(2*countMaths/3);
	                pb=countMaths-sb;
	            }
	            
	            System.out.println(jb);
	            System.out.println(nb);
	            System.out.println(pb);
	            System.out.println(sb);



	           int n=idList3.size();
	           int oo=idList.size();
	           int p=idList4.size();
	           int r=idList2.size();

	            
	            int j=0;
	            int i=1;
	            int h=0;
	            int u=0;
	            int x=0;
	            LocalDate currentDate = LocalDate.parse("2023-01-01"); 
	            while(true) {
	            for(int k=0;k<pb;k++){
	            	 preparedStatementInsertSoutenance.setString(1, " "); 
	            	 preparedStatementInsertSoutenance.setInt(2, i); 
	                 preparedStatementInsertSoutenance.setInt(3, k+1); 
	                 preparedStatementInsertSoutenance.setString(4, currentDate.toString()); 
	                 preparedStatementInsertSoutenance.setInt(5, idList3.get(u)); 
	                
	                 
	                 preparedStatementInsertSoutenance.setInt(6,idList.get(j) ); 
	                 PreparedStatement selectStatement9 = con.prepareStatement(selectSQL9);
	                 selectStatement9.setInt(1,idList3.get(u));
	                 ResultSet resultSet9= selectStatement9.executeQuery();
	                 if (resultSet9.next()) {
	 	               int idenc = resultSet9.getInt("encadreur");
	 	              PreparedStatement selectStatement8 = con.prepareStatement(selectSQL8);
		                 selectStatement8.setInt(1, idenc);
		                 selectStatement8.setInt(2, idList.get(j));
		                 selectStatement8.executeUpdate();
	 	            }
	                 preparedStatementInsertSoutenance.executeUpdate();
	            	       System.out.println("letudiant "+idList.get(j)+" a une soutnance a la salle  "+(k+1)+" avec la Jury  "+ idList3.get(u)+" a Heur "+i);
	            	        j++;
	            	        u++;
	            	        if(u==n) {
	            	        	u=0;
	            	        }
	            	        if(j==oo) {
	     	            	   break;
	     	               }
	               }
	               i++;
	               if(i>8) {
	            	   i=1;
	            	   currentDate = currentDate.plusDays(1);}
	               if(j==oo) {
	            	   break;
	               }
	            }
	            
	            while(true) {
		            for(int k=0;k<jb;k++){
		            	 preparedStatementInsertSoutenance.setString(1, " "); 
		            	 preparedStatementInsertSoutenance.setInt(2, i); 
		                 preparedStatementInsertSoutenance.setInt(3, k+1); 
		                 preparedStatementInsertSoutenance.setString(4, currentDate.toString()); 
		                 preparedStatementInsertSoutenance.setInt(5, idList4.get(x)); 
		                 preparedStatementInsertSoutenance.setInt(6,idList2.get(h) ); 
		                 PreparedStatement selectStatement10 = con.prepareStatement(selectSQL9);
		                 selectStatement10.setInt(1,idList4.get(x));
		                 ResultSet resultSet10= selectStatement10.executeQuery();
		                 if (resultSet10.next()) {
		 	               int idenc = resultSet10.getInt("encadreur");
		 	              PreparedStatement selectStatement11 = con.prepareStatement(selectSQL8);
		 	             selectStatement11.setInt(1, idenc);
		 	            selectStatement11.setInt(2, idList2.get(h));
		 	           selectStatement11.executeUpdate();
		 	            }
		                 preparedStatementInsertSoutenance.executeUpdate();
		            	       System.out.println("letudiant "+idList2.get(h)+" a une soutnance a la salle  "+(k+1)+" avec la Jury  "+ idList4.get(x)+" a Heur "+i);
		            	        h++;
		            	        x++;
		            	        if(x==p) {
		            	        	x=0;
		            	        }
		            	        if(h==r) {
		 		            	   break;
		 		               }
		               }
		            i++;
 	               if(i>8) {
 	            	   i=1;
 	            	   currentDate = currentDate.plusDays(1);}
		               if(h==r) {
		            	   break;
		               }
		            }
	        
	            	        
	            preparedStatement.close();
	            preparedStatement2.close();
	            preparedStatement3.close();
	            preparedStatement4.close();
	            selectStatement6.close();
	            selectStatement5.close();
	            preparedStatementInsertSoutenance.close();
	            con.close();
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
	        } catch (Exception e) {
	        	System.out.println("Error you don't have all data, please enter more professor" );
	        	labelDownload.setTextFill(Color.RED);
   		        labelDownload.setText( "Error you don't have all data, please enter more professor");
	           
	        }
	}
	  
	        else {
	           
	            System.out.println("User clicked No or closed the dialog");
	            
	        }
	}
	
}

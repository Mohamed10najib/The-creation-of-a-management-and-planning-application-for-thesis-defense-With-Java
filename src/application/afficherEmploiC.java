package application;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class afficherEmploiC implements Initializable {
private  int idsout ;
private  int idsoutM ;
private  int idsoutS ;
private  int idsout1 ;
private  int idsoutM1 ;
private  int idsoutS1 ;
@FXML
private Label Label_updload ;
@FXML
private TableColumn<Soutenance1, HBox> Import_Rapport;
private String inputValue  ;
@FXML
private Label ADD_THEME_LABEL;
    @FXML
    private ImageView Exit;
    @FXML 
   private Label error_label;
    @FXML
    private Label LableNom;
  @FXML
    private Label LableRole;
  @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;
    @FXML
    private Button buttonadd;
    @FXML 
    private Button empliobtn;
    @FXML 
    private Button listebtn;
    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnProffessors;

    @FXML
    private JFXButton btnStudents;
    @FXML
    private JFXButton btnexit;

    @FXML
    private AnchorPane content;
    @FXML
    private TableView<Soutenance1> emploi;
    
    @FXML
    private TableColumn<Soutenance1, String> C_Heure;
    @FXML
    private TableColumn<Soutenance1, String> Email_Encadreur;
    @FXML
    private TableColumn<Soutenance1, String> Encadreur;
    @FXML
    private TableColumn<Soutenance1, String> C_Salle;

    @FXML
    private TableColumn<Soutenance1, String> C_Date;
    @FXML
    private TableColumn<Soutenance1, HBox> C_Theme;
    @FXML
    private TableColumn<Soutenance1, HBox> Note;
    @FXML
    private TableColumn<Soutenance1, HBox> NoteM;
    @FXML
    private TableColumn<Soutenance1, HBox> NoteS;
    public SharedModelID sharedModel;
    public static int idme;
    
	public void setSharedModel(SharedModelID sharedModel) {
        this.sharedModel =sharedModel ;
        this.sharedModel.setSelectedUserId(132);
	}

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
    	System.out.print(idme);
    	if(Import_Rapport!=null) {Import_Rapport.setCellValueFactory(cellData -> { Button AddButton =  new Button ("import");
       
        HBox buttons = new HBox(new Label("    ") , AddButton, new Label(" "));
        AddButton.setOnAction(event -> {
        	
        	 handleChooseFile(idme,Integer.parseInt(cellData.getValue().idProperty().get()));
      
        });
        return new SimpleObjectProperty<>(buttons);
    	
    	});}
        C_Date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        C_Heure.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        C_Salle.setCellValueFactory(cellData -> cellData.getValue().salleProperty());
        if(Encadreur!=null) {Encadreur.setCellValueFactory(cellData -> cellData.getValue().NomencProperty());}
        if(Email_Encadreur!=null) {Email_Encadreur.setCellValueFactory(cellData -> cellData.getValue().emailencProperty());}
        if(C_Theme!=null) {
        C_Theme.setCellValueFactory(cellData -> {
       	 
            Button AddButton =  cellData.getValue().add_NotePropertytheme();
            TextField input =  cellData.getValue().input_NotePropertytheme();
            input.setText(cellData.getValue().themeProperty().get());
            HBox buttons = new HBox(new Label("  "), input, new Label(" "), AddButton);
            AddButton.setOnAction(event -> {
                
               inputValue = input.getText();
                System.out.println("Input Value: " + inputValue);
                String Sql = "UPDATE soutnance SET Theme = ? WHERE id_etud = ?";

                try {
                    String url = "jdbc:mysql://localhost:3306/soutenance";
                    String utilisateur = "root";
                    String motDePasse = "";
                    String sqlnotes="select * from notes where id_sout=? and id_prof=?";
                    try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
                         PreparedStatement preparedStatement = connection.prepareStatement(Sql)) {

                        
                        preparedStatement.setString(1, inputValue);
                        preparedStatement.setInt(2, idme); 

                       
                        int rowsAffected = preparedStatement.executeUpdate();
                        
                        if (rowsAffected > 0) {
                        	ADD_THEME_LABEL.setText("Update successful.");
                        	ADD_THEME_LABEL.setTextFill(Color.GREEN);
                            System.out.println("Update successful.");
                        } else {
                            System.out.println("No rows were updated.");
                        }
                        
                       
                    }

                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
            });
            return new SimpleObjectProperty<>(buttons);
        });
        }
       
        if(Note!=null) {   Note.setCellValueFactory(cellData -> {
        	String url = "jdbc:mysql://localhost:3306/soutenance";
            String utilisateur = "root";
            String motDePasse = "";
            String sqlnotes="select * from notes where id_sout=? and id_prof=?";
            try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
            		 PreparedStatement preparedStatementsqlnotes = connection.prepareStatement(sqlnotes);) {
            	 idsout1=0 ;
                 idsoutM1=0 ;
                idsoutS1=0 ;
            preparedStatementsqlnotes.setInt(1, Integer.parseInt(cellData.getValue().idProperty().get()));
            preparedStatementsqlnotes.setInt(2, MainsceneController.id_user);
             ResultSet resultSetsqlnotes = preparedStatementsqlnotes.executeQuery();
             
             while (resultSetsqlnotes.next()) {
                 // Retrieve data from the result set
                 int note1 = resultSetsqlnotes.getInt("note");
                 int note2 = resultSetsqlnotes.getInt("noteM");
                 int note3 = resultSetsqlnotes.getInt("noteS");
                 idsout1=note1 ;
                 idsoutM1=note2 ;
                idsoutS1=note3 ;
             idsout1=note1 ;
                  idsoutM1=note2 ;
                 idsoutS1=note3 ;
                 // Process the retrieved data as needed
                 System.out.println(cellData.getValue().idProperty().get()+":"+note1+","+note2+","+note3);
             
             }
            }catch(Exception e) {}
            
            
            Button AddButton =  cellData.getValue().add_NoteProperty();
            TextField input =  cellData.getValue().input_NoteProperty();
            input.setText(idsout1+"");
            HBox buttons = new HBox(new Label("  "), input, new Label(" "), AddButton);
            return new SimpleObjectProperty<>(buttons);
        });
        
        
        
        
        }
        if(NoteS!=null) {   NoteS.setCellValueFactory(cellData -> {
          	 
            Button AddButton =  cellData.getValue().add_NotePropertyS();
            TextField input =  cellData.getValue().input_NotePropertyS();
            input.setText(idsoutS1+"");
            HBox buttons = new HBox(new Label("  "), input, new Label(" "), AddButton);
            return new SimpleObjectProperty<>(buttons);
        });}
        if(NoteM!=null) {   NoteM.setCellValueFactory(cellData -> {
          	 
            Button AddButton =  cellData.getValue().add_NotePropertyM();
            TextField input =  cellData.getValue().input_NotePropertyM();
            input.setText(idsoutM1+"");
            HBox buttons = new HBox(new Label("  "), input, new Label(" "), AddButton);
            return new SimpleObjectProperty<>(buttons);
        });}
     
        int profId = idme;

        try {
            String fxmlFileName = new File(location.getPath()).getName();
            
            if (fxmlFileName.equalsIgnoreCase("emploiProf.fxml")) {
                populateTableView(profId);
            } else {
                populateTableView1(profId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		

      
        



        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
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

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }
    private void handleChooseFile(int id_user  ,int id_sout) {
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
                   
                    String sql = "INSERT INTO  rapport (id_sout,id_etud ,rapport_pdf) VALUES (?, ?,?)";
                    String sql1 = "select * from  rapport where id_sout= ? and  id_etud = ? ";
                    String sqlupdate = "update   rapport set rapport_pdf=? where id_sout= ? and  id_etud = ?   ";
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {
                    	 preparedStatement1.setInt(1, id_sout);
                         preparedStatement1.setInt(2, id_user);
                         
                         try (ResultSet resultSetex = preparedStatement1.executeQuery()) {
                            
                             if (!resultSetex.next()) {
                                
                            	 try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                                   	 preparedStatement.setInt(1, id_sout);
                                       preparedStatement.setInt(2, id_user);
                                       preparedStatement.setBytes(3, fileBytes);
                                       preparedStatement.executeUpdate();
                                       ADD_THEME_LABEL.setText("File uploaded successfully!");
                                   	ADD_THEME_LABEL.setTextFill(Color.GREEN);
                                       System.out.println("File uploaded successfully!");
                                   }
                             } else {
                            	 try (PreparedStatement preparedStatement = connection.prepareStatement(sqlupdate)) {
                                   	 preparedStatement.setInt(2, id_sout);
                                       preparedStatement.setInt(3, id_user);
                                       preparedStatement.setBytes(1, fileBytes);
                                       preparedStatement.executeUpdate();
                                       ADD_THEME_LABEL.setText("File UPDATE successfully!");
                                      	ADD_THEME_LABEL.setTextFill(Color.GREEN);
                                       System.out.println("File UPDATE successfully!");
                                   }
                                
                             }
                         }
                    	
                    	
                    }catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Error uploading file to the database: " + e.getMessage());
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

    public static boolean isNumeric(String str) {
        try {
          
            Double.parseDouble(str);
            return true;  
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    public static Integer convertToInteger(StringProperty stringProperty) {
        try {
           
            String stringValue = stringProperty.get();
            
            if (stringValue != null) {
                return Integer.parseUnsignedInt(stringValue);
            } else {
              
                return null;
            }
        } catch (NumberFormatException e) {
           
            return null;
        }
    }
    public static Integer convertToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            
            return null;
        }
    }
    public static double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
           
            System.err.println("Error: Cannot convert to double. Defaulting to 0.0.");
            return 0.0;  
        }
    }
    private void populateTableView(int profId) {
        emploi.getItems().clear();

        try {
            String url = "jdbc:mysql://localhost:3306/soutenance";
            String utilisateur = "root";
            String motDePasse = "";

            Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);

            String query = "SELECT s.Num_sout, s.Theme, s.salle, s.Date, s.heure, e.id_etud AS etudiantName, " +
                    "j.prof1 AS prof1Name, j.prof2 AS prof2Name, j.prof3 AS prof3Name " +
                    "FROM soutnance s " +
                    "JOIN jury_membres j ON s.id_jury = j.ID " +
                    "JOIN etudiant e ON s.id_etud = e.id_etud " +
                    "WHERE ? IN (j.prof1, j.prof2, j.prof3)";


            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, profId);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                	String juryMembers = resultSet.getString("prof1Name") + ", " +
                            resultSet.getString("prof2Name") + ", " +
                            resultSet.getString("prof3Name");
                	
                    Soutenance1 soutenance = new Soutenance1(
                            resultSet.getString("Num_sout"),
                            resultSet.getString("Theme"),
                            resultSet.getString("salle"),
                            resultSet.getString("Date"),
                            resultSet.getString("heure"),
                            resultSet.getString("etudiantName"),
                            juryMembers,
                            "",
                            "",
                            ""
                    );
                    
                    soutenance.add_NoteProperty().setOnAction(event ->{
                    	String note =soutenance.input_NoteProperty().getText();
                    	if(isNumeric(note)) {
                    		if(parseDouble(note)<0  || parseDouble(note)>20)
                    		{
                    			
                    			
                    			error_label.setText("Erorr please write number between 0 and 20");
                    			error_label.setTextFill(Color.RED);
                    			
                    		}
                    		else {error_label.setText("Note add with success");
                    		error_label.setTextFill(Color.GREEN);

                        int id_soutenance=convertToInteger(soutenance.idProperty());
                        
                        String sqlAddNote = "INSERT INTO notes (id_sout, id_prof, note) VALUES (" + id_soutenance + ", " + profId + ", " + parseDouble(note) + "); ";
                        
                        String sqlisfound = "SELECT * FROM notes WHERE id_sout = " + id_soutenance + " AND id_prof = " + profId;

                			try { 

                            Connection connection1 = DriverManager.getConnection(url, utilisateur, motDePasse);
                            PreparedStatement preparedStatementSelect = connection1.prepareStatement(sqlisfound);
            			    
            			    
                            try (ResultSet resultSetselect = preparedStatementSelect.executeQuery()) {
                                int rowCount = 0;

                                while (resultSetselect.next()) {
                                   

                                    rowCount++;
                                }
                                if(rowCount==0) { PreparedStatement preparedStatementAdd = connection1.prepareStatement(sqlAddNote);
                              
                			    
                			    preparedStatementAdd.executeUpdate();
                			    }
                                else {String sqlUpdateNote = "UPDATE notes SET note = ? WHERE id_sout = ? AND id_prof = ?";

try (PreparedStatement preparedStatementUpdate = connection1.prepareStatement(sqlUpdateNote)) {
    preparedStatementUpdate.setDouble(1, parseDouble(note));  
    preparedStatementUpdate.setInt(2, id_soutenance);
    preparedStatementUpdate.setInt(3, profId);

    int rowsAffected = preparedStatementUpdate.executeUpdate();

    System.out.println("Number of rows updated: " + rowsAffected);
} catch (SQLException e) {
    e.printStackTrace();
}}
                                System.out.println("Number of rows: " + rowCount);
                                connection1.close();
                            }
                            
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } 
                			
                			  
                			   

                			
                    		
                    		}
                    		
                    		}
                    	else {error_label.setText("Erorr please write number between 0 and 20");
                    	error_label.setTextFill(Color.RED);
                    	}
                    	
                    	
                    });
                    
                    soutenance.add_NotePropertyM().setOnAction(event ->{
                    	String note =soutenance.input_NotePropertyM().getText();
                    	if(isNumeric(note)) {
                    		if(parseDouble(note)<0  || parseDouble(note)>20)
                    		{
                    			
                    			
                    			error_label.setText("Erorr please write number between 0 and 20");
                    			error_label.setTextFill(Color.RED);
                    			
                    		}
                    		else {error_label.setText("Note add with success");
                    		error_label.setTextFill(Color.GREEN);

                        int id_soutenance=convertToInteger(soutenance.idProperty());
                        
                        String sqlAddNote = "INSERT INTO notes (id_sout, id_prof, noteM) VALUES (" + id_soutenance + ", " + profId + ", " + parseDouble(note) + "); ";
                        
                        String sqlisfound = "SELECT * FROM notes WHERE id_sout = " + id_soutenance + " AND id_prof = " + profId;

                			try { 

                            Connection connection1 = DriverManager.getConnection(url, utilisateur, motDePasse);
                            PreparedStatement preparedStatementSelect = connection1.prepareStatement(sqlisfound);
            			    
            			    
                            try (ResultSet resultSetselect = preparedStatementSelect.executeQuery()) {
                                int rowCount = 0;

                                while (resultSetselect.next()) {
                                  

                                    rowCount++;
                                }
                                if(rowCount==0) { PreparedStatement preparedStatementAdd = connection1.prepareStatement(sqlAddNote);
                              
                			    
                			    preparedStatementAdd.executeUpdate();
                			    }
                                else {String sqlUpdateNote = "UPDATE notes SET noteM = ? WHERE id_sout = ? AND id_prof = ?";

try (PreparedStatement preparedStatementUpdate = connection1.prepareStatement(sqlUpdateNote)) {
    preparedStatementUpdate.setDouble(1, parseDouble(note));  
    preparedStatementUpdate.setInt(2, id_soutenance);
    preparedStatementUpdate.setInt(3, profId);

    int rowsAffected = preparedStatementUpdate.executeUpdate();

    System.out.println("Number of rows updated: " + rowsAffected);
} catch (SQLException e) {
    e.printStackTrace();
}}
                                System.out.println("Number of rows: " + rowCount);
                                connection1.close();
                            }
                            
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } 
                			
                			  
                			   

                			
                    		
                    		}
                    		
                    		}
                    	else {error_label.setText("Erorr please write number between 0 and 20");
                    	error_label.setTextFill(Color.RED);
                    	}
                    	
                    	
                    });
                    
                    
                    
                    
                    soutenance.add_NotePropertyS().setOnAction(event ->{
                    	String note =soutenance.input_NotePropertyS().getText();
                    	if(isNumeric(note)) {
                    		if(parseDouble(note)<0  || parseDouble(note)>20)
                    		{
                    			
                    			
                    			error_label.setText("Erorr please write number between 0 and 20");
                    			error_label.setTextFill(Color.RED);
                    			
                    		}
                    		else {error_label.setText("Note add with success");
                    		error_label.setTextFill(Color.GREEN);

                        int id_soutenance=convertToInteger(soutenance.idProperty());
                        
                        String sqlAddNote = "INSERT INTO notes (id_sout, id_prof, noteS) VALUES (" + id_soutenance + ", " + profId + ", " + parseDouble(note) + "); ";
                        
                        String sqlisfound = "SELECT * FROM notes WHERE id_sout = " + id_soutenance + " AND id_prof = " + profId;

                			try { 

                            Connection connection1 = DriverManager.getConnection(url, utilisateur, motDePasse);
                            PreparedStatement preparedStatementSelect = connection1.prepareStatement(sqlisfound);
            			    
            			    
                            try (ResultSet resultSetselect = preparedStatementSelect.executeQuery()) {
                                int rowCount = 0;

                                while (resultSetselect.next()) {
                                   

                                    rowCount++;
                                }
                                if(rowCount==0) { PreparedStatement preparedStatementAdd = connection1.prepareStatement(sqlAddNote);
                              
                			    
                			    preparedStatementAdd.executeUpdate();
                			    }
                                else {String sqlUpdateNote = "UPDATE notes SET noteS = ? WHERE id_sout = ? AND id_prof = ?";

try (PreparedStatement preparedStatementUpdate = connection1.prepareStatement(sqlUpdateNote)) {
    preparedStatementUpdate.setDouble(1, parseDouble(note));  
    preparedStatementUpdate.setInt(2, id_soutenance);
    preparedStatementUpdate.setInt(3, profId);

    int rowsAffected = preparedStatementUpdate.executeUpdate();

    System.out.println("Number of rows updated: " + rowsAffected);
} catch (SQLException e) {
    e.printStackTrace();
}}
                                System.out.println("Number of rows: " + rowCount);
                                connection1.close();
                            }
                            
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } 
                			
                			  
                			   

                			
                    		
                    		}
                    		
                    		}
                    	else {error_label.setText("Erorr please write number between 0 and 20");
                    	error_label.setTextFill(Color.RED);
                    	}
                    	
                    	
                    });
                    String sqlSelect = "SELECT * FROM notes WHERE id_sout = ? AND id_prof = ?";
                    try (PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlSelect)) {
                        preparedStatementSelect.setInt(1, convertToInteger(soutenance.idProperty()));
                        preparedStatementSelect.setInt(2, profId);

                        try (ResultSet resultSet4 = preparedStatementSelect.executeQuery()) {
                            int rowCount = 0;
                            int mynote=0;
                            int mynote1=0;
                            int mynote2=0;
                            while (resultSet4.next()) {
                               
                                mynote=resultSet4.getInt("note");
                                mynote1=resultSet4.getInt("noteM");
                                mynote2=resultSet4.getInt("noteS");
                                rowCount++;
                            }
                            if(rowCount!=0) {idsout=mynote;
                            
                            idsoutM=mynote1;
                            idsoutS=mynote2;
                            }

                            System.out.println("Number of rows: " + rowCount);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    emploi.getItems().add(soutenance);
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void populateTableView1(int id_etude) {
        emploi.getItems().clear();

        try {
            String url = "jdbc:mysql://localhost:3306/soutenance";
            String utilisateur = "root";
            String motDePasse = "";

            Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);

            String query = "SELECT Num_sout, Theme, salle, Date, heure,id_jury " +
                    "FROM soutnance " +
                    "WHERE id_etud = ?";
      int number_jury;
      String nom_prenom_enc="";
     String email_enc="";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id_etude);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                	 number_jury=resultSet.getInt("id_jury");
                	  String sqljury = "SELECT * FROM jury_membres WHERE ID = " + number_jury;
                	  int idenc = 0;
                      try (PreparedStatement preparedStatement1 = connection.prepareStatement(sqljury)) {
                          ResultSet resultSet2 = preparedStatement1.executeQuery();
                          

                          while (resultSet2.next()) {
                              idenc = resultSet2.getInt("encadreur");
                          }}
                      
                      String sqlenc="select * from utilisateur where id_user ="+idenc;
                      try (PreparedStatement preparedStatement2 = connection.prepareStatement(sqlenc)) {
                          ResultSet resultSet3 = preparedStatement2.executeQuery();
                         

                          while (resultSet3.next()) {
                        	  nom_prenom_enc = resultSet3.getString("nom")+" "+resultSet3.getString("prenom");
                        	  email_enc=resultSet3.getString("email");
                          }}
                          System.out.print(idenc);
                    Soutenance1 soutenance = new Soutenance1(
                            resultSet.getString("Num_sout"),
                            resultSet.getString("Theme"),
                            resultSet.getString("salle"),
                            resultSet.getString("Date"),
                            resultSet.getString("heure"),
                            "", 
                            "" ,
                            nom_prenom_enc,
                            email_enc,
                            ""
                    );
                  
                    emploi.getItems().add(soutenance);
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
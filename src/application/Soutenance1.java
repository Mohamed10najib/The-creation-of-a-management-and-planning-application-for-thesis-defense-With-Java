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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Soutenance1 {
    private final StringProperty id;
    private final StringProperty theme;
    private final StringProperty salle;
    private final StringProperty date;
    private final StringProperty heure;
    private final StringProperty etudiantName;
    private final StringProperty juryMembers;
    private final StringProperty nomenc;
    private final StringProperty filiere;
    
    private final Button add_Note;
    private final TextField input_Note;
    private final Button add_NoteS;
    private final TextField input_NoteS;
    private final Button add_NoteM;
    private final TextField input_NoteM;
    private final Button add_theme;
    private final TextField input_add_theme;
    private final  StringProperty  emailenc;
    public Soutenance1(String id, String theme, String salle, String date, String heure, String etudiantName, String juryMembers,String nomenc,String emailenc,String filiere) {
        this.id = new SimpleStringProperty(id);
        this.theme = new SimpleStringProperty(theme);
        this.salle = new SimpleStringProperty(salle);
        this.date = new SimpleStringProperty(date);
        this.heure = new SimpleStringProperty(heure);
        this.etudiantName = new SimpleStringProperty(etudiantName);
        this.juryMembers = new SimpleStringProperty(juryMembers);
     this.nomenc= new SimpleStringProperty(nomenc);
     this.add_Note=new Button("Add_Note");
     this.input_Note=new TextField ();
     this.add_NoteS=new Button("Add_Note");
     this.input_NoteS=new TextField ();
     this.add_NoteM=new Button("Add_Note");
     this.input_NoteM=new TextField ();
     this.add_theme=new Button("Add theme");
     this.input_add_theme=new TextField ();
     this.emailenc=new SimpleStringProperty(emailenc);
     this.filiere=new SimpleStringProperty(filiere);
    }

    public StringProperty idProperty() {
        return id;
    }
    public StringProperty filiereProperty() {
        return filiere;
    }
    public Button add_NotePropertyM() {
        return add_NoteM;
    }
    public TextField input_NotePropertyM() {
        return input_NoteM;
    }
    public Button add_NotePropertyS() {
        return add_NoteS;
    }
    public TextField input_NotePropertyS() {
        return input_NoteS;
    }
    public Button add_NoteProperty() {
        return add_Note;
    }
    public TextField input_NoteProperty() {
        return input_Note;
    }
    public Button add_NotePropertytheme() {
        return add_theme;
    }
    public TextField input_NotePropertytheme() {
        return input_add_theme;
    }

    public StringProperty themeProperty() {
        return theme;
    }

    public StringProperty salleProperty() {
        return salle;
    }

    public StringProperty dateProperty() {
        return date;
    }
    public StringProperty NomencProperty() {
        return nomenc;
    }
    public StringProperty emailencProperty() {
        return emailenc;
    }

    public StringProperty heureProperty() {
        return heure;
    }

    public StringProperty etudiantNameProperty() {
        return etudiantName;
    }

    public StringProperty juryMembersProperty() {
        return juryMembers;
    }
}



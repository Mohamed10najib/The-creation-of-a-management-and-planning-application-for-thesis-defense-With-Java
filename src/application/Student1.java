package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Student1{

    private StringProperty email;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty filiere;
    private Button btn;
    private Button update;
    private Button downloadpdf;
   

    public Student1(String email, String nom, String prenom, String filiere) {
        this.email = new SimpleStringProperty(email);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.filiere = new SimpleStringProperty(filiere);
        this.btn=new Button("Delete");
        this.update=new Button("Update");
        this.downloadpdf=new Button ("Downloadpdf");
    }

    

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public StringProperty filiereProperty() {
        return filiere;
    }

    

    public String getEmail() {
        return email.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getFiliere() {
        return filiere.get();
    }
    public Button getBtn() {
        return btn;
    }
    public Button getUpdate() {
        return update;
    }
    public Button getdownloadpdf() {
        return downloadpdf;
    }
}
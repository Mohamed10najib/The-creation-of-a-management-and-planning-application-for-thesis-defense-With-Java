package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CProf {

    private StringProperty email;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty departement;
    private StringProperty annesex;
    private Button btn;
    private Button update;
    

    public CProf(String email, String nom, String prenom, String departement,String annesex) {
        this.email = new SimpleStringProperty(email);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.departement = new SimpleStringProperty(departement);
        this.annesex=new SimpleStringProperty(annesex);
        this.btn=new Button("Delete");
        this.update=new Button("Update");
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

    public StringProperty departementProperty() {
        return departement;
    }
    public StringProperty annesProperty() {
        return annesex;
    }
    
    public Button getBtn() {
        return btn;
    }
    public Button getUpdate() {
        return update;
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

    public String getdepartement() {
        return departement.get();
    }
    public String getannes() {
        return annesex.get();
    }

}

package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Etudiant_enc {
	 private  StringProperty email;
	    private  StringProperty filiereEnc;
	    private  StringProperty nom;
	    private  StringProperty prenom;
	    private  StringProperty theme;

	    public Etudiant_enc(String email, String filiereEnc, String nom, String prenom, String theme) {
	        this.email = new SimpleStringProperty(email);
	        this.filiereEnc = new SimpleStringProperty(filiereEnc);
	        this.nom = new SimpleStringProperty(nom);
	        this.prenom = new SimpleStringProperty(prenom);
	        this.theme = new SimpleStringProperty(theme);
	    }

	    
	    public String getEmail() {
	        return email.get();
	    }

	    public String getFiliereEnc() {
	        return filiereEnc.get();
	    }

	    public String getNom() {
	        return nom.get();
	    }

	    public String getPrenom() {
	        return prenom.get();
	    }

	    
	    public String getTheme() {
	        return theme.get();
	    }

	    
	    public void setEmail(String email) {
	        this.email.set(email);
	    }

	    public void setFiliereEnc(String filiereEnc) {
	        this.filiereEnc.set(filiereEnc);
	    }

	    public void setNom(String nom) {
	        this.nom.set(nom);
	    }

	    public void setPrenom(String prenom) {
	        this.prenom.set(prenom);
	    }

	    
	    public void setTheme(String theme) {
	        this.theme.set(theme);
	    }

	   
	    public StringProperty emailProperty() {
	        return email;
	    }

	    public StringProperty filiereEncProperty() {
	        return filiereEnc;
	    }

	    public StringProperty nomProperty() {
	        return nom;
	    }

	    public StringProperty prenomProperty() {
	        return prenom;
	    }

	    public StringProperty themeProperty() {
	        return theme;
	    }
}

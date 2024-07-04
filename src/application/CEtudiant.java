package application;

import javafx.scene.control.Button;

public class CEtudiant {
	    private int idEtud;
	    private String filiere;
	    private int idEncadrent;
	    private Button btn;
	    private Button update;
	    private Button downloadpdf;

	    // Constructeur
	    public CEtudiant(int idEtud, String filiere, int idEncadrent) {
	        this.idEtud = idEtud;
	        this.filiere = filiere;
	        this.idEncadrent = idEncadrent;
	       
	    }

	    // Getters et setters
	    public int getIdEtud() {
	        return idEtud;
	    }

	    public void setIdEtud(int idEtud) {
	        this.idEtud = idEtud;
	    }

	    public String getFiliere() {
	        return filiere;
	    }

	    public void setFiliere(String filiere) {
	        this.filiere = filiere;
	    }

	    public int getIdEncadrent() {
	        return idEncadrent;
	    }

	    public void setIdEncadrent(int idEncadrent) {
	        this.idEncadrent = idEncadrent;
	    }
	    public Button getBtn() {
	        return btn;
	    }
	    public Button getUpdate() {
	        return update;
	    }
	    public Button getdownloadpdfe() {
	        return downloadpdf;
	    }
	


}

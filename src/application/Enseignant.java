 package application;
class Enseignant {
        private final String id;
        private final String nom;
        private final String prenom;

        public Enseignant(String id, String nom, String prenom) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
        }

        public String getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }
    }
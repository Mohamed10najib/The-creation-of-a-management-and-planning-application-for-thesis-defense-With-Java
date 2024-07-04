 package application;
class Jury1 {
    private final String id;
    private final String prof1Id;
    private final String prof2Id;
    private final String prof3Id;

    public Jury1(String id, String prof1Id, String prof2Id, String prof3Id) {
        this.id = id;
        this.prof1Id = prof1Id;
        this.prof2Id = prof2Id;
        this.prof3Id = prof3Id;
    }

    public String getId() {
        return id;
    }

    public String getProf1Id() {
        return prof1Id;
    }

    public String getProf2Id() {
        return prof2Id;
    }

    public String getProf3Id() {
        return prof3Id;
    }
}
    class Etudiant {
        private final String id;
        private final String nom;
        private final String prenom;

        public Etudiant(String id, String nom, String prenom) {
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
 
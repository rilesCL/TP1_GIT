public class Composant {
    private final String CATEGORIE;
    private String marque;
    private String nom;
    private double prix;
    private double rabais;

    public Composant(String CATEGORIE, String nom, String marque, double prix){
        this.CATEGORIE = CATEGORIE.trim();
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.rabais = 0.0;
    }

    public Composant copier(){
        //méthode pour copier
        Composant copie = new Composant(this.CATEGORIE, this.nom, this.marque, this.prix);
        copie.setRabais(this.rabais);
        return copie;
    }

    public boolean estIdentique(Composant autre){
        return this.CATEGORIE.equals(autre.CATEGORIE) &&
                this.marque.equals(autre.marque) &&
                this.nom.equals(autre.nom);
    }

    public double getPrix(){
        return this.prix * (1 - this.rabais);
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String toString(){
        return String.format("[%s] %s %s (%.2f$)", CATEGORIE.toUpperCase(), nom, marque, getPrix());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getRabais() {
        return rabais;
    }

    public void setRabais(double rabais) {
        if (rabais >= 0 && rabais <= 1) {
            this.rabais = rabais;
        }
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getCategorie() {
        return CATEGORIE.toUpperCase().trim();
    }
}

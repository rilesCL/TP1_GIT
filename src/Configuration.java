public class Configuration {

    private final String description;

    private double prixMax;

    private Composant[] composants;

    private int nbComposants;

    private static final int MAX_COMPOSANTS = 20;

    public Configuration(String description, double prixMax, Composant[] composants){
        this.description = description;
        this.prixMax = prixMax;
        this.composants = new Composant[MAX_COMPOSANTS];
        this.nbComposants = composants.length;
        if(nbComposants >= 1) {
            for (int i = 0; i < this.nbComposants; i++) {
                this.composants[i] = composants[i].copier();
            }
        }
    }

    public Configuration(Configuration originale){
        this.description = originale.description;
        this.prixMax = originale.prixMax;
        this.composants = new Composant[MAX_COMPOSANTS];
        this.nbComposants = originale.nbComposants;
        if(originale.composants.length >=   1) {
            for (int i = 0; i < originale.nbComposants; i++) {
                if(originale.composants[i] != null){
                    this.composants[i] = originale.composants[i].copier();
                }
            }
        }
    }


    public double calculerTotal(double taxe){
        double total = 0.0;
        for (int i = 0; i < this.composants.length; i++) {
            if (this.composants[i] != null) {
                total += this.composants[i].getPrix();
            }
        }
        return total * (1 + taxe);
    }


    public Composant rechercher(String categorie){
        for (int i = 0; i < nbComposants; i++) {
            if (composants[i].getCategorie().equalsIgnoreCase(categorie)) {
                return composants[i];
            }
        }
        return null;
    }

    public boolean ajouter(Composant composant){
        for (int i = 0; i < nbComposants; i++) {
            if (composants[i].getCategorie().equals(composant.getCategorie())) {
                System.out.println("Il y a déjà un composant de cette catégorie: " + "[" + composants[i].getCategorie().trim()+ "]" + " "+ composants[i].getNom() +" " + composants[i].getMarque());
                return false;
            }
        }

        if (nbComposants >= MAX_COMPOSANTS){
            return false;
        }
        double prixTotal = calculerTotal(0);
        if(prixTotal + composant.getPrix() > prixMax){
            System.out.println("l'ajout de ce composant ferait dépasser le prix maximum: " + "[" + composant.getCategorie().trim() +"]" + " " + composant.getNom() + " " + composant.getMarque ());
            return false;
        }
        composants[nbComposants] = composant;
        nbComposants++;
        System.out.println("[" + composant.getCategorie().trim() + "] " + composant.getNom() + " " + composant.getMarque () + " ajouté à la configuration (total = " + String.format("%.2f", calculerTotal(0)) + "$)");
        return true;
    }



    public boolean retirer(Composant composant){
        for(int i = 0; i < nbComposants; i++){
            if(this.composants[i] != null && this.composants[i].estIdentique(composant)){
                this.composants[i] = null; // Retire le composant
                System.out.println("[" + composant.getCategorie().trim() +"]" + " " + composant.getNom() + " " + composant.getMarque() +" retiré de la configuration" + "(total = " +calculerTotal(0) + "$)");

                for(int j = i; j < nbComposants - 1; j++){
                    this.composants[j] = this.composants[j+1];
                }
                this.composants[nbComposants - 1] = null;
                nbComposants--;
                return true;
            }
        }
        System.out.println("Composant introuvable: " + "[" + composant.getCategorie().trim() +"]" + " " + composant.getNom() + " " + composant.getMarque());
        return false;


    }

    public boolean remplacer(Composant composant){
        for (int i = 0; i < nbComposants; i++){
            if(composants[i] != null && composants[i].getCategorie().trim().equals(composant.getCategorie().trim())){
                System.out.println("[" + composants[i].getCategorie().trim() + "] " + composants[i].getNom() + " " + composants[i].getMarque() + " retiré de la configuration (total = " + String.format("%.2f", calculerTotal(0) - composants[i].getPrix()) + "$)");
                composants[i] = composant;
                System.out.println("[" + composant.getCategorie().trim() + "] " + composant.getNom() + " " + composant.getMarque() + " ajouté à la configuration (total = " + String.format("%.2f", calculerTotal(0)) + "$)");
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String resultat = description + " (max " + prixMax + "$):\n";
        int compteur = 1;
        for (int i = 0; i < nbComposants; i++) {
            if (composants[i] != null) {
                resultat += "\t" + compteur++ + ": " + composants[i].toString() + "\n";
            }
        }
        return resultat;
    }

    public String getDescription() {
        return description;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public Composant[] getComposants() {
        return composants;
    }



    public int getNbComposants() {
        return nbComposants;
    }
}








import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TesterAjouter {

    private Configuration config;
    private Composant composantCPU;
    private Composant composantSSD;
    private Composant composantGPU;

    @BeforeEach
    void setUp() {
        config = new Configuration("Test Config", 1000.00, new Composant[]{});
        composantCPU = new Composant("CPU", "Ryzen 5 5600X", "AMD", 299.99);
        composantSSD = new Composant("SSD", "980 Pro", "Samsung", 199.99);
        composantGPU = new Composant("GPU", "RTX 3080", "NVIDIA", 699.99);
    }

    @Test
    void ajouterComposantFonctionne() {
        assertTrue(config.ajouter(composantCPU), "Le composant CPU devrait être ajouté avec succès");
    }

    @Test
    void ajouterComposantMemeCategorieEchoue() {
        config.ajouter(composantCPU);
        assertFalse(config.ajouter(new Composant("CPU", "Core i9", "Intel", 499.99)), "L'ajout d'un deuxième CPU devrait échouer");
    }

    @Test
    void ajouterComposantDepassePrixMaxEchoue() {
        config.ajouter(composantCPU);
        config.ajouter(composantGPU); // Cela devrait fonctionner
        assertFalse(config.ajouter(composantSSD), "L'ajout du SSD devrait échouer car il dépasse le prix maximum");
    }


}

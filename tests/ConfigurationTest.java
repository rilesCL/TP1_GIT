import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TesterAjouter {

    private Configuration config;
    private Composant composantCPU;
    private Composant composantSSD;
    private Composant composantGPU;
    private static final int TEST_MAX_COMPOSANTS = 20;

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
        config.ajouter(composantGPU);
        assertFalse(config.ajouter(composantSSD), "L'ajout du SSD devrait échouer car il dépasse le prix maximum");
    }

    @Test
    void ajouterComposantDepasseNombreMaxEchoue() {

        for (int i = 0; i < TEST_MAX_COMPOSANTS; i++) {
            assertTrue(config.ajouter(new Composant("RAM" + i, "RAM Gen" + i, "Brand" + i, 10.00)));
        }

        assertFalse(config.ajouter(new Composant("HDD", "Hard Drive", "Seagate", 50.00)), "L'ajout d'un composant supplémentaire devrait échouer car il dépasse le nombre maximum de composants");
    }
}






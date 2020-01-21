package exemple_poulailler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

interface Oeuf {             // Un oeuf (générique) peut eclore et
    Object eclore();           // faire naître une nouvelle entité
}

class Poule {
    private String nom;        // Nom de la poule
    private int nbOeufs;       // Nombre d’oeufs pondus

    public Poule(String s) {
        nom = s;
    }

    public Oeuf pondre() {     // Ponte d’un oeuf (de Poule)
        System.out.println(nom + " pond son oeuf #" + ++nbOeufs); // incrémenter le compteur

        return new Oeuf() {           // rendre une instance de classe anonyme
            int oeufId = nbOeufs;     // attribut de la classe anonyme
            public Object eclore()    // implantation de la méthode de l'interface Oeuf
            {
                Poule p = new Poule(nom + oeufId);
                System.out.println(p.nom + " sort de l'oeuf #" + oeufId + " de " + nom);
                return p;
            }
        };
    }
}

public class Poulailler {
    private int tour;                             // Numéro de tour dans la simulation
    private LinkedList poules = new LinkedList(); // Poules du poulailler
    private Random random = new Random();         // Générateur aléatoire

    public void ajouter(Poule p) {                // Ajout d’une poule dans le poulailler
        poules.add(p);
    }

    public void tour() {                          // Nouveau tour (ponte + eclosion)
        System.out.println("-- Tour #" + ++tour);   // incrémenter le compteur de tours
        LinkedList oeufs = new LinkedList();        // création de la liste (vide) d'oeufs
        Iterator it;

        it = poules.iterator();                       // ponte des oeufs
        while (it.hasNext())
        {
            int count = random.nextInt(3);
            Poule p = (Poule) it.next();            // rem: transtypage d'Object à Poule
            for (int i = 0; i < count; i++) {
                oeufs.add(p.pondre());
            }
        }

        it = oeufs.iterator();                       // éclosion des oeufs
        while (it.hasNext()) {
            poules.add(((Oeuf) it.next()).eclore()); // ajout des nouvelles poules
            // rem: transtypage d'Object à Oeuf
        }
    }

    public static void main(String[] args) {      // Démarrer la simulation
        Poulailler p = new Poulailler();            // d’un poulailler

        p.ajouter(new Poule("Cocotte"));            // possédeant au départ 2 poules
        p.ajouter(new Poule("Rosette"));

        for (int i = 0; i < 10; i++)                // sur 10 tours
            p.tour();
    }
}
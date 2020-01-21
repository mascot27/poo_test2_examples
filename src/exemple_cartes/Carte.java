package exemple_cartes;


import java.util.HashSet;
import java.util.Set;

enum Valeur {
    DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7),
    HUIT(8), NEUF(9), DIX(10), VALET(11), REINE(12), ROI(13), AS(14);
    private final int v;
    Valeur(int v) { this.v = v; }
    int getV() { return v; }
}

enum Enseigne {
    PIQUE,
    COEUR,
    CARREAU,
    TREFLE
}

public class Carte {
    private final Valeur valeur;
    private final Enseigne enseigne;

    public Carte(Valeur valeur, Enseigne enseigne) {
        this.valeur = valeur;
        this.enseigne = enseigne;
    }

    public Enseigne getEnseigne() {
        return enseigne;
    }

    public Valeur getValeur() {
        return valeur;
    }

    public String toString() {
        return valeur + " de " + enseigne;
    }

    public static int valeurMain(Set<Carte> main) {
        int valeur = 0;
        for (Carte carte : main) {
            valeur += carte.getValeur().getV();
        }
        return valeur;
    }

    public static void main(String ... args) {
        Carte[] cartes = new Carte[Enseigne.values().length * Valeur.values().length];
        int i = 0;
        for (Enseigne enseigne : Enseigne.values()) {
            for (Valeur valeur : Valeur.values()) {
                cartes[i++] = new Carte(valeur, enseigne);
            }
        }
        System.out.println("Jeu de " + cartes.length + " cartes:");
        for (Carte carte : cartes) {
            System.out.println(carte);
        }

        Set<Carte> main = new HashSet<Carte>();
        main.add(new Carte(Valeur.AS, Enseigne.PIQUE));
        main.add(new Carte(Valeur.DIX, Enseigne.CARREAU));
        System.out.println(Carte.valeurMain(main));
    }
}
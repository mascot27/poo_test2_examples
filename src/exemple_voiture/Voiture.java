package exemple_voiture;

import java.util.LinkedList;
import java.util.List;

public class Voiture {
    private String marque;
    private Personne proprietaire; // initialisé par défaut à null
    public Voiture(String marque) {
        this.marque = marque;
    }
    public Personne getProprietaire() {
        return proprietaire;
    }
    // méthode publique, peut être appellée par tous
    // maintient la cohérence
    public void definirProprietaire(Personne p) {
        Personne ancienProprietaire = proprietaire;
        definirProprietaire_(p);
        if (ancienProprietaire != null) {
            ancienProprietaire.enleverVoiture_(this);
        }
        if (proprietaire != null) {
            proprietaire.ajouterVoiture_(this);
        }
    }
    // méthode interne au package
    // ne maintient pas la cohérence
    void definirProprietaire_(Personne p) {
        proprietaire = p;
    }
    public String getMarque() { return marque; }
    public String toString() {
        return "Voiture " + marque
                + (proprietaire != null ? " a comme propriétaire " + proprietaire.getNom()
                : " n'appartient à personne");
    }
}

class Personne {
    private String nom;
    private List<Voiture> voitures = new LinkedList<Voiture>();
    Personne(String nom) {
        this.nom = nom;
    }

    // méthode publique, peut être appellée par tous
    // maintient la cohérence
    public void ajouterVoiture(Voiture v) {
        if (v == null) return;
        if (v.getProprietaire() != this) {
            Personne ancienPropietaire = v.getProprietaire();
            if (ancienPropietaire != null) ancienPropietaire.enleverVoiture_(v);
            v.definirProprietaire_(this);
        }
        ajouterVoiture_(v);
    }
    // méthode interne au package
    // ne maintient pas la cohérence
    void ajouterVoiture_(Voiture v) {
        if (v == null) return;
        if (!voitures.contains(v)) {
            voitures.add(v);
        }
    }
    // méthode publique, peut être appellée par tous
    // maintient la cohérence
    public void enleverVoiture(Voiture v) {
        if (v == null) return;
        v.definirProprietaire_(null);
        enleverVoiture_(v);
    }
    // méthode interne au package
    // ne maintient pas la cohérence
    void enleverVoiture_(Voiture v) {
        if (v == null) return;
        voitures.remove(v);
    }
    public String getNom() { return nom; }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nom);
        sb.append(" possède ");
        boolean separateur = false;
        for (Voiture v : voitures) {
            if (separateur) {
                sb.append(", ");
            }
            sb.append("une ");
            sb.append(v.getMarque());
            separateur = true;
        }
        if (voitures.isEmpty()) {
            sb.append("<rien>");
        }
        return sb.toString();
    }
}

class Test {
    Personne paul;
    Personne ringo;
    Voiture fiat;
    Voiture audi;
    Voiture bmw;

    private void creerObjets() {
        paul = new Personne("Paul");
        ringo = new Personne("Ringo");
        fiat = new Voiture("Fiat");
        audi = new Voiture("Audi");
        bmw = new Voiture("BMW");
    }

    private void creerAssociations() {
        // Paul possède une Fiat
        paul.ajouterVoiture(fiat);
        // Paul possède une Audi
        paul.ajouterVoiture(audi);
        // Ringo possède une BMW
        ringo.ajouterVoiture(bmw);
    }

    private void afficherEtat() {
        System.out.println(paul);
        System.out.println(ringo);
        System.out.println(fiat);
        System.out.println(audi);
        System.out.println(bmw);
    }

    void testPersonneVoiture() {
        System.out.println("Création des objets et associations");
        creerObjets();
        creerAssociations();
        afficherEtat();
        // Ringo vend sa BMW à Paul
        System.out.println("Ringo vend sa BMW à Paul (ajouterVoiture)");
        paul.ajouterVoiture(bmw);
        afficherEtat();
        // Paul abandonne la Fiat
        System.out.println("Paul abandonne la Fiat (enleverVoiture)");
        paul.enleverVoiture(fiat);
        afficherEtat();
    }

    void testVoiturePersonne() {
        System.out.println("Création des objets et associations");
        creerObjets();
        creerAssociations();
        afficherEtat();
        // Ringo vend sa BMW à Paul
        System.out.println("Ringo vend sa BMW à Paul (definirProprietaire)");
        bmw.definirProprietaire(paul);
        afficherEtat();
        // Paul abandonne la Fiat
        System.out.println("Paul abandonne la Fiat (definirProprietaire)");
        fiat.definirProprietaire(null);
        afficherEtat();
    }

    private void test() {
        testPersonneVoiture();
        testVoiturePersonne();
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
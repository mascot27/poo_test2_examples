public class Amorce {
    public static void unDeuxUnDeuxTroisQuatre(Musicien ... ms) {
        for (Musicien m : ms) {
            System.out.println(m);
            // Affiche:
            // 1. le nom du musicien
            // 2. le nom de la classe d'instanciation du musicien
            // 3. la spécialité du musicien
            // (nom du genre musical correspondant)

            System.out.println("Je connais le solfège: " + m.estSolfegiste());
            // La méthode estSolfegiste() retourne true 
            // si le musicien est un solfegiste

            System.out.println("Ma musique de référence: " + m.musiqueDeReference());
            // La méthode musiqueDeReference() retourne un String

            System.out.println("Genres musicaux --");
            for (GenreMusical genre : GenreMusical.values())
                if (m.douePour(genre))
                    System.out.println("Je joue du: " + genre);
            // La méthode douePour() retourne true
            // si le genre musical passé en paramètre correspond
            // à la spécialité du musicien ou à un genre musical
            // à l'origine de sa spécialité
        }
    }

    public static void main(String ... args) {
        unDeuxUnDeuxTroisQuatre(
                new MusicienBaroque("Charles-Albert"),
                new MusicienClassique("Anatole"),
                new Rocker("Bob", GenreMusical.POP_ROCK),
                new Rocker("Sam", GenreMusical.VIKING_METAL));
        // Rocker charlie = new Rocker("Charlie", null);
        // Passe à la compilation mais donne une erreur à l'exécution:
        // Exception in thread "main" java.lang.RuntimeException: Quelle spécialité ?
    }
}
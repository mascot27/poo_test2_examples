public abstract class Musicien {

    private String nom;

    Musicien(String nom){
        this.nom = nom;
    }

    // on va toujours préférer surcharger que faire un instanceof
    boolean estSolfegiste(){
        return false;
    }

    public abstract String musiqueDeReference();

    // Cette méthode n'utilise rien de l'instance de la classe, on peut la mettre en static si déjà mais en plus elle sert pas a grand chose
    /*
    public static boolean isInOrigines(GenreMusical origine, GenreMusical genre){

        while(origine != null){
            if(origine == genre) return true;
            origine = origine.origine();
        }
        return false;
    }*/



    @Override
    public String toString() {
        return "Hello! je m'appel " + nom + "\n"
                + "Je suis un: " + this.getClass().getName() + "\n"
                + "Ma spécialitée: " + this.genre();
    }

    // pas besoin de l'abstraire, l'abstraction est faite dans la méthode genre() qui sera surchargée par les classes filles
    boolean douePour(GenreMusical genre) {
           GenreMusical origine = genre();
           while(origine != null) {
               if(origine == genre) return true;
               else origine = origine.origine();
           }
           return false;
    }

    public abstract GenreMusical genre();
}

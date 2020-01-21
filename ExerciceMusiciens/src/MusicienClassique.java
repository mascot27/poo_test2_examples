public class MusicienClassique extends Musicien implements Solfegiste {

    MusicienClassique(String nom) {
        super(nom);
    }

    @Override
    public String musiqueDeReference() {
        return "Mozart";
    }

    @Override
    public GenreMusical genre() {
        return GenreMusical.CLASSIQUE;
    }

    @Override
    public boolean estSolfegiste() { return true; }
}

public class MusicienBaroque extends MusicienClassique {

    public MusicienBaroque(String nom) {
        super(nom);
    }

    @Override
    public String musiqueDeReference() {
        return "Vivaldi";
    }

    @Override
    public GenreMusical genre() {
        return GenreMusical.BAROQUE;
    }
}

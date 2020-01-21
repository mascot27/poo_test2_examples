public class Rocker extends Musicien {
    private GenreMusical genreMusical;
    Rocker(String nom, GenreMusical genre){
        super(nom);
        this.genreMusical = genre;
    }

    @Override
    public String musiqueDeReference() {
        return "Led Zeppelin";
    }

    @Override
    public GenreMusical genre() {
        return this.genreMusical;
    }
}

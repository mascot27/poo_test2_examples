interface Solfegiste { }

enum GenreMusical {
    CLASSIQUE(null),
    BAROQUE(null),
    BLUES(null),
    ROCK(BLUES),
    POP_ROCK(ROCK),
    HARD_ROCK(ROCK),
    VIKING_METAL(HARD_ROCK);

    private final GenreMusical origine;

    GenreMusical(GenreMusical origine) {
        this.origine = origine;
    }

    public GenreMusical origine() {
        return origine;
    }
}
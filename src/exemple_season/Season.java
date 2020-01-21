package exemple_season;

final class Season {
    private final String name;
    private final int index;
    private static int count;
    public static final Season
            Spring = new Season("Spring"),
            Summer = new Season("Summer"),
            Fall = new Season("Fall"),
            Winter = new Season("Winter");

    private Season(String name) {
        this.name = name;
        index = count++;
    }

    public static Season[] values() {
        return new Season[]{Spring, Summer, Fall, Winter};
    }

    public String toString() {
        return name + " (" + index + ")";
    }

    public String name() {
        return name;
    }

    public int ordinal() {
        return index;
    }

    public Season next() {
        return values()[(index + 1) % count];
    }

    public Season previous() {
        return values()[(index + count - 1) % count];
    }


    public static Season valueOf(String name) {
        for (Season s : values())
            if (s.name.equals(name))
                return s;

        return null;
    }

    public static Season[] range(Season first, Season last) {
        if (first == null || last == null) return new Season[0];
        int size = last.index - first.index + 1;
        if (size < 0) size = 0;
        Season[] result = new Season[size];
        Season[] values = values();
        for (int i = 0; i < size; ++i)
            result[i] = values[first.index + i];
        return result;
    }

    public static void main(String... args) {
        for (Season s : Season.values())
            System.out.println(s);

        for (Season s : Season.range(Season.Spring, Season.Fall))
            System.out.printf("%10s <- %10s -> %10s\n", s.previous(), s, s.next());

        Season winter = Season.valueOf("Winter");
        if (winter == Season.Winter)
            System.out.println("Winter is coming");
    }

}
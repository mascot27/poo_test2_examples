package exemple_starwars;

class ForceUser
{
    private String name;
    private int midiChlorians;
    private ForceUser master;

    public ForceUser(String s, int c, ForceUser m) {
        name = s;
        midiChlorians = c;
        master = m;
    }

    public String saberColor() {
        return "<none>";
    }

    public String toString() {
        return name + ", " + midiChlorians + "mc, color: " + saberColor() +
                ", master: " + (master == null ? "<none>" : master.name);
    }

    public String name() {
        return name;
    }

    public int midiChlorians() {
        return midiChlorians;
    }
}

class Jedi extends ForceUser
{
    private String saberColor;

    public Jedi(String n, int c, Jedi m, String s) {
        super(n, c, m);
        saberColor = s;
    }

    public Jedi(String n, int c, String s) {
        this(n, c, null, s);
    }

    public String saberColor() {
        return saberColor;
    }

    public String toString() {
        return "Jedi " + super.toString();
    }
}

class Sith extends ForceUser
{
    public Sith(String s, int c, Sith m) {
        super(s, c, m);
    }

    public Sith(String s, int c) {
        this(s, c, null);
    }

    public String saberColor() {
        return "red";
    }

    public String toString() {
        return "Sith " + super.toString();
    }

    public Sith corrupt(Jedi jedi, String newName) {
        System.out.println(name() + " corrupts " + jedi.name() + "!");
        return new Sith(newName, jedi.midiChlorians(), this);
    }
}

class StarWars
{
    public static void main(String ... args) {
        Jedi
                yoda = new Jedi("Yoda", 19000, "green"),
                obiwan = new Jedi("Obi-Wan", 17000, yoda, "blue"),
                anakin = new Jedi("Anakin", 27000, obiwan, "green"),
                luke = new Jedi("Luke", 23000, obiwan, "green");
        Sith
                sidious = new Sith("Darth Sidious", 20000);

        Sith vader = sidious.corrupt(anakin, "Darth Vader");
        anakin = null;

        ForceUser array[] = { yoda, sidious, obiwan, vader, luke };

        for (int i = 0; i < array.length; ++i)
            System.out.println(array[i]);
    }
}

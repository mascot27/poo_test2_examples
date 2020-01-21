package exemple_enceinte;


interface Enceinte {
    double perimetre();
    double surface();
}

class Cercle implements Enceinte {
    double rayon;
    Cercle(double rayon) {
        this.rayon = rayon;
    }

    public double perimetre() {
        return Math.PI * 2 * rayon;
    }

    public double surface() {
        return Math.PI * rayon * rayon;
    }
}

class Carre implements Enceinte {
    double width;
    Carre(double width) {
        this.width = width;
    }

    public double perimetre() {
        return 4 * width;
    }

    public double surface() {
        return width * width;
    }
}

class TestEnceinte {

    Enceinte[] enceintes = new Enceinte[] {
            new Cercle(5.0),
            new Carre(10.0)
    };

    Enceinte minPerParSurf(Enceinte[] enceintes) {
        double minVal = Double.MAX_VALUE;
        Enceinte minE = null;
        for (Enceinte e : enceintes) {
            double perParSurf = e.perimetre() / e.surface();
            if (perParSurf < minVal) {
                minVal = perParSurf;
                minE = e;
            }
        }
        return minE;
    }

    void test() {
        for (Enceinte e : enceintes)
            System.out.println("surface = " +
                    e.surface() +", périmètre = " +
                    e.perimetre());

        System.out.println("Enceinte minPerParSurf()");
        System.out.println(minPerParSurf(enceintes));
    }

    public static void main(String[] args) {
        new TestEnceinte().test();
    }
}
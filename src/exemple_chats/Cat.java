package exemple_chats;

import java.util.*;

class Cat {
    private static LinkedList<Cat> cats = new LinkedList<>();
    private int id;

    static {
        System.out.println(">> Bloc statique");
        new Cat();
        System.out.println("<< Bloc statique");
    }

    public Cat() {
        id = count(); // Les ids des chats commencent à 0
        cats.add(this);
        System.out.println("Nouveau chat (" + id + ")");
    }

    public static int count() {
        return cats.size();
    }

    public void mreow() {
        System.out.println("Le chat #" + id + " miaule...");
    }

    public static void info() {
        System.out.println(count() + " chats créés");
    }

    public static Cat get(int id) {
        return (Cat) cats.get(id);
    }

    public static void mreowAll() {
        for (Cat c : cats)
            c.mreow();
    }

    public static void main(String... args) {
        for (int i = 0; i < 5; ++i)
            new Cat().mreow();

        Cat.info();

        System.out.println("J'obtiens le chat #3");
        Cat c = Cat.get(3);
        c.mreow();

        c = new Cat();
        c.mreow();
        c.info(); // mauvaise idée, préférer Cat.info();

        Cat.mreowAll();
    }
}
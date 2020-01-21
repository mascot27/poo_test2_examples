package example_automation;

import java.util.*;

interface Target {
    String name();
}

class Action {
    private int time;

    public Action(int time) {
        this.time = time;
    }

    public int time() {
        return time;
    }

    public String message() {
        return "[action " + time + "]";
    }

    public Target target() {
        return null;
    }
}

class Equipment implements Target {
    private String name;

    public Equipment(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Action defineAction(int time, String type) {
        return new Action(time) {
            public String message() {
                return super.message() + " " + name + ": " + type;
            }

            public Target target() {
                return Equipment.this;
            }
        };
    }
}

class HASystem {
    private static LinkedList<Action> actions = new LinkedList<>();

    public static void registerAction(Action a) {
        java.lang.System.out.println("Register action " +
                (a.target() == null ? "" : a.target().name()));
        actions.add(a);
    }

    public static void checkAction(int currentTime) {
        for (Action a : actions)
            if (a.time() == currentTime)
                java.lang.System.out.println(a.message());
    }
}


class Main {
    public static void main(String... args) {
        Equipment
                e1 = new Equipment("Blind Kitchen"),
                e2 = new Equipment("Light Kitchen");

        Action
                a0 = new Action(600),
                a1 = e1.defineAction(750, "raise"),
                a2 = e2.defineAction(800, "on"),
                a3 = e1.defineAction(900, "lower");

        HASystem.registerAction(a0);
        HASystem.registerAction(a1);
        HASystem.registerAction(a2);
        HASystem.registerAction(a3);

        for (int time = 0; time < 60*24; time++)
            HASystem.checkAction(time);
    }
}
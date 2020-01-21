package exemple_observateur;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Observer {
    void update(String event);
}

abstract class EventSource {
    private final List<Observer> observers = new ArrayList<>();

    protected void notify(String event) {
        for (Observer o : observers) o.update(event);
    }

    public void addObserver(Observer observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        if (observer != null)
            observers.remove(observer);
    }
}

class SystemInScanner extends EventSource {
    public void scanSystemIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Text: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            notify(line);
        }
    }
}

// The Observers are typically in a different package
// and possibly written by a different developer

class FirstObserver implements Observer {
    FirstObserver(EventSource eventSource) {
        eventSource.addObserver(this);
    }

    public void update(String event) {
        System.out.println("FirstObserver received event: " + event);
    }
}

class SecondObserver implements Observer {
    SecondObserver(EventSource eventSource) {
        eventSource.addObserver(this);
    }

    public void update(String event) {
        System.out.println("SecondObserver received event: " + event);
    }
}

class ThirdObserver {
    ThirdObserver(EventSource eventSource) {
        eventSource.addObserver(new Observer() {
            @Override
            public void update(String event) {
                System.out.println("ThirdObserver received event: " + event);
            }
        });
    }
}

class FourthObserver extends EventSource {
    FourthObserver(EventSource eventSource) {
        eventSource.addObserver(new Observer() {
            @Override
            public void update(String event) {
                System.out.println("FourthObserver received event: " + event);
                FourthObserver.this.notify(event);
            }
        });
    }
}

class ObserverDemo {
    public static void main(String[] args) {
        SystemInScanner eventSource = new SystemInScanner();

        new FirstObserver(eventSource);
        new SecondObserver(eventSource);
        new ThirdObserver(eventSource);
        new FirstObserver(new FourthObserver(eventSource));

        eventSource.scanSystemIn();
    }
}
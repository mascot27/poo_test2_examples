package exemple_object_list;

public class Examinator {
    private Element current;

    // Le constructeur a visibilité package seulement
    // (il doit seulement être instancié par ObjectList).
    Examinator(Element first) {
        current = first;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Object next() {
        if (current == null) {
            throw new RuntimeException("No more elements to return");
        }
        Object data = current.data;
        current = current.next;
        return data;
    }
}
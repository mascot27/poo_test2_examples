package exemple_object_list;


import java.util.*;

// ----------------------------------------------------------------------------
// Classe Element (package)
// ----------------------------------------------------------------------------

class Element {
    Object data;
    Element next;

    Element(Object data) {
        this.data = data;
    }

    Element(Object data, Element next) {
        this(data);
        this.next = next;
    }
}

// ----------------------------------------------------------------------------
// Classe ObjectList (publique)
// ----------------------------------------------------------------------------

public class ObjectList {
    private Element head;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Insertion d'un elelement en debut de liste

    public void insert(Object o) {
        size++;

        if (isEmpty())
            head = new Element(o);
        else {
            Element e = new Element(o, head);
            head = e;
        }
    }

    // Insertion d'un element en fin de liste

    public void append(Object o) {
        size++;

        if (isEmpty())
            head = new Element(o);
        else {
            Element e;
            for (e = head; e.next != null; e = e.next) ;
            e.next = new Element(o);
        }
    }

    // Suppression du premier element contenant o

    public void remove(Object o) {
        if (isEmpty())
            return;

        if (head.data == o) {
            head = head.next;
            size--;
            return;
        }

        for (Element e = head; e.next != null; e = e.next)
            if (e.next.data == o) {
                e.next = e.next.next;
                size--;
                return;
            }
    }

    // Accesseur sur l'element a la position index

    public Object get(int index) {
        if (isEmpty())
            return null;

        Element e = head;
        for (int i = 0; i < index && e != null; i++, e = e.next) ;

        if (e != null)
            return e.data;

        return null;
    }

    public String toString() {
        String s = "( ";

        for (Element e = head; e != null; e = e.next)
            s += e.data + " ";

        return s + ")";
    }

    public Examinator examinator() {
        return new Examinator(head);
    }

    // Programme de test

    public static void main(String[] args) {
        ObjectList list = new ObjectList();

        Random random = new Random();
        for (int i = 0; i < 10; i++)
            list.insert(new Integer(random.nextInt(100)));
        list.append("-End-");

        System.out.println(list);

        Object i3 = list.get(3), i0 = list.get(0), i9 = list.get(9);

        System.out.println("Removing " + i3);
        list.remove(i3);
        System.out.println(list);

        System.out.println("Removing " + i0);
        list.remove(i0);
        System.out.println(list);

        System.out.println("Removing " + i9);
        list.remove(i9);
        System.out.println(list);

        System.out.println("Examinator test");
        int sum = 0;
        Examinator it = list.examinator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o instanceof Integer) // necessaire: contient egalement un String
                sum += ((Integer) o).intValue();
        }
        System.out.println("Sum of Integer elements: " + sum);
    }
}
package hacking;

/**
 * Created by igor on 05.07.16.
 * this class we'll change private variable
 */
public class Victim {

    private String person;

    public Victim(String person) {
        this.person = person;
    }

    private int mas[] = {2, 3, 3, 5, 12};

    private String printMe() {
        return "nice to meet you " + person;
    }

    public int[] getMas() {
        return mas;
    }
}

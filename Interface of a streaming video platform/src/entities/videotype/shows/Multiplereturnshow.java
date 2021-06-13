package entities.videotype.shows;

import java.util.ArrayList;

public class Multiplereturnshow {
    /**
     * parametru care contine un string avem nevoei la returnarea multipla.
     */
    private final String first;
    /**
     * parametru care contine o lista de seriale avem nevoei la returnarea
     * multipla.
     */
    private final ArrayList<Shows> second;

    /**
     * @param string contine numele unui serial
     * @param shows lista de seriale
     */
    public Multiplereturnshow(
            final String string,
            final ArrayList<Shows> shows) {
        this.first = string;
        this.second = shows;
    }

    /**
     * @return un string.
     */
    public String getFirst() {
        return first;
    }

    /**
     * @return o lista care contine seriale.
     */
    public ArrayList<Shows> getSecond() {
        return second;
    }
}

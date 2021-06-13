package entities.videotype;

import java.util.ArrayList;

public class Multiplereturnmovie {
    /**
     * Parametru care stocheaza un string.
     */
    private final String first;
    /**
     * Parametru care stocheaza un ArrayList de Movies.
     */
    private final ArrayList<Movies> second;

    /**
     * @param string se refera la un string
     * @param movies se refera la o lista cu filme
     */
    public Multiplereturnmovie(
            final String string, final ArrayList<Movies> movies) {
        this.first = string;
        this.second = movies;
    }

    /**
     * Parametru care stocheaza un ArrayList de Movies.
     */
    public String getFirst() {
        return first;
    }

    /**
     * Parametru care stocheaza un ArrayList de Movies.
     */
    public ArrayList<Movies> getSecond() {
        return second;
    }
}


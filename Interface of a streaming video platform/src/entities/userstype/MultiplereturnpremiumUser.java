package entities.userstype;

import entities.videotype.Movies;
import entities.videotype.shows.Shows;

import java.util.ArrayList;

public class MultiplereturnpremiumUser {

    /**
     * Parametru care stocheaza un ArrayList de Movies.
     */
    private final ArrayList<Movies> first;
    /**
     * Parametru care stocheaza un ArrayList de Shows.
     */
    private final ArrayList<Shows> second;

    /**
     * @param movies se refera la o lista cu filme
     * @param shows  se refera la o lista de seriale
     */
    public MultiplereturnpremiumUser(final ArrayList<Movies> movies,
                                     final ArrayList<Shows> shows) {
        this.first = movies;
        this.second = shows;
    }

    /**
     * @return primul parametrul care ne ajuta la returnarea multipla
     * fiind o lista de filme.
     */
    public final ArrayList<Movies> getFirst() {
        return first;
    }

    /**
     * @return al doilea parametrul care ne ajuta la returnarea multipla
     * fiind o lista de seriale.
     */
    public final ArrayList<Shows> getSecond() {
        return second;
    }

}

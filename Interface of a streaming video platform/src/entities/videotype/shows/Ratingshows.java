package entities.videotype.shows;

public class Ratingshows {

    /**
     * numele serialului.
     */
    private final String title;
    /**
     * sezonul caruia trebuie sa i se acorde rating.
     */
    private final int seasontoberated;
    /**
     * nota acordata de utilizator.
     */
    private final double rating;

    /**
     * @param nume numele serialului
     * @param sezonulcurent sezonul caruia trebuie sa i se acorde rating
     * @param grade nota acordata de utilizator
     */
    public Ratingshows(
            final String nume,
            final int sezonulcurent,
            final double grade) {

        this.title = nume;
        this.seasontoberated = sezonulcurent;
        this.rating = grade;
    }

    /**
     * @return numele serialului
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return sezonul caruia i-a fost acordataa nota
     */
    public int getSeasontoberated() {
        return seasontoberated;
    }

    /**
     * @return ratingul dat de utilizaotr
     */
    public double getRating() {
        return rating;
    }

}

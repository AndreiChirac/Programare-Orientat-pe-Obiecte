package entities.videotype;

public class Ratingmovies {
    /**
     * numele filmului.
     */
    private final String title;
    /**
     * nota acordata filmului de catre utilizator.
     */
    private final double rating;

    /**
     * @param nume numele filmului
     * @param nota nota acordata de utilizator
     */
    public Ratingmovies(
            final String nume,
            final double nota) {
        this.title = nume;
        this.rating = nota;
    }

    /**
     * @return numele filmului
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return donta acordata de utilizator
     */
    public double getRating() {
        return rating;
    }

}

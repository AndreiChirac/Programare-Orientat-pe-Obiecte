package entities.videotype;

import java.util.ArrayList;
import java.util.List;

public class Movies extends Video {
    /**
     * Stocheaza in ratinglist ratingurile oferite de utilizatori.
     */
    private final List<Double> ratingList;
    /**
     * Stocheaza durata fimului .
     */
    private final int duration;
    /**
     * Stocheaza ratingul fimului .
     */
    private double rating;

    /**
     * @param title  reprezinta titlul filmului.
     * @param year   reprezinta anul aparitiei.
     * @param cast   reprezinta o lista cu actorii care joaca in film.
     * @param genres reprezinta stilurile care sunt continute in film.
     * @param length reprezinta durata filmului.
     *               Constructor pentru a initializa campurile unui film.
     */
    public Movies(final String title, final int year,
                  final ArrayList<String> cast, final ArrayList<String> genres,
                  final int length) {

        super(title, year, genres);
        this.duration = length;
        this.ratingList = new ArrayList<>();
        this.rating = movieRatingCalculator();
        super.setRating(this.rating);
    }

    /**
     * @return Ofera durata fimului.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return Ofera lista cu ratinguri oferite de utilizatori fimului.
     */
    public List<Double> getRatingList() {
        return ratingList;
    }

    /**
     * @return Ofera ratingul fimului.
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param grade reprezinta ratingul fimului.
     *             Seteaza ratingul , folosita la updatarea ratingului dupa
     *             adaugarea unui nou rating in lista.
     */
    public void setRating(final double grade) {
        this.rating = grade;
    }

    /**
     * Calculeaza ratingul unui film pe baza listei cu ratinguri oferite de
     * utilizatori.
     *
     * @return se refera la ratingul final al filmului
     */
    public double movieRatingCalculator() {

        double ratingcalc = 0;

        if (this.getRatingList().size() != 0) {
            for (int i = 0; i < this.getRatingList().size(); i++) {
                ratingcalc = ratingcalc + this.getRatingList().get(i);
            }

            ratingcalc = ratingcalc / this.getRatingList().size();
        }
        return ratingcalc;
    }


}

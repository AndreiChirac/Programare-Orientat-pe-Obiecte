package entities.videotype.shows;

import entertainment.Season;
import entities.videotype.Video;

import java.util.ArrayList;

public class Shows extends Video {
    /**
     * Numarul de sezoane pe care il are un serial.
     */
    private final int numberOfSeasons;
    /**
     * Lista cu sezaonele continute de un serial.
     */
    private final ArrayList<Season> seasons;
    /**
     * durata totala.
     */
    private final int duration;
    /**
     * ratingul calculat in functie de ratingul pe sezoane.
     */
    private double rating;

    /**
     * @param title numele serialului
     * @param year anul de publicatie serialului
     * @param cast actorii care au jucat in film
     * @param genres genurile care se intalnesc in serial
     * @param nuamruldesezoane nuamrul de sezoane a unui serial
     * @param sezoane lista cu sezoanele
     */
    public Shows(
            final String title,
            final int year,
            final ArrayList<String> cast,
            final ArrayList<String> genres,
            final int nuamruldesezoane,
            final ArrayList<Season> sezoane) {
        super(title, year, genres);
        this.numberOfSeasons = nuamruldesezoane;
        this.seasons = sezoane;
        this.duration = this.showDuration();
        this.rating = this.ratingCalcualtor();
        super.setRating(rating);
    }

    /**
     * @return lista cu sezoanele unui serial
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * @return durata unui serial
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return ratingul unui serial
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param grade seteaza ratingul total
     */
    public void setRating(final double grade) {
        this.rating = grade;
    }

    /**
     * @return calculeaza durata totala a unui serial bazat pe duratele
     * tuturor sezoanelor si returneaza durata totala
     */
    private int showDuration() {

        int totalduration = 0;

        for (int i = 0; i < this.numberOfSeasons; i++) {
            totalduration =
                    totalduration + this.getSeasons().get(i).getDuration();
        }

        return totalduration;
    }

    /**
     * @return calculeaza ratingul total a unui serial bazat pe ratingurile
     * tuturor sezoanelor si returneaza ratingul final
     */
    public double ratingCalcualtor() {

        double totalratingsperseason = 0;
        double totalratings = 0;


        for (Season season : this.seasons) {

            for (int j = 0; j < season.getRatings().size(); j++) {
                totalratingsperseason =
                        totalratingsperseason
                                + season.getRatings().get(j);
            }
            if (season.getRatings().size() != 0) {
                totalratingsperseason =
                        totalratingsperseason
                                / season.getRatings().size();
                totalratings = totalratings + totalratingsperseason;
                totalratingsperseason = 0;
            }
        }

        if (this.numberOfSeasons != 0) {
            totalratings = totalratings / this.numberOfSeasons;
        }

        return totalratings;
    }


}

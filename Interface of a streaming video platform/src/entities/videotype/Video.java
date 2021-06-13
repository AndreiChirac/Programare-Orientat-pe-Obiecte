package entities.videotype;

import java.util.ArrayList;

public class Video {

    /**
     * numele videoului.
     */
    private final String title;
    /**
     * anul publicatiei.
     */
    private final int year;
    /**
     * tipul pe care il contine un videoclip.
     */
    private final ArrayList<String> genres;
    /**
     * ratingul unui videoclip.
     */
    private double rating;


    /**
     * @param nume numele videoclipului
     * @param an anul in care a aparut
     * @param tipuri tipurile pe care il contine
     */
    public Video(
            final String nume,
            final int an,
            final ArrayList<String> tipuri) {

        this.title = nume;
        this.year = an;
        this.genres = tipuri;
    }

    /**
     * @return numele unui videoclip
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return anul aparitiei unui videoclip
     */
    public int getYear() {
        return year;
    }

    /**
     * @return tipurile pe care le contine un videoclip
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * @return ratingul unui videoclip
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param grade seteaza ratingul
     */
    public void setRating(final double grade) {
        this.rating = grade;
    }


}

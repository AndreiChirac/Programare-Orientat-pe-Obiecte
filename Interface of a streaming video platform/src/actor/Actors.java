package actor;

import entities.videotype.Movies;
import entities.videotype.shows.Shows;

import java.util.ArrayList;
import java.util.Map;

public class Actors {
    /**
     * numele actorului.
     */
    private final String name;
    /**
     * cariera actorului.
     */
    private final String careerDescription;
    /**
     * o lista cu filmele in care a jucat actorul.
     */
    private final ArrayList<String> filmography;
    /**
     * un hasmap a carei keys reprezinta numele premiului si values de cate ori
     * a fost castigat de actroul respectiv.
     */
    private final Map<ActorsAwards, Integer> awards;
    /**
     * numarul total de premii obtinute de actor.
     */
    private final int numberofawards;
    /**
     * ratingul actorului in funcite de videourile in care a jucat.
     */
    private final double rating;

    /**
     * @param nume              numele actroului
     * @param careerdescription descrierea carierei lui
     * @param aparitiifilme     numele filmelor in care a juccat
     * @param premii            premiile pe care le-a obtinut si de cate ori
     * @param movies            filmele din baza de date pentru a calcula
     *                          ratingul
     * @param shows             serialele din baza de date pentru a calcula
     *                          ratingul
     */
    public Actors(
            final String nume,
            final String careerdescription,
            final ArrayList<String> aparitiifilme,
            final Map<ActorsAwards, Integer> premii,
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows) {

        this.name = nume;
        this.careerDescription = careerdescription;
        this.filmography = aparitiifilme;
        this.awards = premii;
        this.rating = this.ratingForActor(movies, shows);
        this.numberofawards = this.totalAwards();
    }

    /**
     * @return numele actorului
     */
    public String getName() {
        return name;
    }

    /**
     * @return cariera actroului
     */
    public String getCareerDescription() {
        return careerDescription;
    }

    /**
     * @return hasmapul cu premiile obrinute
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    /**
     * @return nuamrul totoal de premii obtinute de actor
     */
    public int getNumberofawards() {
        return numberofawards;
    }

    /**
     * @return ratingul obtinut de actor pe baza videourilor in care a juact
     */
    public double getRating() {
        return rating;
    }

    private Double ratingForActor(
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows) {
        double ratingul = 0;
        double cnt = 0;

        for (Movies movie : movies) {
            if (this.filmography.contains(movie.getTitle())
                    && movie.getRating() != 0) {
                ratingul = ratingul + movie.getRating();
                cnt++;
            }
        }
        for (Shows show : shows) {
            if (this.filmography.contains(show.getTitle())
                    && show.getRating() != 0) {
                ratingul = ratingul + show.getRating();
                cnt++;
            }
        }

        if (cnt != 0) {
            ratingul = ratingul / cnt;
        }
        return ratingul;
    }

    /**
     * @return numarul total de premii pe care le-a obtinut un acotr
     */
    public int totalAwards() {
        int totalawards = 0;
        for (Integer value : this.getAwards().values()) {
            totalawards = totalawards + value;
        }
        return totalawards;
    }


}

package entities.userstype;

import entities.videotype.Movies;
import entities.videotype.Multiplereturnmovie;
import entities.videotype.Ratingmovies;
import entities.videotype.shows.Multiplereturnshow;
import entities.videotype.shows.Ratingshows;
import entities.videotype.shows.Shows;

import java.util.ArrayList;
import java.util.Map;

public class Users {
    /**
     * lista cu videourile preferate ale userului.
     */
    private final ArrayList<String> favoritevideos;
    /**
     * lista cu numele filmelor si ratingurile lor.
     */
    private final ArrayList<Ratingmovies> movieratings;
    /**
     * lista cu numele serialelor si ratingurile lor.
     */
    private final ArrayList<Ratingshows> showratings;
    /**
     * numele userului.
     */
    private final String username;
    /**
     * tipul de subscriptie.
     */
    private final String subscriptiontype;
    /**
     * hasmap care contine numele videourile ca key si numarul de viuzalizari
     * ca value.
     */
    private final Map<String, Integer> history;

    /**
     * @param name numele userului
     * @param subscription tipul de subscriprie BASIC/PREMIUM
     * @param viewdhistory istoricul de videouri vizualizate
     * @param likedvideos lista cu filmele favorite ale utilizatorului
     * Constructor care realizeaza initializarea unui user.
     */
    public Users(
            final String name,
            final String subscription,
            final Map<String, Integer> viewdhistory,
            final ArrayList<String> likedvideos) {

        this.username = name;
        this.subscriptiontype = subscription;
        this.history = viewdhistory;
        this.favoritevideos = likedvideos;
        this.movieratings = new ArrayList<>();
        this.showratings = new ArrayList<>();
    }

    /**
     * @return numele userului.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return tipul de subscripite.
     */
    public String getSubscriptiontype() {
        return subscriptiontype;
    }

    /**
     * @return istoicul de vizualizari ale utilizatorului.
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * @return lista de videouri favorite ale utilizatorului.
     */
    public ArrayList<String> getFavoritevideos() {
        return favoritevideos;
    }

    /**
     * @return lsita cu filmele caruia i-au dat utilizatorul rating.
     */
    public ArrayList<Ratingmovies> getMovieratings() {
        return movieratings;
    }

    /**
     * @return lista cu showurile caruia i-au dat utilizatorul rating
     */
    public ArrayList<Ratingshows> getShowratings() {
        return showratings;
    }


    private int viewd(final String title) {
        int ok = 0;

        for (String key : this.history.keySet()) {

            if (key.equals(title)) {
                ok = 1;
                break;
            }
        }
        return ok;
    }

    private int alreayLiked(final String title) {

        int ok = 0;

        for (String favoriteVideo : this.favoritevideos) {
            if (favoriteVideo.equals(title)) {

                ok = 1;
                break;

            }
        }
        return ok;
    }

    /**
     * @param movies lista de filmele din baza de date
     * @param shows lista de showuri din baza de date
     * @return întoarce numele primul video nevăzut de utilizator din baza de
     * date
     * Se cauta in initial in filmele din baza de date daca se intalneste un
     * video nevizualizat se returneaza numele acestuai in caz similar se
     * procedeaza si pentru seriale.
     */
    public String standard(
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows) {

        String answear;

        for (entities.videotype.Movies movie : movies) {
            if (viewd(movie.getTitle()) == 0) {
                answear = "StandardRecommendation result: " + movie.getTitle();
                return answear;
            }
        }

        for (int i = 0; i < shows.size(); i++) {
            if (viewd(shows.get(i).getTitle()) == 0) {
                answear = "StandardRecommendation result: "
                        + movies.get(i).getTitle();
                return answear;
            }
        }

        answear = "StandardRecommendation cannot be applied!";
        return answear;
    }

    /**
     * @param movies lista de filmele din baza de date
     * @param shows lista de showuri din baza de date
     * @return întoarce cel mai bun video nevizualizat de acel utilizator
     * Se sorteaza filmele si showrile din baza de date in functie de ratingul
     * si apoi se returneaza numele primului videoclip care nu a fost vizualizat
     */
    public String bestUnseen(
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows) {

        ArrayList<Movies> sortedmovies = new ArrayList<>(movies);
        ArrayList<Shows> sortedshows = new ArrayList<>(shows);
        sortedmovies =
                sortByRating(sortedmovies, sortedshows, "desc").getFirst();
        sortedshows =
                sortByRating(sortedmovies, sortedshows, "desc").getSecond();

        String answer = "BestRatedUnseenRecommendation result: ";

        for (entities.videotype.Movies sortedMovie : sortedmovies) {
            if (!this.getHistory().containsKey(sortedMovie.getTitle())
                    && sortedMovie.getRating() != 0) {
                answer = answer + sortedMovie.getTitle();
                return answer;
            }
        }
        for (entities.videotype.shows.Shows sortedShow : sortedshows) {
            if (!this.getHistory().containsKey(sortedShow.getTitle())
                    && sortedShow.getRating() != 0) {
                answer = answer + sortedShow.getTitle();
                return answer;
            }
        }
        for (entities.videotype.Movies movie : movies) {
            if (!this.getHistory().containsKey(movie.getTitle())) {
                answer = answer + movie.getTitle();
                return answer;
            }
        }
        for (entities.videotype.shows.Shows show : shows) {
            if (!this.getHistory().containsKey(show.getTitle())) {
                answer = answer + show.getTitle();
                return answer;
            }
        }


        answer = "BestRatedUnseenRecommendation cannot be applied!";
        return answer;

    }


    private MultiplereturnpremiumUser sortByRating(
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows,
            final String sorttype) {

        Movies auxmovie;
        Shows auxshow;

        if (sorttype.equals("asc")) {

            for (int i = 0; i < movies.size(); i++) {
                for (int j = i; j < movies.size(); j++) {
                    if (movies.get(i).getRating() > movies.get(j).getRating()) {

                        auxmovie = movies.get(i);
                        movies.set(i, movies.get(j));
                        movies.set(j, auxmovie);
                    }
                    if (movies.get(i).getRating()
                            == movies.get(j).getRating()) {
                        if (movies.get(i).getTitle()
                                .compareTo(movies.get(j).getTitle()) > 0) {

                            auxmovie = movies.get(i);
                            movies.set(i, movies.get(j));
                            movies.set(j, auxmovie);
                        }
                    }

                }
            }

            for (int i = 0; i < shows.size(); i++) {
                for (int j = i; j < shows.size(); j++) {
                    if (shows.get(i).getRating() > shows.get(j).getRating()) {

                        auxshow = shows.get(i);
                        shows.set(i, shows.get(j));
                        shows.set(j, auxshow);
                    }
                    if (shows.get(i).getRating() == shows.get(j).getRating()) {
                        if (shows.get(i).getTitle()
                                .compareTo(shows.get(j).getTitle()) > 0) {

                            auxshow = shows.get(i);
                            shows.set(i, shows.get(j));
                            shows.set(j, auxshow);
                        }
                    }

                }
            }
        } else {
            for (int i = 0; i < movies.size(); i++) {
                for (int j = i; j < movies.size(); j++) {
                    if (movies.get(i).getRating() < movies.get(j).getRating()) {

                        auxmovie = movies.get(i);
                        movies.set(i, movies.get(j));
                        movies.set(j, auxmovie);
                    }
                    if (movies.get(i).getRating()
                            == movies.get(j).getRating()) {
                        if (movies.get(i).getTitle()
                                .compareTo(movies.get(j).getTitle()) > 0) {

                            auxmovie = movies.get(i);
                            movies.set(i, movies.get(j));
                            movies.set(j, auxmovie);
                        }
                    }

                }
            }
            for (int i = 0; i < shows.size(); i++) {
                for (int j = i; j < shows.size(); j++) {
                    if (shows.get(i).getRating() < shows.get(j).getRating()) {

                        auxshow = shows.get(i);
                        shows.set(i, shows.get(j));
                        shows.set(j, auxshow);
                    }
                    if (shows.get(i).getRating() == shows.get(j).getRating()) {
                        if (shows.get(i).getTitle()
                                .compareTo(shows.get(j).getTitle()) > 0) {

                            auxshow = shows.get(i);
                            shows.set(i, shows.get(j));
                            shows.set(j, auxshow);
                        }
                    }
                }
            }

        }

        return new MultiplereturnpremiumUser(movies, shows);
    }

    /**
     * @param title este numele videoclipului
     * @return intoarce numele videoclipului adaugat in lista de favorite
     * in cazul in care filmul nu a fost vazut nu se poate adauga astfel se
     * verifica acest lucru , iar daca se constata ca acesta este vizualizat
     * se adauga in lista de favorite doar daca nu exista deja.
     */
    public String favoriteCommand(final String title) {

        String answer = "error -> " + title + " is not seen";

        if (this.viewd(title) == 1) {
            if (this.alreayLiked(title) == 0) {
                this.getFavoritevideos().add(title);
                answer = "success -> "
                        + this.getFavoritevideos()
                                .get(this.getFavoritevideos().size() - 1)
                        + " was added as favourite";
            } else {
                answer = "error -> " + title + " is already in favourite list";
            }
        }

        return answer;
    }

    /**
     * @param title este numele videoclipului
     * @return numele videului insotit de numarul de vizualizari ale acestuia
     * Se cerceteaza daca filmul a fost sau nu vazut daca acesta nu a vost
     * vizualizat atunci se aduaga numele si o vizualizare in history , daca
     * este deja vazut doar se incrementeaza nuamrul de vizualizari ale
     * videoului cu numele respectiv.
     */
    public String viewCommand(final String title) {

        String answer;

        if (this.viewd(title) == 0) {
            this.getHistory().put(title, 1);
            answer =
                    "success -> " + title + " was viewed with total views of "
                            + 1;
        } else {
            this.getHistory().put(title, this.getHistory().get(title) + 1);
            answer =
                    "success -> " + title + " was viewed with total views of "
                            + this.getHistory().get(title);
        }

        return answer;
    }

    /**
     * @param movie parametru care contine numele si ratingul oferit de
     *              utilizator
     * @param movies lista cu filmele din baza de date
     * @param grade ratingul oferit de utilizator
     * @return numele filmului care a fost vazut immpruna cu ratingul / eroare
     * datorita faptului ca a fost deja rate-uit / eroare daca acesta nu a fost
     * vazut
     * Se verifica daca filmul a fost vizualizat sau nu , daca a fost rate-uit
     * sau nu , si in cazul in care nu este cazul erorii , se aduaga in lista
     * de retinguri ale filmului si apoi se returneaza numele impreuna cu
     * ratingul
     */
    public Multiplereturnmovie ratingCommandMovie(
            final Ratingmovies movie,
            final ArrayList<Movies> movies,
            final Double grade) {

        String answer = "error -> " + movie.getTitle() + " is not seen";

        int ok = 1;

        for (int i = 0; i < getMovieratings().size(); i++) {
            if (getMovieratings().get(i).getTitle().equals(movie.getTitle())) {
                ok = 0;
                break;
            }
        }
        if (ok == 0) {
            answer = "error -> " + movie.getTitle() + " has been already rated";
        }

        if (this.viewd(movie.getTitle()) == 1 && ok == 1) {
            movies.get(getMovieIndex(movies, movie.getTitle())).getRatingList()
                    .add(grade);
            this.getMovieratings().add(movie);
            answer = "success -> " + movie.getTitle() + " was rated with "
                    + movie.getRating() + " by " + this.getUsername();

        }


        return new Multiplereturnmovie(answer, movies);
    }

    /**
     * @param show parametru care contine numele si ratingul oferit de
     *             utilizator
     * @param shows lista cu serialele din baza de date
     * @param grade ratingul oferit de utilizator
     * @return numele serialului care a fost vazut immpruna cu ratingul si
     * sezonul caruia i s-a ofeir ratingul /eroare datorita faptului ca a
     * fost deja rate-uit / eroare daca acesta nu a fost vazut
     * Se verifica daca serialul a fost vizualizat sau nu , daca a fost rate-uit
     * sezonul respectiv sau nu , si in cazul in care nu este cazul erorii , se
     * aduaga in lista de retinguri ale sezonului serialului si apoi se
     * returneaza numele impreuna cu ratingul
     */
    public Multiplereturnshow ratingCommandShows(
            final Ratingshows show,
            final ArrayList<Shows> shows,
            final Double grade) {
        String answer = "error -> " + show.getTitle() + " is not seen";
        int ok = 1;

        for (int i = 0; i < this.getShowratings().size(); i++) {
            if (this.getShowratings().get(i).getTitle().equals(show.getTitle())
                    &&
                    this.getShowratings().get(i).getSeasontoberated()
                            == show.getSeasontoberated()) {
                ok = 0;
                break;
            }
        }

        if (this.viewd(show.getTitle()) == 1) {
            if (this.getShowratings().contains(show) && ok == 1) {
                shows.get(getShowIndex(shows, show.getTitle())).getSeasons()
                        .get(show.getSeasontoberated() - 1).getRatings()
                        .add(grade);
                this.getShowratings().add(show);
                answer = "success -> " + show.getTitle() + " was rated with "
                        + show.getRating()
                        + " by " + this.getUsername();
            } else if (!this.getShowratings().contains(show)) {
                shows.get(getShowIndex(shows, show.getTitle())).getSeasons()
                        .get(show.getSeasontoberated() - 1).getRatings()
                        .add(grade);
                this.getShowratings().add(show);
                answer = "success -> " + show.getTitle() + " was rated with "
                        + show.getRating()
                        + " by " + this.getUsername();
            }
        }

        if (ok == 0) {
            answer = "error -> " + show.getTitle() + " has been already rated";
        }

        return new Multiplereturnshow(answer, shows);
    }


    private int getMovieIndex(
            final ArrayList<Movies> movies,
            final String title) {
        int index = 0;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int getShowIndex(
            final ArrayList<Shows> shows,
            final String title) {
        int index = 0;

        for (int i = 0; i < shows.size(); i++) {
            if (shows.get(i).getTitle().equals(title)) {
                index = i;
                break;
            }
        }
        return index;
    }

}






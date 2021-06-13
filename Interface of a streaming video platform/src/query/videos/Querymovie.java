package query.videos;

import entities.userstype.Users;
import entities.videotype.Movies;
import entities.videotype.Ratingmovies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Querymovie {

    /**
     * @param userlist repr
     * @param movies   for output file
     * @param filters  for output file
     * @param number   for output file
     * @param sorttype for output file
     *                 Ofera un string care reprezinta primele N video-uri
     *                 sortate după rating.
     * @return giveAnswerRating
     */
    public String movieRating(final ArrayList<Users> userlist,
                              final ArrayList<Movies> movies,
                              final List<List<String>> filters,
                              final int number,
                              final String sorttype) {
        ArrayList<Movies> filteredmovies;
        filteredmovies = filter(movies, filters);
        ArrayList<Ratingmovies> avrageRatingofEachMovie =
                listRatingEachMovie(userlist, filteredmovies);
        sorted(avrageRatingofEachMovie, sorttype);
        return giveAnswerRating(avrageRatingofEachMovie, number);
    }

    /**
     * @param userlist lista cu userii pentru a vedea listele de videoclipuri
     *                 favorite
     * @param movies   lista cu filmele din baza de date
     * @param filters  lista cu filtrele care trebuiesc aplicate
     * @param number   parametru care reprezinta cate videouri trebuie intoarse
     * @param sorttype parametru care reprezinta modul de sortare
     * @return numel primelor number video-uri sortate descrescător după numărul
     * de apariții în listele de video-uri favorite ale utilizatorilor.
     * Se primeste o lista cu filmele din baza de date care este filtrata ,
     * apoi intr-un hasmap se retine numele filmului si de cate ori a aparut
     * acesta in listele de favorite ale userilor.Ulterior se sorteaza si se
     * transmit numele celor number filme .
     */
    public String favorite(final ArrayList<Users> userlist,
                           final ArrayList<Movies> movies,
                           final List<List<String>> filters, final int number,
                           final String sorttype) {

        ArrayList<Movies> filteredmovies;
        filteredmovies = filter(movies, filters);
        HashMap<String, Integer> favoritemovie =
                favoriteForEachMovie(userlist, filteredmovies);
        favoritemovie = sortByValue(favoritemovie, sorttype);


        return giveAnserHasMaps(favoritemovie, number);
    }

    /**
     * @param movies   lista cu filmele din baza de date
     * @param filters  lista cu filtrele care trebuie aplicate
     * @param number   numarul care precizate cate filme trebuie afisate
     * @param sorttype modul de sortare al filmelor
     * @return primele number video-uri sortate după durata lor
     * Se primese o lista de filme se filtreaza ulterior se sorteaza dupa
     * durata si apoi se ofera numele a number filme.
     */
    public String longest(
            final ArrayList<Movies> movies,
            final List<List<String>> filters,
            final int number,
            final String sorttype) {
        ArrayList<Movies> filteredmovies;
        filteredmovies = filter(movies, filters);
        durationSort(filteredmovies, sorttype);
        return giveAnswer(filteredmovies, number);
    }

    /**
     * @param userlist lista cu userii
     * @param movies   lista cu filmele din baza de date
     * @param filters  lista cu filmele de trebuie aplicate
     * @param number   numarul de filme care trebuie returnat
     * @param sorttype modul de sortare al filmelor
     * @return primele nume ale celor N video-uri sortate după numărul de
     * vizualizări.
     * Se filreaza filmele din baza de date , apoi se creaza un hasmap care sa
     * sa contina ca key numele videoclipurilor si ca values numarul de
     * vizualizari, se sorteaza si paoi se returneaza numele filmelor.
     */
    public String mostViewd(final ArrayList<Users> userlist,
                            final ArrayList<Movies> movies,
                            final List<List<String>> filters,
                            final int number,
                            final String sorttype) {
        ArrayList<Movies> filteredmovies;
        filteredmovies = filter(movies, filters);
        HashMap<String, Integer> viewsmovie =
                viewsForEachMovie(userlist, filteredmovies);

        viewsmovie = sortByValue(viewsmovie, sorttype);

        return giveAnserHasMaps(viewsmovie, number);

    }

    private HashMap<String, Integer> sortByValue(
            final HashMap<String, Integer> hm,
            final String sorttype) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());

        list.sort((o1, o2) -> {
            if (sorttype.equals("asc")) {
                if ((o1.getValue()).compareTo(o2.getValue()) != 0) {
                    return (o1.getValue()).compareTo(o2.getValue());
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            } else {
                if ((o1.getValue()).compareTo(o2.getValue()) != 0) {
                    return -(o1.getValue()).compareTo(o2.getValue());
                } else {
                    return -(o1.getKey().compareTo(o2.getKey()));
                }
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private ArrayList<Movies> filter(
            final ArrayList<Movies> movies,
            final List<List<String>> filters) {

        ArrayList<Movies> filteredmovies = new ArrayList<>();

        String year = filters.get(0).get(0);
        String genre = filters.get(1).get(0);

        if (year == null && genre == null) {
            return movies;
        }
        if (year == null) {
            for (entities.videotype.Movies movie : movies) {
                if (movie.getGenres().contains(genre)) {
                    filteredmovies.add(movie);
                }
            }
        }
        if (genre == null) {
            for (entities.videotype.Movies movie : movies) {
                if (String.valueOf(movie.getYear()).equals(year)) {
                    filteredmovies.add(movie);
                }
            }
        }
        if (year != null && genre != null) {
            for (entities.videotype.Movies movie : movies) {
                if (String.valueOf(movie.getYear()).equals(year)
                        && movie.getGenres().contains(genre)) {
                    filteredmovies.add(movie);
                }
            }
        }

        return filteredmovies;
    }

    private ArrayList<Ratingmovies> listRatingEachMovie(
            final ArrayList<Users> userlist,
            final ArrayList<Movies> movies) {

        ArrayList<Ratingmovies> avrageRatingofEachMovie = new ArrayList<>();

        double movieAvrageRating = 0;
        double numberOfAppearances = 0;

        for (entities.videotype.Movies movie : movies) {
            for (Users users : userlist) {
                for (int k = 0; k < users.getMovieratings().size(); k++) {
                    if (users.getMovieratings().get(k).getTitle()
                            .equals(movie.getTitle())) {
                        movieAvrageRating = movieAvrageRating
                                + users.getMovieratings().get(k).getRating();
                        numberOfAppearances++;
                        break;
                    }
                }

            }

            if (numberOfAppearances != 0) {
                movieAvrageRating = movieAvrageRating / numberOfAppearances;
            }
            Ratingmovies individualMovie = new Ratingmovies(
                    movie.getTitle(), movieAvrageRating);
            avrageRatingofEachMovie.add(
                    avrageRatingofEachMovie.size(), individualMovie);
            movieAvrageRating = 0;
            numberOfAppearances = 0;
        }

        return avrageRatingofEachMovie;
    }

    private void sorted(
            final ArrayList<Ratingmovies> avrageRatingofEachMovie,
            final String sorttype) {

        Ratingmovies auxmovie;

        if (sorttype.equals("asc")) {
            for (int i = 0; i < avrageRatingofEachMovie.size(); i++) {
                for (int j = i; j < avrageRatingofEachMovie.size(); j++) {
                    if (avrageRatingofEachMovie.get(i).getRating()
                            > avrageRatingofEachMovie.get(j).getRating()) {

                        auxmovie = avrageRatingofEachMovie.get(i);
                        avrageRatingofEachMovie.set(
                                i, avrageRatingofEachMovie.get(j));
                        avrageRatingofEachMovie.set(
                                j, auxmovie);
                    }
                }
            }
        } else {
            for (int i = 0; i < avrageRatingofEachMovie.size(); i++) {
                for (int j = i; j < avrageRatingofEachMovie.size(); j++) {
                    if (avrageRatingofEachMovie.get(i).getRating()
                            > avrageRatingofEachMovie.get(j).getRating()) {

                        auxmovie = avrageRatingofEachMovie.get(i);
                        avrageRatingofEachMovie.set(
                                i, avrageRatingofEachMovie.get(j));
                        avrageRatingofEachMovie.set(
                                j, auxmovie);
                    }
                }
            }
        }

    }

    private HashMap<String, Integer> favoriteForEachMovie(
            final ArrayList<Users> userlist,
            final ArrayList<Movies> movies) {

        HashMap<String, Integer> favoritemovie = new HashMap<>();

        int numberofappearances = 0;

        for (entities.videotype.Movies movie : movies) {
            for (Users users : userlist) {
                for (int k = 0; k < users.getFavoritevideos().size(); k++) {
                    if (users.getFavoritevideos().get(k)
                            .equals(movie.getTitle())) {
                        numberofappearances++;
                        break;
                    }
                }

            }

            favoritemovie.put(movie.getTitle(), numberofappearances);
            numberofappearances = 0;
        }
        return favoritemovie;
    }

    private void durationSort(
            final ArrayList<Movies> movies,
            final String sorttype) {

        Movies auxmovie;

        if (sorttype.equals("desc")) {

            for (int i = 0; i < movies.size(); i++) {
                for (int j = i + 1; j < movies.size(); j++) {
                    if (movies.get(i).getDuration()
                            < movies.get(j).getDuration()) {

                        auxmovie = movies.get(i);
                        movies.set(i, movies.get(j));
                        movies.set(j, auxmovie);
                    }
                    if (movies.get(i).getDuration()
                            == movies.get(j).getDuration()) {
                        if (movies.get(i).getTitle()
                                .compareTo(movies.get(j).getTitle()) < 0) {
                            auxmovie = movies.get(i);
                            movies.set(i, movies.get(j));
                            movies.set(j, auxmovie);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < movies.size(); i++) {
                for (int j = i + 1; j < movies.size(); j++) {
                    if (movies.get(i).getDuration()
                            > movies.get(j).getDuration()) {

                        auxmovie = movies.get(i);
                        movies.set(i, movies.get(j));
                        movies.set(j, auxmovie);
                    }
                    if (movies.get(i).getDuration()
                            == movies.get(j).getDuration()) {
                        if (movies.get(i).getTitle()
                                .compareTo(movies.get(j).getTitle()) > 0) {
                            auxmovie = movies.get(i);
                            movies.set(i, movies.get(j));
                            movies.set(j, auxmovie);
                        }
                    }
                }
            }
        }

    }

    private HashMap<String, Integer> viewsForEachMovie(
            final ArrayList<Users> userlist,
            final ArrayList<Movies> movies) {

        HashMap<String, Integer> viewsmovie = new HashMap<>();

        int numberofviews = 0;

        for (entities.videotype.Movies movie : movies) {
            for (Users users : userlist) {
                for (Map.Entry<String, Integer> entry : users.getHistory()
                        .entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.equals(movie.getTitle())) {
                        numberofviews += (int) value;
                        break;
                    }
                }

            }

            viewsmovie.put(movie.getTitle(), numberofviews);
            numberofviews = 0;
        }
        return viewsmovie;
    }

    private String giveAnswer(
            final ArrayList<Movies> movies,
            final int number) {

        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;

        for (entities.videotype.Movies movie : movies) {
            if (cnt == number) {
                break;
            }
            if (movie.getDuration() != 0) {
                if (cnt == 0) {
                    answer.append(movie.getTitle());
                } else {
                    answer.append(", ").append(movie.getTitle());
                }
                cnt++;
            }
        }
        answer.append("]");
        return answer.toString();

    }

    private String giveAnswerRating(
            final ArrayList<Ratingmovies> avrageratingofeachmovie,
            final int number) {

        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;


        for (Ratingmovies ratingmovies : avrageratingofeachmovie) {
            if (cnt == number) {
                break;
            }
            if (ratingmovies.getRating() != 0) {
                if (cnt == 0) {
                    answer.append(ratingmovies.getTitle());
                } else {
                    answer.append(", ").append(ratingmovies.getTitle());
                }
                cnt++;
            }
        }


        answer.append("]");
        return answer.toString();

    }

    private String giveAnserHasMaps(final HashMap<String, Integer> hasmap,
                                    final int number) {

        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;


        for (Map.Entry<String, Integer> entry : hasmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value != 0 && cnt < number) {
                if (cnt == 0) {
                    answer.append(key);
                } else {
                    answer.append(", ").append(key);
                }
                cnt++;
            }
        }


        answer.append("]");
        return answer.toString();

    }

}

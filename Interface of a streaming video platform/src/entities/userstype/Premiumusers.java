package entities.userstype;

import entertainment.Genre;
import entities.videotype.Movies;
import entities.videotype.Video;
import entities.videotype.shows.Shows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public final class Premiumusers extends Users {

    /**
     * @param username numele utilizatorului
     * @param subscriptiontype tipul de aboanment care este premium
     * @param history hasmap care contine numele videourile vizualizate
     *                insotite de numarul de vizualizari
     * @param favoritevideos o lista in care se retin videourile favorite
     */
    public Premiumusers(final String username, final String subscriptiontype,
                        final Map<String, Integer> history,
                        final ArrayList<String> favoritevideos) {
        super(username, subscriptiontype, history, favoritevideos);
    }

    private static HashMap<String, Integer> sortbyvalue(
            final HashMap<String, Integer> hm,
            final String sorttype) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());


        list.sort((o1, o2) -> {
            if (sorttype.equals("asc")) {
                return (o1.getValue()).compareTo(o2.getValue());
            } else {
                return -((o1.getValue()).compareTo(o2.getValue()));
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * @param premiumusers lista cu userii cu subscriptia premium
     * @param movies lista cu filmele din data de baze
     * @param shows lista cu serialele din data de baze
     * @return popular este o functie care intoarce numele primului video
     * nevizualizat din cel mai popular gen .
     * Functia transforma campul Genre intr-un hasmap care sa tina cont de
     * cate viuzalizari are fiecare gen pentru a decide care este cel mai
     * popular , hasmpaul are campurile value initial initializate cu 0.
     * Urmand ca acestea sa fie actualizate prin parcurgerea utilizatorilor
     * care au campul history . Ulterior se sorteaza hasmapul si se cauta in
     * functie de gen in baza de date adica mai intai in movies si apoi in shows
     * filmul care nu este vizualizat.
     * */
    public String popular(final ArrayList<Premiumusers> premiumusers,
                          final ArrayList<Movies> movies,
                          final ArrayList<Shows> shows) {
        String answer = "PopularRecommendation result: ";

        HashMap<String, Integer> numbersofviewsgenre = new HashMap<>();
        String[] genre = Arrays.stream(Genre.values())
                .map(Enum::toString)
                .toArray(String[]::new);

        for (int i = 0; i < genre.length; i++) {
            genre[i] = genre[i].toLowerCase();
        }

        ArrayList<Video> videos = new ArrayList<>();
        videos.addAll(movies);
        videos.addAll(shows);

        for (String s : genre) {
            numbersofviewsgenre
                    .put(s, numberofviewsgenre(premiumusers, videos, s));
        }

        numbersofviewsgenre = sortbyvalue(numbersofviewsgenre, "desc");

        for (String key : numbersofviewsgenre.keySet()) {
            for (int i = 0; i < videos.size(); i++) {
                if (containsgenre(key, videos, videos.get(i).getTitle())
                        && !this.getHistory()
                                .containsKey(videos.get(i).getTitle())) {
                    answer = answer + videos.get(i).getTitle();
                    return answer;
                }
            }
        }

        return "PopularRecommendation cannot be applied!";
    }

    private int numberofviewsgenre(final ArrayList<Premiumusers> premiumusers,
                                   final ArrayList<Video> videos,
                                   final String genre) {

        int numberofviews = 0;

        for (Premiumusers premiumUser : premiumusers) {
            for (Map.Entry<String, Integer> entry : premiumUser.getHistory()
                    .entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (containsgenre(genre, videos, key)) {
                    numberofviews = numberofviews + value;
                }
            }
        }

        return numberofviews;
    }

    private boolean containsgenre(final String genre,
                                  final ArrayList<Video> videos,
                                  final String title) {

        for (Video video : videos) {
            if (video.getTitle().equals(title)) {
                for (int j = 0; j < video.getGenres().size(); j++) {
                    if (video.getGenres().get(j).toLowerCase().equals(genre)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     *
     * @param allusers lista cu userii
     * @param movies lista cu filmele din baza de date
     * @param shows lista cu serialele din baza de date
     * @return numele întoarce videoclipul care e cel mai des intalnit in
     * lista de favorite a userilor.
     * Se creazaz un hasmap in care key-urile sunt numele videourilor , iar
     * values sunt reprezentate de numarul de aparitii in listele de favorite
     * ale userilor , se sorteaza si apoi se returneaza numele filmului .
     */
    public String favoriterecommandation(final ArrayList<Users> allusers,
                                         final ArrayList<Movies> movies,
                                         final ArrayList<Shows> shows) {

        HashMap<String, Integer> favorite =
                favoriteForEach(allusers, movies, shows);
        favorite = sortbyvalue(favorite, "desc");
        return giveanserhasmaps(favorite);
    }

    /**
     *
     * @param movies lista cu filmele din baza de date
     * @param shows lista cu serialelel din baza de date
     * @param genre String care reprezinta genul filmului dorit
     * @return numele tuturor videoclipurile nevăzute de user dintr-un anumit
     * gen. Se filtreaza filmele si serialele date pentru a ramane doar cele
     * din genul oferit. Se sorteaza dupa rating si apoi se afiseaza toate
     * videoclipurile in cazul in care userul a vazut toate videourile se
     * afiseaza un mesaj de eroare.
     */
    public String search(final ArrayList<Movies> movies,
                         final ArrayList<Shows> shows,
                         final String genre) {
        ArrayList<Movies> filteredmovies;
        ArrayList<Shows> filteredshows;
        filteredmovies = filtermoviesandshows(movies, shows, genre).getFirst();
        filteredshows = filtermoviesandshows(movies, shows, genre).getSecond();

        ArrayList<Video> videos = new ArrayList<>();
        videos.addAll(filteredmovies);
        videos.addAll(filteredshows);

        sortbyrating(videos, "asc");


        StringBuilder answer =
                new StringBuilder("SearchRecommendation result: [");
        int cnt = 0;

        for (Video video : videos) {
            if (!this.getHistory().containsKey(video.getTitle())) {
                if (cnt == 0) {
                    answer.append(video.getTitle());
                } else {
                    answer.append(", ").append(video.getTitle());
                }
                cnt++;
            }
        }

        answer.append("]");
        if (answer.toString().equals("SearchRecommendation result: []")) {
            answer = new StringBuilder(
                    "SearchRecommendation cannot be applied!");
        }
        return answer.toString();
    }

    private MultiplereturnpremiumUser filtermoviesandshows(
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows,
            final String genre) {

        ArrayList<Movies> filteredmovies = new ArrayList<>();
        ArrayList<Shows> filteredshows = new ArrayList<>();

        for (entities.videotype.Movies movie : movies) {
            if (movie.getGenres().contains(genre)) {
                filteredmovies.add(movie);
            }
        }

        for (entities.videotype.shows.Shows show : shows) {
            if (show.getGenres().contains(genre)) {
                filteredshows.add(show);
            }
        }

        return new MultiplereturnpremiumUser(filteredmovies, filteredshows);

    }

    private void sortbyrating(final ArrayList<Video> videos,
                              final String sorttype) {

        Video auxvideo;

        if (sorttype.equals("asc")) {

            for (int i = 0; i < videos.size(); i++) {
                for (int j = i; j < videos.size(); j++) {
                    if (videos.get(i).getRating() > videos.get(j).getRating()) {

                        auxvideo = videos.get(i);
                        videos.set(i, videos.get(j));
                        videos.set(j, auxvideo);
                    }
                    if (videos.get(i).getRating()
                            ==
                            videos.get(j).getRating()) {
                        if (videos.get(i).getTitle()
                                .compareTo(videos.get(j).getTitle()) > 0) {
                            auxvideo = videos.get(i);
                            videos.set(i, videos.get(j));
                            videos.set(j, auxvideo);
                        }
                    }

                }
            }
        } else {
            for (int i = 0; i < videos.size(); i++) {
                for (int j = i; j < videos.size(); j++) {
                    if (videos.get(i).getRating() < videos.get(j).getRating()) {
                        auxvideo = videos.get(i);
                        videos.set(i, videos.get(j));
                        videos.set(j, auxvideo);
                    }
                    if (videos.get(i).getRating()
                            == videos.get(j).getRating()) {
                        if (videos.get(i).getTitle()
                                .compareTo(videos.get(j).getTitle()) < 0) {
                            auxvideo = videos.get(i);
                            videos.set(i, videos.get(j));
                            videos.set(j, auxvideo);
                        }
                    }

                }
            }

        }

    }

    private HashMap<String, Integer> favoriteForEach(
            final ArrayList<Users> allusers,
            final ArrayList<Movies> movies,
            final ArrayList<Shows> shows) {

        HashMap<String, Integer> favorite = new HashMap<>();

        int numberofappearances = 0;

        for (entities.videotype.Movies movie : movies) {
            for (Users allUser : allusers) {
                for (int k = 0; k < allUser.getFavoritevideos().size(); k++) {
                    if (allUser.getFavoritevideos().get(k)
                            .equals(movie.getTitle())) {
                        numberofappearances++;
                    }
                }

            }

            favorite.put(movie.getTitle(), numberofappearances);
            numberofappearances = 0;
        }

        for (entities.videotype.shows.Shows show : shows) {
            for (Users allUser : allusers) {
                for (int k = 0; k < allUser.getFavoritevideos().size(); k++) {
                    if (allUser.getFavoritevideos().get(k)
                            .equals(show.getTitle())) {
                        numberofappearances++;
                    }
                }

            }

            favorite.put(show.getTitle(), numberofappearances);
            numberofappearances = 0;
        }

        return favorite;
    }

    private String giveanserhasmaps(final HashMap<String, Integer> favorite) {

        String answer = "FavoriteRecommendation result: ";


        for (String key : favorite.keySet()) {
            if (!this.getHistory().containsKey(key)) {
                answer = answer + key;
                return answer;
            }
        }

        answer = "FavoriteRecommendation cannot be applied!";
        return answer;

    }

}

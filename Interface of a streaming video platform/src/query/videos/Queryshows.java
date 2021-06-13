package query.videos;

import entities.userstype.Users;
import entities.videotype.shows.Shows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Queryshows {

    private static HashMap<String, Integer> sortByValue(
            final HashMap<String, Integer> hm,
            final String sorttype) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());

        list.sort((o1, o2) -> {
            if (sorttype.equals("asc")) {
                if ((o1.getValue()).compareTo(o2.getValue()) == 0) {
                    return (o1.getKey()).compareTo(o2.getKey());
                } else {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            } else {

                if ((o1.getValue()).compareTo(o2.getValue()) == 0) {
                    return -(o1.getKey()).compareTo(o2.getKey());
                } else {
                    return -(o1.getValue()).compareTo(o2.getValue());
                }
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    final ArrayList<Shows> filter(
            final ArrayList<Shows> shows,
            final List<List<String>> filters) {

        ArrayList<Shows> filteredshows = new ArrayList<>();

        String year = filters.get(0).get(0);
        String genre = filters.get(1).get(0);

        if (year == null && genre == null) {
            return shows;
        }
        if (year == null) {
            for (entities.videotype.shows.Shows show : shows) {
                if (show.getGenres().contains(genre)) {
                    filteredshows.add(show);
                }
            }
        }
        if (genre == null) {
            for (entities.videotype.shows.Shows show : shows) {
                if (String.valueOf(show.getYear()).equals(year)) {
                    filteredshows.add(show);
                }
            }
        }
        if (year != null && genre != null) {
            for (entities.videotype.shows.Shows show : shows) {
                if (String.valueOf(show.getYear()).equals(year)
                        && show.getGenres().contains(genre)) {
                    filteredshows.add(show);
                }
            }
        }

        return filteredshows;
    }

    private HashMap<String, Integer> favoriteForEachShow(
            final ArrayList<Users> userlist,
            final ArrayList<Shows> shows) {

        HashMap<String, Integer> favoriteshow = new HashMap<>();

        int numberofappearances = 0;

        for (entities.videotype.shows.Shows show : shows) {
            for (Users users : userlist) {
                for (int k = 0; k < users.getFavoritevideos().size(); k++) {
                    if (users.getFavoritevideos().get(k)
                            .equals(show.getTitle())) {
                        numberofappearances++;
                        break;
                    }
                }

            }

            favoriteshow.put(show.getTitle(), numberofappearances);
            numberofappearances = 0;
        }
        return favoriteshow;
    }

    private HashMap<String, Integer> viewsForEachShow(
            final ArrayList<Users> userlist,
            final ArrayList<Shows> shows) {

        HashMap<String, Integer> viewsshow = new HashMap<>();

        int numberofviews = 0;

        for (entities.videotype.shows.Shows show : shows) {
            for (Users users : userlist) {
                for (Map.Entry<String, Integer> entry : users.getHistory()
                        .entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.equals(show.getTitle())) {
                        numberofviews += (int) value;
                        break;
                    }
                }

            }

            viewsshow.put(show.getTitle(), numberofviews);
            numberofviews = 0;
        }
        return viewsshow;
    }

    private ArrayList<Shows> sortByRating(
            final ArrayList<Shows> shows,
            final String sorttype) {

        Shows auxshow;

        if (sorttype.equals("desc")) {

            for (int i = 0; i < shows.size(); i++) {
                for (int j = i; j < shows.size(); j++) {
                    if (shows.get(i).getRating() > shows.get(j).getRating()) {

                        auxshow = shows.get(i);
                        shows.set(i, shows.get(j));
                        shows.set(j, auxshow);
                    }
                }
            }
        } else {
            for (int i = 0; i < shows.size(); i++) {
                for (int j = i; j < shows.size(); j++) {
                    if (shows.get(i).getRating() < shows.get(j).getRating()) {

                        auxshow = shows.get(i);
                        shows.set(i, shows.get(j));
                        shows.set(j, auxshow);
                    }
                }
            }
        }

        return shows;


    }

    private String giveAnswerRating(
            final ArrayList<Shows> shows,
            final int number,
            final String sorttype) {

        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;

        if (sorttype.equals("desc")) {
            for (entities.videotype.shows.Shows show : shows) {
                if (cnt == number) {
                    break;
                }
                if (show.getRating() != 0) {
                    if (cnt == 0) {
                        answer.append(show.getTitle());
                    } else {
                        answer.append(", ").append(show.getTitle());
                    }
                    cnt++;
                }
            }
        } else {
            for (int i = shows.size() - 1; i >= 0; i--) {
                if (cnt == number) {
                    break;
                }
                if (shows.get(i).getRating() != 0) {
                    if (cnt == 0) {
                        answer.append(shows.get(i).getTitle());
                    } else {
                        answer.append(", ").append(shows.get(i).getTitle());
                    }
                    cnt++;
                }
            }
        }

        answer.append("]");
        return answer.toString();

    }

    /**
     * @param souri    lista cu serialele din baza de date
     * @param filters  lista cu filtrele ce trebuei aplicate
     * @param number   nuamrul de seriale ce trbuie retunate
     * @param sorttype modul de sortare
     * @return numel primelor number seriale sortate după rating
     */
    public String rating(
            final ArrayList<Shows> souri,
            final List<List<String>> filters,
            final int number,
            final String sorttype) {

        ArrayList<Shows> filteredshows;
        filteredshows = filter(souri, filters);
        sortByRating(filteredshows, sorttype);

        return giveAnswerRating(filteredshows, number, sorttype);
    }

    /**
     * @param userList
     * @param shouri
     * @param filters
     * @param number
     * @param sorttype
     * @return
     */
    public String mostViewd(
            final ArrayList<Users> userList,
            final ArrayList<Shows> shouri,
            final List<List<String>> filters,
            final int number,
            final String sorttype) {
        ArrayList<Shows> filteredshows;
        filteredshows = filter(shouri, filters);
        HashMap<String, Integer> viewsshow = viewsForEachShow(userList,
                filteredshows);

        viewsshow = sortByValue(viewsshow, sorttype);

        return giveAnserHasMaps(viewsshow, number);

    }

    /**
     * @param userlist
     * @param souri
     * @param filters
     * @param number
     * @param sorttype
     * @return
     */
    public String favorite(
            final ArrayList<Users> userlist,
            final ArrayList<Shows> souri,
            final List<List<String>> filters,
            final int number,
            final String sorttype) {

        ArrayList<Shows> filteredshows;
        filteredshows = filter(souri, filters);
        HashMap<String, Integer> favoriteshow =
                favoriteForEachShow(userlist, filteredshows);
        favoriteshow = sortByValue(favoriteshow, sorttype);


        return giveAnserHasMaps(favoriteshow, number);
    }


    private ArrayList<Shows> showsDurationSort(
            final ArrayList<Shows> showsarraylist,
            final String sorttype) {

        Shows auxshow;

        if (sorttype.equals("asc")) {

            for (int i = 0; i < showsarraylist.size(); i++) {
                for (int j = i; j < showsarraylist.size(); j++) {
                    if (showsarraylist.get(i).getDuration()
                            > showsarraylist.get(j).getDuration()) {

                        auxshow = showsarraylist.get(i);
                        showsarraylist.set(i, showsarraylist.get(j));
                        showsarraylist.set(j, auxshow);
                    }
                }
            }
        } else {
            for (int i = 0; i < showsarraylist.size(); i++) {
                for (int j = i; j < showsarraylist.size(); j++) {
                    if (showsarraylist.get(i).getDuration()
                            < showsarraylist.get(j).getDuration()) {

                        auxshow = showsarraylist.get(i);
                        showsarraylist.set(i, showsarraylist.get(j));
                        showsarraylist.set(j, auxshow);
                    }
                }
            }
        }

        return showsarraylist;
    }


    private String giveAnswer(
            final ArrayList<Shows> showsarraylist,
            final int number) {
        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;


        for (entities.videotype.shows.Shows show : showsarraylist) {
            if (cnt == number) {
                break;
            }
            if (show.getDuration() != 0) {
                if (cnt == 0) {
                    answer.append(show.getTitle());
                } else {
                    answer.append(", ").append(show.getTitle());
                }
                cnt++;
            }
        }

        answer.append("]");
        return answer.toString();

    }

    /**
     * @param souri
     * @param filters
     * @param number
     * @param sorttype
     * @return
     */
    public String longest(
            final ArrayList<Shows> souri,
            final List<List<String>> filters,
            final int number,
            final String sorttype) {

        ArrayList<Shows> filteredshows;
        filteredshows = filter(souri, filters);
        filteredshows = showsDurationSort(filteredshows, sorttype);
        return giveAnswer(filteredshows, number);
    }


    private String giveAnserHasMaps(
            final HashMap<String, Integer> hasmap,
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


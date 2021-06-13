package query.actors;

import actor.Actors;
import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Queryactors {
    /**
     * @param actori   lista cu actroii din baza de date
     * @param number   numarul de actroi doriti
     * @param sorttype modul de sortare
     * @return primele number nume de actori sortați după media ratingurilor
     * filmelor și a serialelor în care au jucat.
     * In clasa Actors avem un camp care este destinat "ratingului" unui actor
     * astfel se sorteaza dupa acest rating si apoi se intorc numele acestora.
     */
    public String average(
            final ArrayList<Actors> actori,
            final int number,
            final String sorttype) {
        sortActors(actori, sorttype);
        return actorAnswerRating(actori, number);
    }

    /**
     * @param actori   lista cu actroii din baza de date
     * @param sorttype modul de sortare
     * @param awards   lista cu premii
     * @return toți actorii cu premiile menționate in awards
     * Se filtreaza actorii , daca acestaia au castigat toate premiile
     * propuse in awards se afla in filteredactors .Ulterior se sorteaza dupa
     * campul din clasa Awards care retine nr total de premii castigate de
     * acestai . Apoi se furnizeaza numele lor.
     */
    public String awards(
            final ArrayList<Actors> actori,
            final String sorttype,
            final List<String> awards) {
        ArrayList<Actors> filteredactors;
        filteredactors = filterActorsByAwards(actori, awards);
        sortActorsByAwards(filteredactors, sorttype);
        return actorAnswer(filteredactors);
    }

    /**
     * @param actori   lista cu actroii din baza de date
     * @param sorttype modul de sortare
     * @param words    lista cu cuvintele cheie
     * @return toți actorii în descrierea cărora apar toate keywords-urile
     * Se filtreaza actorii si se pastreazaz cei care au toate keywords-urile
     * si apoi acesti actori sunt sortati alfabetic.
     */
    public String filterDescription(
            final ArrayList<Actors> actori,
            final String sorttype,
            final List<String> words) {
        ArrayList<Actors> filteredactros;
        filteredactros = filteractorsbywords(actori, words);
        sortAlphabetically(filteredactros, sorttype);
        return actorAnswer(filteredactros);
    }


    private ArrayList<Actors> filterActorsByAwards(
            final ArrayList<Actors> actori,
            final List<String> awards) {

        if (awards == null) {
            return actori;
        }

        ArrayList<Actors> filteredactros = new ArrayList<>();
        int ok;


        for (actor.Actors actor : actori) {
            ok = 1;
            for (String s : awards) {
                ActorsAwards award = ActorsAwards.valueOf(s);
                if (!actor.getAwards().containsKey(award)) {
                    ok = 0;
                }
            }
            if (ok == 1 && actor.totalAwards() != 0) {
                filteredactros.add(actor);
            }
        }
        return filteredactros;
    }

    private ArrayList<Actors> filteractorsbywords(
            final ArrayList<Actors> actori,
            final List<String> words) {

        if (words == null) {
            return actori;
        }

        ArrayList<Actors> filteredactros = new ArrayList<>();
        HashMap<String, Integer> hashmapword = new HashMap<>();


        int ok;

        for (actor.Actors actor : actori) {
            ok = 1;
            for (String word : words) {
                hashmapword.put(word, 0);
            }
            String[] tokens =
                    actor.getCareerDescription().toLowerCase().split("[-\\s+]");
            for (int k = 0; k < tokens.length; k++) {
                tokens[k] = tokens[k].replaceAll("[^\\w]", "");
            }
            for (String key : hashmapword.keySet()) {
                for (String token : tokens) {
                    if (key.equals(token)) {
                        hashmapword.put(key, hashmapword.get(key) + 1);
                    }
                }
            }

            for (Integer value : hashmapword.values()) {
                if (value == 0) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                filteredactros.add(actor);
            }

        }
        return filteredactros;
    }

    private void sortAlphabetically(
            final ArrayList<Actors> actori,
            final String sorttype) {

        Actors auxactor;

        if (sorttype.equals("asc")) {
            for (int i = 0; i < actori.size(); i++) {
                for (int j = i + 1; j < actori.size(); j++) {
                    if (actori.get(i).getName()
                            .compareTo(actori.get(j).getName()) > 0) {
                        auxactor = actori.get(i);
                        actori.set(i, actori.get(j));
                        actori.set(j, auxactor);
                    }
                }
            }
        } else {
            for (int i = 0; i < actori.size(); i++) {
                for (int j = i + 1; j < actori.size(); j++) {
                    if (actori.get(i).getName()
                            .compareTo(actori.get(j).getName()) < 0) {
                        auxactor = actori.get(i);
                        actori.set(i, actori.get(j));
                        actori.set(j, auxactor);
                    }
                }
            }
        }
    }


    private void sortActorsByAwards(
            final ArrayList<Actors> actori,
            final String sorttype) {

        Actors auxactor;


        if (sorttype.equals("asc")) {
            for (int i = 0; i < actori.size(); i++) {
                for (int j = i; j < actori.size(); j++) {
                    if (actori.get(i).getNumberofawards()
                            > actori.get(j).getNumberofawards()) {
                        auxactor = actori.get(i);
                        actori.set(i, actori.get(j));
                        actori.set(j, auxactor);
                    }
                    if (actori.get(i).getNumberofawards()
                            == actori.get(j).getNumberofawards()) {
                        if (actori.get(i).getName()
                                .compareTo(actori.get(j).getName()) > 0) {
                            auxactor = actori.get(i);
                            actori.set(i, actori.get(j));
                            actori.set(j, auxactor);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < actori.size(); i++) {
                for (int j = i; j < actori.size(); j++) {
                    if (actori.get(i).getNumberofawards()
                            < actori.get(j).getNumberofawards()) {
                        auxactor = actori.get(i);
                        actori.set(i, actori.get(j));
                        actori.set(j, auxactor);
                    }
                    if (actori.get(i).getNumberofawards()
                            == actori.get(j).getNumberofawards()) {
                        if (actori.get(i).getName()
                                .compareTo(actori.get(j).getName()) < 0) {
                            auxactor = actori.get(i);
                            actori.set(i, actori.get(j));
                            actori.set(j, auxactor);
                        }
                    }
                }
            }
        }

    }


    private void sortActors(
            final ArrayList<Actors> actori,
            final String sorttype) {

        Actors auxactor;


        if (sorttype.equals("asc")) {
            for (int i = 0; i < actori.size(); i++) {
                for (int j = i; j < actori.size(); j++) {
                    if (actori.get(i).getRating() > actori.get(j).getRating()) {
                        auxactor = actori.get(i);
                        actori.set(i, actori.get(j));
                        actori.set(j, auxactor);
                    }
                    if (actori.get(i).getRating()
                            == actori.get(j).getRating()) {
                        if (actori.get(i).getName()
                                .compareTo(actori.get(j).getName()) > 0) {
                            auxactor = actori.get(i);
                            actori.set(i, actori.get(j));
                            actori.set(j, auxactor);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < actori.size(); i++) {
                for (int j = i; j < actori.size(); j++) {
                    if (actori.get(i).getRating() < actori.get(j).getRating()) {
                        auxactor = actori.get(i);
                        actori.set(i, actori.get(j));
                        actori.set(j, auxactor);
                    }
                    if (actori.get(i).getRating()
                            == actori.get(j).getRating()) {
                        if (actori.get(i).getName()
                                .compareTo(actori.get(j).getName()) < 0) {
                            auxactor = actori.get(i);
                            actori.set(i, actori.get(j));
                            actori.set(j, auxactor);
                        }
                    }
                }
            }
        }

    }


    private String actorAnswerRating(
            final ArrayList<Actors> actori,
            final int number) {

        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;


        for (actor.Actors actor : actori) {
            if (cnt == number) {
                break;
            }
            if (actor.getRating() != 0) {
                if (cnt == 0) {
                    answer.append(actor.getName());
                } else {
                    answer.append(", ").append(actor.getName());
                }
                cnt++;
            }
        }

        answer.append("]");
        return answer.toString();
    }

    private String actorAnswer(final ArrayList<Actors> actori) {

        StringBuilder answer = new StringBuilder("Query result: [");

        int cnt = 0;

        for (actor.Actors actor : actori) {
            if (cnt == 0) {
                answer.append(actor.getName());
            } else {
                answer.append(", ").append(actor.getName());
            }
            cnt++;

        }

        answer.append("]");
        return answer.toString();
    }


}




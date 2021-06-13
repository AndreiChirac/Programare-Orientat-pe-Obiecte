package query.users;

import entities.userstype.Users;

import java.util.ArrayList;

public class Queryuser {
    /**
     * Lista cu userii.
     */
    private final ArrayList<Users> userlist;

    /**
     * @param listofusers lista cu userii
     */
    public Queryuser(final ArrayList<Users> listofusers) {
        this.userlist = listofusers;
    }

    /**
     * @return lista cu userii
     */
    public ArrayList<Users> getUserlist() {
        return userlist;
    }

    /**
     * @param usersarraylist lista de useri
     * @param number         nuamrul de ultilizaotri care trebuie afisati
     * @param sortType       modul de sortare
     * @return primii number utilizatori sortați după numărul de ratings pe
     * care le-au dat
     */
    public String numberOfRatings(
            final ArrayList<Users> usersarraylist,
            final int number,
            final String sortType) {
        ArrayList<Users> sorteduserlist;
        sorteduserlist = sortUserList(usersarraylist, sortType);

        StringBuilder answer = new StringBuilder("Query result: [");
        int cnt = 0;

        for (Users users : sorteduserlist) {
            if (cnt == number) {
                break;
            }
            if (users.getMovieratings().size()
                    + users.getShowratings().size() != 0) {
                if (cnt == 0) {
                    answer.append(users.getUsername());
                } else {
                    answer.append(", ").append(users.getUsername());
                }
                cnt++;
            }
        }
        answer.append("]");
        return answer.toString();
    }

    private ArrayList<Users> sortUserList(
            final ArrayList<Users> usersArrayList,
            final String sortType) {
        Users auxuser;
        if (sortType.equals("asc")) {
            for (int i = 0; i < usersArrayList.size(); i++) {
                for (int j = i + 1; j < usersArrayList.size(); j++) {
                    if ((usersArrayList.get(i).getMovieratings().size()
                            + usersArrayList.get(i).getShowratings().size())
                            > (usersArrayList.get(j).getMovieratings().size()
                            + usersArrayList.get(j).getShowratings().size())) {
                        auxuser = usersArrayList.get(i);
                        usersArrayList.set(i, usersArrayList.get(j));
                        usersArrayList.set(j, auxuser);
                    }
                    if ((usersArrayList.get(i).getMovieratings().size()
                            + usersArrayList.get(i).getShowratings().size())
                            == (usersArrayList.get(j).getMovieratings().size()
                            + usersArrayList.get(j).getShowratings().size())) {
                        if (usersArrayList.get(i).
                                getUsername().compareTo(usersArrayList.get(j)
                                .getUsername()) > 0) {
                            auxuser = usersArrayList.get(i);
                            usersArrayList.set(i, usersArrayList.get(j));
                            usersArrayList.set(j, auxuser);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < usersArrayList.size(); i++) {
                for (int j = i + 1; j < usersArrayList.size(); j++) {
                    if ((usersArrayList.get(i).getMovieratings().size()
                            + usersArrayList.get(i).getShowratings().size())
                            < (usersArrayList.get(j).getMovieratings().size()
                            + usersArrayList.get(j).getShowratings().size())) {

                        auxuser = usersArrayList.get(i);
                        usersArrayList.set(i, usersArrayList.get(j));
                        usersArrayList.set(j, auxuser);
                    }
                    if ((usersArrayList.get(i).getMovieratings().size()
                            + usersArrayList.get(i).getShowratings().size())
                            == (usersArrayList.get(j).getMovieratings().size()
                            + usersArrayList.get(j).getShowratings().size())) {
                        if (usersArrayList.get(i)
                                .getUsername().compareTo(usersArrayList.get(j)
                                        .getUsername()) < 0) {
                            auxuser = usersArrayList.get(i);
                            usersArrayList.set(i, usersArrayList.get(j));
                            usersArrayList.set(j, auxuser);
                        }
                    }
                }
            }
        }

        return usersArrayList;
    }


}

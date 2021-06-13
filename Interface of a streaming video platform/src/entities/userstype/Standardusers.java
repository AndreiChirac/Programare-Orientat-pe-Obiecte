package entities.userstype;

import java.util.ArrayList;
import java.util.Map;

public class Standardusers extends Users {

    /**
     * @param name numele utilizatorului
     * @param subtype subscriptia de tipul BASIC
     * @param watchhistory hasmap cu numele filmelor si numarul de
     *                     vizualizari ale acestora
     * @param likedvideos lista cu filmele favorite ale utilizaotrului
     */
    public Standardusers(final String name,
                         final String subtype,
                         final Map<String, Integer> watchhistory,
                         final ArrayList<String> likedvideos) {
        super(name, subtype, watchhistory, likedvideos);
    }


}

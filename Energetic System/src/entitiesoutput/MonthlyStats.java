package entitiesoutput;

import java.util.ArrayList;

public class MonthlyStats {
    /**
     * numarul lunuii in care ne aflam.
     */
    private int month;
    /**
     * lista cu id-urile distribuitorilor care iau curent de la producatorul
     * respectiv.
     */
    private ArrayList<Integer> distributorsIds;

    /**
     * @param monthData           numarul lunuii in care ne aflam
     * @param distributorsIdsData lista cu id-urile distribuitorilor care
     *                            iau curent de la producatorul respectiv
     */
    public MonthlyStats(final int monthData,
                        final ArrayList<Integer> distributorsIdsData) {
        this.month = monthData;
        this.distributorsIds = distributorsIdsData;
    }

    /**
     * @return numarul lunuii in care ne aflam
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param monthData numarul lunuii in care ne aflam.
     */
    public void setMonth(final int monthData) {
        this.month = monthData;
    }

    /**
     * @return lista cu id-urile distribuitorilor care iau curent de la
     * producatorul respectiv.
     */
    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    /**
     * @param distributorsIdsDara lista cu id-urile distribuitorilor care
     *                            iau curent de la producatorul respectiv.
     */
    public void setDistributorsIds(
            final ArrayList<Integer> distributorsIdsDara) {
        this.distributorsIds = distributorsIdsDara;
    }
}

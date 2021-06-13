package parsing;

import java.util.ArrayList;

public class Input {
    /**
     * numarul de simulari.
     */
    private int numberOfTurns;
    /**
     * datele initiale ce fac referire atat la utilizatori cat si la
     * distribuitori.
     */
    private InitialDataInput initialData;
    /**
     * actualizarile care trebuie sa se exercite la inceperea lunii atat din
     * punct de vedere al venirii unor noi consumaotri cat si in cazul
     * distribuitorilor care isi modicia costurile.
     */
    private ArrayList<MonthlySingleDataInput> monthlyUpdates;

    /**
     * @return numarul de simulari
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * @param numberOfTurnsData numarul de simulari
     */
    public void setNumberOfTurns(final int numberOfTurnsData) {
        this.numberOfTurns = numberOfTurnsData;
    }

    /**
     * @return datele initiale ce fac referire atat la utilizatori cat si la
     * distribuitori
     */
    public InitialDataInput getInitialData() {
        return initialData;
    }

    /**
     * @param initialSituation datele initiale ce fac referire atat la
     *                         utilizatori cat si la distribuitori
     */
    public void setInitialData(final InitialDataInput initialSituation) {
        this.initialData = initialSituation;
    }

    /**
     * @return actualizarile care trebuie sa se exercite la inceperea lunii
     */
    public ArrayList<MonthlySingleDataInput> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     * @param monthlyUpdatesData actualizarile care trebuie sa se exercite la
     *                           inceperea lunii
     */
    public void setMonthlyUpdates(
            final ArrayList<MonthlySingleDataInput> monthlyUpdatesData) {
        this.monthlyUpdates = monthlyUpdatesData;
    }

    /**
     * @return ne ajuta sa vizualizam inputul
     */
    @Override
    public String toString() {
        return "Input{ "
                + "numberOfTurns=" + numberOfTurns
                + ", initialData=" + initialData
                + ", monthlyUpdates=" + monthlyUpdates
                + '}';
    }
}

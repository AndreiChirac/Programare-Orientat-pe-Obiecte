package entitiesoutput;

public abstract class EntitiesOutput {
    /**
     * @return id-ul unei entitati
     */
    public abstract int getId();

    /**
     * @return faptul ca entitatea este sau nu in faliment
     */
    public abstract boolean getIsBankrupt();

    /**
     * @return bugetul final al unei entittait.
     */
    public abstract int getBudget();

}

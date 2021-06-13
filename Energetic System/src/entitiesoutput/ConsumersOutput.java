package entitiesoutput;

public class ConsumersOutput extends EntitiesOutput {
    /**
     * ID-ul unui consumator.
     */
    private final int id;
    /**
     * returneaza statutul de adevar al afirmatiei : Clientul este in faliment.
     */
    private final boolean isBankrupt;
    /**
     * returneaza bugetul final al unui consumator.
     */
    private final int budget;

    /**
     * @param idData         id consumator
     * @param isBankruptData daca un consumator este sau nu in faliment
     * @param finalBuget     bugetul final al unui consumator
     */
    public ConsumersOutput(final int idData,
                           final boolean isBankruptData,
                           final int finalBuget) {
        this.id = idData;
        this.isBankrupt = isBankruptData;
        this.budget = finalBuget;
    }

    /**
     * @return ID-ul unui consumator
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * @return returneaza statutul de adevar al afirmatiei : Clientul este in
     * faliment
     */
    @Override
    public boolean getIsBankrupt() {
        return this.isBankrupt;
    }

    /**
     * @return bugetul final al unui consumator
     */
    @Override
    public int getBudget() {
        return this.budget;
    }
}

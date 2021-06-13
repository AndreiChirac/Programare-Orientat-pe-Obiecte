package parsing;

public class ConsumersDataInput {
    /**
     * ID-ul unui consumator.
     */
    private int id;
    /**
     * Bugetul initial al unui consumator.
     */
    private int initialBudget;
    /**
     * venitul lunar obtinut de un consumator.
     */
    private int monthlyIncome;

    /**
     * @return ID-ul unui consumator
     */
    public int getId() {
        return id;
    }

    /**
     * @param idData ID-ul unui consumator
     */
    public void setId(final int idData) {
        this.id = idData;
    }

    /**
     * @return Bugetul initial al unui consumator
     */
    public int getInitialBudget() {
        return initialBudget;
    }

    /**
     * @param initialBudgetData Bugetul initial al unui consumator
     */
    public void setInitialBudget(final int initialBudgetData) {
        this.initialBudget = initialBudgetData;
    }

    /**
     * @return venitul lunar obtinut de un consumator.
     */
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @param monthlyIncomeData venitul lunar obtinut de un consumator.
     */
    public void setMonthlyIncome(final int monthlyIncomeData) {
        this.monthlyIncome = monthlyIncomeData;
    }

    /**
     * @return ne ajuta sa vizualizam inputul
     */
    @Override
    public String toString() {
        return "ConsumersDataInput{ "
                + "id=" + id
                + ", initialBudget=" + initialBudget
                + ", monthlyIncome=" + monthlyIncome
                + '}';
    }
}

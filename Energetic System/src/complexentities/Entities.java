package complexentities;

import contracts.Contracts;

import java.util.ArrayList;

public class Entities {
    /**
     * ID-ul.
     */
    private final int id;
    /**
     * Bugetul initial.
     */
    private int budget;
    /**
     * statutul de adevar al afirmatiei : Entitatea este in
     * faliment.
     */
    private boolean isBankrupt;
    /**
     * lista de contracte.
     */
    private ArrayList<Contracts> contracts = new ArrayList<>();

    /**
     * @param idData            id
     * @param initialBudgetData bugetulinitial
     */
    public Entities(final int idData,
                    final int initialBudgetData) {
        this.id = idData;
        this.budget = initialBudgetData;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return buget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * @param initialBudgetData bugetul la inceput de luna.
     */
    public void setBudget(final int initialBudgetData) {
        this.budget = initialBudgetData;
    }

    /**
     * @return entitatea se afla sau nu in faliment
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * @param bankruptData seteaza proprietatea unei entitati de a se afla
     *                     sau nu in faliment
     */
    public void setBankrupt(final boolean bankruptData) {
        isBankrupt = bankruptData;
    }

    /**
     * @return contractele
     */
    public ArrayList<Contracts> getContracts() {
        return contracts;
    }

    /**
     * @param contractsData contacte
     */
    public void setContracts(final ArrayList<Contracts> contractsData) {
        this.contracts = contractsData;
    }

}

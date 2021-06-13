package entitiesoutput;

import contracts.Contracts;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class DistibutorsOutput extends EntitiesOutput {
    /**
     * ID-ul unui distribuitor.
     */
    private final int id;
    /**
     * energia necesara unui distibuitor.
     */
    private final int energyNeededKW;
    /**
     * costul unui contract.
     */
    private final int contractCost;
    /**
     * bugetul final al unui distibuitor.
     */
    private final int budget;
    /**
     * strategia conform careia un distribuitor isi selecteaza furnizorii de
     * curent
     */
    private final EnergyChoiceStrategyType producerStrategy;
    /**
     * statutul de adevar al afirmatiei : Distibuitorul este in
     * faliment.
     */
    private final boolean isBankrupt;
    /**
     * lista de contracte a fiecarui consumator care este abonat la
     * distibuirorul respectv.
     */
    private final ArrayList<Contracts> contracts;

    /**
     * @param idData               id distibuitor
     * @param energyNeededKWData   energia necesara unui distibuitor
     * @param contractCostData     costul unui contract
     * @param producerStrategyData strategia conform careia un distribuitor isi
     *                             selecteaza furnizorii de curent
     * @param isBankruptData       conditie faliment
     * @param budgetData           bugetul final
     * @param contractsData        contractele consumatorilor la acel
     *                             distribuitor
     */
    public DistibutorsOutput(final int idData,
                             final int energyNeededKWData,
                             final int contractCostData,
                             final EnergyChoiceStrategyType producerStrategyData,
                             final boolean isBankruptData,
                             final int budgetData,
                             final ArrayList<Contracts> contractsData) {
        this.id = idData;
        this.energyNeededKW = energyNeededKWData;
        this.contractCost = contractCostData;
        this.producerStrategy = producerStrategyData;
        this.isBankrupt = isBankruptData;
        this.budget = budgetData;
        this.contracts = contractsData;
    }

    /**
     * @return ID-ul unui distribuitor
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * @return statutul de adevar al afirmatiei : Distibuitorul este in faliment
     */
    @Override
    public boolean getIsBankrupt() {
        return this.isBankrupt;
    }

    /**
     * @return bugetul final al unui distibuitor
     */
    @Override
    public int getBudget() {
        return this.budget;
    }

    /**
     * @return lista de contracte
     */
    public ArrayList<Contracts> getContracts() {
        return this.contracts;
    }

    /**
     * @return energia necesara unui distibuitor
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * @return costul unui contract
     */
    public int getContractCost() {
        return contractCost;
    }

    /**
     * @return strategia conform careia un distribuitor isi selecteaza
     * furnizorii de curent
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

}

package parsing;

import strategies.EnergyChoiceStrategyType;

public class DistributorsDataInput {
    /**
     * ID-ul unui distribuitor.
     */
    private int id;
    /**
     * durata unui contract.
     */
    private int contractLength;
    /**
     * bugetul initial al unui distribuitor.
     */
    private int initialBudget;
    /**
     * costul initial pentru realizarea infrastructurii.
     */
    private int initialInfrastructureCost;
    /**
     * cantitatea de energie lunara de care distibuitorul are nevoie.
     */
    private int energyNeededKW;
    /**
     * strategia distibuitorului de a alege sursa de energie.
     */
    private EnergyChoiceStrategyType producerStrategy;

    /**
     * @return ID-ul unui distribuitor
     */
    public int getId() {
        return id;
    }

    /**
     * @param idData ID-ul unui distribuitor
     */
    public void setId(final int idData) {
        this.id = idData;
    }

    /**
     * @return durata unui contract
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     * @param contractLengthData durata unui contract
     */
    public void setContractLength(final int contractLengthData) {
        this.contractLength = contractLengthData;
    }

    /**
     * @return bugetul initial al unui distribuitor
     */
    public int getInitialBudget() {
        return initialBudget;
    }

    /**
     * @param initialBudgetData bugetul initial al unui distribuitor
     */
    public void setInitialBudget(final int initialBudgetData) {
        this.initialBudget = initialBudgetData;
    }

    /**
     * @return costul initial pentru realizarea infrastructurii
     */
    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * @param initialInfrastructureCostData costul initial pentru
     *                                      realizarea infrastructurii
     */
    public void setInitialInfrastructureCost(
            final int initialInfrastructureCostData) {
        this.initialInfrastructureCost = initialInfrastructureCostData;
    }

    /**
     * @return energia de care are nevoie un distibuitor
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * @param energyNeededKWData energia de care are nevoie un distibuitor
     */
    public void setEnergyNeededKW(final int energyNeededKWData) {
        this.energyNeededKW = energyNeededKWData;
    }

    /**
     * @return strategia pe care o adopta un distibuitor
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    /**
     * @param producerStrategyData strategia pe care o adopta un distribuitor
     */
    public void setProducerStrategy(
            final EnergyChoiceStrategyType producerStrategyData) {
        this.producerStrategy = producerStrategyData;
    }
}

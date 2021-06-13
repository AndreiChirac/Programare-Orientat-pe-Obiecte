package parsing;

import entities.EnergyType;

public class ProducersDataInput {
    /**
     * ID-ul unui producator.
     */
    private int id;
    /**
     * tipul de energie a unui producator.
     */
    private EnergyType energyType;
    /**
     * numărul maxim de distribuitori către care poate oferi energie.
     */
    private int maxDistributors;
    /**
     * pretul pe KWh a unui producator.
     */
    private double priceKW;
    /**
     * cantitatea lunară de energie oferită fiecărui distribuitor.
     */
    private int energyPerDistributor;

    /**
     * @return ID-ul unui producator
     */
    public int getId() {
        return id;
    }

    /**
     * @param idData ID-ul unui producator
     */
    public void setId(final int idData) {
        this.id = idData;
    }

    /**
     * @return tipul de energie a unui producator
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * @param energyTypeData tipul de energie a unui producator
     */
    public void setEnergyType(final EnergyType energyTypeData) {
        this.energyType = energyTypeData;
    }

    /**
     * @return numărul maxim de distribuitori către care poate oferi energie
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * @param maxDistributorsData numărul maxim de distribuitori către care
     *                            poate oferi energie
     */
    public void setMaxDistributors(final int maxDistributorsData) {
        this.maxDistributors = maxDistributorsData;
    }

    /**
     * @return pretul pe KWh a unui producator
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     * @param priceKWData pretul pe KWh a unui producator
     */
    public void setPriceKW(final double priceKWData) {
        this.priceKW = priceKWData;
    }

    /**
     * @return cantitatea lunară de energie oferită fiecărui distribuitor
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * @param energyPerDistributorData cantitatea lunară de energie oferită
     *                                 fiecărui distribuitor
     */
    public void setEnergyPerDistributor(final int energyPerDistributorData) {
        this.energyPerDistributor = energyPerDistributorData;
    }
}

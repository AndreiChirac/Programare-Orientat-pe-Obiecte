package parsing;

public class ProducerChangesDataInput {
    /**
     * ID-ul unui producator.
     */
    private int id;
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
